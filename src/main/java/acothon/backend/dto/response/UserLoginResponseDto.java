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

    public static UserLoginResponseDto of(User user) {
        return UserLoginResponseDto.builder()
                .studentNumber(user.getStudentNumber())
                .admission(user.getAdmission())
                .build();
    }

}
