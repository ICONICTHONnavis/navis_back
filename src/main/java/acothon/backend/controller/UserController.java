package acothon.backend.controller;

import acothon.backend.dto.request.UserLoginRequestDto;
import acothon.backend.dto.request.UserSignUpRequestDto;
import acothon.backend.dto.request.ValidDuplicateRequestDto;
import acothon.backend.dto.response.ResponseDto;
import acothon.backend.dto.response.UserLoginResponseDto;
import acothon.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseDto<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto){
        return new ResponseDto<>(userService.login(userLoginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseDto<Boolean> signup(@RequestBody UserSignUpRequestDto userSignUpRequestDto){
        return new ResponseDto<>(userService.signup(userSignUpRequestDto));
    }

    @PostMapping("/duplicate")
    public ResponseDto<Boolean> validDuplicate(@RequestBody ValidDuplicateRequestDto validDuplicateRequestDto){
        return new ResponseDto<>(userService.validDuplicate(validDuplicateRequestDto));
    }
}
