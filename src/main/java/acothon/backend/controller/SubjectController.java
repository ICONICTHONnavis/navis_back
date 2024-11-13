package acothon.backend.controller;

import acothon.backend.dto.response.ResponseDto;
import acothon.backend.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/akoMain/{userId}")
    public ResponseDto<?> completeSubject(@PathVariable Long userId){
        return new ResponseDto<>(subjectService.completeSubject(userId));
    }

    @GetMapping("/require/{userId}")
    public ResponseDto<?> requireSubject(@PathVariable Long userId){
        return new ResponseDto<>(subjectService.requireSubject(userId));
    }



}
