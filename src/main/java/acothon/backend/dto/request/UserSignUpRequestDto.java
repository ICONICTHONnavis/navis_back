package acothon.backend.dto.request;

import acothon.backend.domain.Major;
import acothon.backend.domain.User;
import lombok.Builder;

@Builder
public record UserSignUpRequestDto(
        Long studentNumber,
        String password,
        String studentName,
        String major) {

        public User toEntity(Major major) {
            String admissionYear = this.studentNumber.toString().substring(0, 4);  // 앞 4글자 추출

            return User.builder()
                    .studentName(this.studentName)
                    .studentNumber(this.studentNumber)
                    .password(this.password)
                    .admission(admissionYear) // 입학년도 자동 설정 부분
                    .major(major)
                    .build();
        }
}
