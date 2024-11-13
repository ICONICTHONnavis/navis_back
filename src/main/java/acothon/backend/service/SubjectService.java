package acothon.backend.service;

import acothon.backend.domain.Complete;
import acothon.backend.domain.GraduateRequirement;
import acothon.backend.domain.User;
import acothon.backend.exception.ApiException;
import acothon.backend.exception.ErrorDefine;
import acothon.backend.repository.CompleteRepository;
import acothon.backend.repository.GraduateRequirementRepository;
import acothon.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final CompleteRepository completeRepository;
    private final UserRepository userRepository;
    private final GraduateRequirementRepository graduateRequirementRepository;


    public Map<String, Object> completeSubject(Long userId){
        User user = userRepository.findByStudentNumber(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        List<Complete> completes = completeRepository.findByUser(user);
        List<GraduateRequirement> graduateRequirements = graduateRequirementRepository.findByEnrollmentYear(Long.valueOf(user.getAdmission()));

        Map<String, Long> completedCredits = completes.stream()
                .collect(Collectors.toMap(
                        complete -> complete.getSubject().getSubjectType().getName(), // SubjectType의 이름을 키로 사용
                        complete -> Long.parseLong(complete.getPoint()),
                        Long::sum                                                     // 동일한 SubjectType이 있을 경우 학점을 누적
                ));

        Long totalCredits = completes.stream()
                .mapToLong(complete -> Long.parseLong(complete.getPoint()))
                .sum();
        completedCredits.put("totalCredits", totalCredits);

        List<Map<String, Long>> graduateRequirementsList = graduateRequirements.stream()
                .map(req -> Map.of(
                        "enrollmentYear", req.getEnrollmentYear(),
                        "majorCredits", req.getMajorCredits(),
                        "commonCredits", req.getMscCredits(),
                        "generalCredits", req.getGeneralCredits(),
                        "requiredTotalCredits", req.getRequiredCredits()
                ))
                .toList();

        // 최종 결과로 반환할 맵을 생성하고 완료 학점 및 졸업 요건 리스트를 추가
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("completedCredits", completedCredits);
        result.put("graduateRequirements", graduateRequirementsList);

        return result;

    }
}