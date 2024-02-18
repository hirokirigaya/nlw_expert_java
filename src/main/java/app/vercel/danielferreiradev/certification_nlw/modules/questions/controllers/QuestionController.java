package app.vercel.danielferreiradev.certification_nlw.modules.questions.controllers;

import app.vercel.danielferreiradev.certification_nlw.modules.questions.dto.AlternativesResultDTO;
import app.vercel.danielferreiradev.certification_nlw.modules.questions.dto.QuestionResultDTO;
import app.vercel.danielferreiradev.certification_nlw.modules.questions.entities.AlternativesEntity;
import app.vercel.danielferreiradev.certification_nlw.modules.questions.entities.QuestionEntity;
import app.vercel.danielferreiradev.certification_nlw.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);
        return result.stream().map(QuestionController::mapQuestionToDTO).toList();
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        QuestionResultDTO questionResultDTO = QuestionResultDTO.builder()
                .id(question.getId())
                .description(question.getDescription())
                .technology(question.getTechnology())
                .build();

        List<AlternativesResultDTO> alternatives = question.getAlternatives()
                .stream().map(QuestionController::mapAlternativeToDTO).toList();

        questionResultDTO.setAlternatives(alternatives);
        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativeToDTO(AlternativesEntity alternative) {
        return AlternativesResultDTO.builder()
                .description(alternative.getDescription())
                .id(alternative.getId())
                .build();
    }
}
