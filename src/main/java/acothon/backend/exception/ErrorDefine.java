package acothon.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorDefine {

    // Bad Request
    INVALID_ARGUMENT("4000", HttpStatus.BAD_REQUEST, "Bad Request: Invalid Arguments"),
    INVALID_HEADER_ERROR("4006", HttpStatus.BAD_REQUEST, "Bad Request: Invalid Header Error"),

    // Not Found
    USER_NOT_FOUND("4040", HttpStatus.NOT_FOUND, "Not Found: User Not Found"),
    MESSAGE_NOT_FOUND("4046", HttpStatus.NOT_FOUND, "Not Found: Message Not Found"),
    MAJOR_NOT_FOUND("4046", HttpStatus.NOT_FOUND, "Not Found: Major Not Found"),
    NO_COMPLETES_FOUND("4047", HttpStatus.NOT_FOUND, "Not Found: Complete Not Found"),
    //Forbidden
    UNAUTHORIZED_USER("4030", HttpStatus.FORBIDDEN, "Forbidden: Unauthorized User"),
    // 엑셀 파일 관련
    FILE_NOT_FOUND("4044", HttpStatus.NOT_FOUND, "Not Found: Excel File Not Found"),
    SUBJECTTYPE_NAME_NOT_FOUND("4045", HttpStatus.NOT_FOUND, "Not Found: SubjectType Not Found"),

    // 소셜로그인 관련
    LOGIN_ACCESS_DENIED("4031", HttpStatus.FORBIDDEN, "Forbidden: Login Access Denied"),
    TOKEN_MALFORMED("4032", HttpStatus.FORBIDDEN, "Forbidden: Token Malformed"),
    TOKEN_TYPE("4033", HttpStatus.FORBIDDEN, "Forbidden: Token Type"),
    TOKEN_EXPIRED("4034", HttpStatus.FORBIDDEN, "Forbidden: Token Expired"),
    TOKEN_UNSUPPORTED("4035", HttpStatus.FORBIDDEN, "Forbidden: Token Unsupported"),
    TOKEN_UNKNOWN("4036", HttpStatus.FORBIDDEN, "Forbidden: Token Unknown"),
    TOKEN_INVALID("4037", HttpStatus.FORBIDDEN, "Forbidden: Token Invalid"),

    DUPLICATE_STUDENT_NUMBER("4038", HttpStatus.FORBIDDEN, "Forbidden: Duplicate Student Number");


    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
