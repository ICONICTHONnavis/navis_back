package acothon.backend.dto.response;

import acothon.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserLoginResponseDto {

    private Long studentNumber; // 학번
    private String admission; // 입학년도
    private String studentName;
    private String major;

    public static UserLoginResponseDto of(User user) {
        return UserLoginResponseDto.builder()
                .studentNumber(user.getStudentNumber())
                .studentName(user.getStudentName())
                .major(user.getMajor().getName())
                .admission(user.getAdmission())
                .build();
    }

}
