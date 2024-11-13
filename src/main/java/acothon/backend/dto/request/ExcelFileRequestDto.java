package acothon.backend.dto.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record ExcelFileRequestDto(
        MultipartFile file,
        Long studentNumber) {

    public ExcelFileRequestDto toEntity() {
        return ExcelFileRequestDto.builder()
                .studentNumber(this.studentNumber)
                .file(this.file)
                .build();
    }
}
