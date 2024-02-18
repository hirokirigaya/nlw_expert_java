package app.vercel.danielferreiradev.certification_nlw.modules.students.controllers;

import app.vercel.danielferreiradev.certification_nlw.modules.students.dto.VerifyIfHasCertificationDTO;
import app.vercel.danielferreiradev.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @PostMapping("/verify-if-has-certification")
    public String verifyIfHasCertification(@RequestBody VerifyIfHasCertificationDTO dto) {
        var result = verifyIfHasCertificationUseCase.execute(dto);
        System.out.println(result);

        return result ? "Prova já realizada" : "Prova pode ser realizada";
    }
}
