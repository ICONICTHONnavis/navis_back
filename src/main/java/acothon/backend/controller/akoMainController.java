package acothon.backend.controller;

import acothon.backend.dto.request.ExcelFileRequestDto;
import acothon.backend.dto.response.ResponseDto;
import acothon.backend.service.CompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/akoMain")
public class akoMainController {
    private final CompleteService completeService;

    @PostMapping("/uploadEx")
    public ResponseDto<String> uploadExcelFileSubject(@RequestParam("studentNumber") Long studentNumber, @RequestParam("file") MultipartFile file) {
        return new ResponseDto<>(completeService.saveExcelToEntity(studentNumber, file));
    }
}
