package acothon.backend.service;


import acothon.backend.domain.Complete;
import acothon.backend.domain.Subject;
import acothon.backend.domain.SubjectType;
import acothon.backend.domain.User;
import acothon.backend.exception.ApiException;
import acothon.backend.exception.ErrorDefine;
import acothon.backend.repository.CompleteRepository;
import acothon.backend.repository.SubjectRepository;
import acothon.backend.repository.SubjectTypeRepository;
import acothon.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    private final SubjectTypeRepository subjectTypeRepository;

    public String saveExcelToEntity(Long studentNumber, MultipartFile file) {

        if(file.isEmpty()) {
            throw new ApiException(ErrorDefine.FILE_NOT_FOUND);
        }

        User user = userRepository.findByStudentNumber(studentNumber)
                .orElseThrow(()-> new ApiException(ErrorDefine.USER_NOT_FOUND));

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
                String number = "";
                String professor= "";
                String point = "";
                String subjectTypeName="";

                for (Cell cell : row) {
                    switch (cell.getColumnIndex()) {
                        case 2:
                            // 들은 연도 ex. 2024
                            semester = new StringBuilder(cell.toString());
                            break;
                        case 3:
                            // 들은 연도 + 이수 학기 (1, 2, 계절)
                            if (cell.toString().equals("1학기")) {
                                semester.append("-1"); //2024-1
                            } else if (cell.toString().equals("2학기")) {
                                semester.append("-2");
                            } else {
                                semester = new StringBuilder("계절");
                            }
                            break;
                        case 4:
                            number = cell.toString(); // 학수번호
                            break;
                        case 6:
                            subjectName = cell.toString(); // 과목명
                            break;
                        case 9:
                            professor = cell.toString(); // 교수
                            break;
                        case 10:
                            point = cell.toString();
                            point = String.valueOf(point.charAt(0));
                            break;
                        case 11:
                            grade = cell.toString();
                            if (grade.equals("A+")) {
                                grade = "4.5";
                            } else if (grade.equals("A0")) {
                                grade = "4.0";
                            } else if (grade.equals("B+")) {
                                grade = "3.5";
                            } else if (grade.equals("B0")) {
                                grade = "3.0";
                            } else if (grade.equals("C+")) {
                                grade = "2.5";
                            } else if (grade.equals("C0")) {
                                grade = "2.0";
                            } else if (grade.equals("D+")) {
                                grade = "1.5";
                            } else if (grade.equals("D0")) {
                                grade = "1.0";
                            } else if (grade.equals("P")) {
                                grade = "4.5";
                            } else if (grade.equals("F")) {
                                grade = "0.0";
                            }
                            break;
                        case 15:
                            if(cell.toString().isEmpty())
                                subjectTypeName = "extra";
                            else if (cell.toString().contains("전공"))
                                subjectTypeName = "major";
                            else if (cell.toString().equals("기초교양(교필)"))
                                subjectTypeName = "common";
                            else if (cell.toString().equals("수학"))
                                subjectTypeName = "general";
                            else if(cell.toString().equals("기초과학"))
                                subjectTypeName = "general";
                            else if(cell.toString().equals("기본소양"))
                                subjectTypeName = "general";

                            break;

                    }
                }

                SubjectType subjectType = subjectTypeRepository.findSubjectTypeByName(subjectTypeName)
                        .orElseThrow(() -> new ApiException(ErrorDefine.SUBJECTTYPE_NAME_NOT_FOUND));

                List<Subject> subjects = subjectRepository.findByName(subjectName);
                Subject subject;
                if (subjects.isEmpty()) {
                    // 새로운 Subject 객체 생성
                    subject = Subject.builder()
                            .subjectName(subjectName)
                            .professor(professor)
                            .number(number)
                            .point(point)
                            .semester(semester.toString())
                            .subjectType(subjectType)
                            .build();
                    subject = subjectRepository.save(subject);
                } else {
                    // 첫 번째 Subject 가져오기
                    subject = subjects.get(0);
                }

                // Complete 객체 생성 및 추가
                Complete complete = Complete.builder()
                        .grade(grade)
                        .point(point)
                        .subject(subject)
                        .user(user)
                        .semester(semester.toString()).build();
                completes.add(complete);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Complete complete : completes) {
            System.out.println(complete);
            try {
                completeRepository.save(complete);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        return"Excel UpLoad success!";
    }
}
