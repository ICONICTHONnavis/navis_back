package acothon.backend.dto.request;

import lombok.Builder;

@Builder
public record UserLoginRequestDto(
        Long studentNumber,
        String password) {

    public UserLoginRequestDto toEntity() {
        return UserLoginRequestDto.builder()
                .studentNumber(this.studentNumber)
                .password(this.password)
                .build();
    }

}
