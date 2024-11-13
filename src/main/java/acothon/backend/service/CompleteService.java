package acothon.backend.service;


import acothon.backend.domain.Complete;
import acothon.backend.domain.User;
import acothon.backend.dto.request.ExcelFileRequestDto;
import acothon.backend.repository.CompleteRepository;
import acothon.backend.repository.SubjectRepository;
import acothon.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompleteService {

    private final CompleteRepository completeRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public String saveExcelToEntity(ExcelFileRequestDto excelFileRequestDto) throws IOException {

        MultipartFile file = excelFileRequestDto.file();
        Long studentNumber = excelFileRequestDto.studentNumber();
        User user = userRepository.findByStudentNumber(excelFileRequestDto.studentNumber())
                .orElseThrow() ;//


        List<Complete> completes = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())){
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                // 1행 건너 뛰기
                if (row.getRowNum() == 0) {
                    continue;
                }
                String grade = "";
                StringBuilder semester = new StringBuilder();
                String subjectName = "";

                for (Cell cell : row) {
                    switch (cell.getColumnIndex()) {
                        case 2:
                            // 들은 연도 ex. 2024
                            semester = new StringBuilder(cell.toString());
                            break;
                        case 3:
                            if(cell.toString().equals("1학기")) {
                                semester.append("-1"); //2024-1
                            }
                            else if(cell.toString().equals("2학기")) {
                                semester.append("-2");
                            }
                            else {
                                semester = new StringBuilder("계절");
                            }
                            break;
                        case 6:
                            subjectName = cell.toString();
                            break;
                        case 11:
                            grade = cell.toString();
                            break;


                    }
                }
                Complete complete = new Complete();
                complete(user, semester, grade , subjectRepository.findby(subjectName));
                completes.add(complete);
            }

        }

        for (Complete complete : completes) {
            try {
                completeRepository.save(complete);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        return"aaa";
    }
}
