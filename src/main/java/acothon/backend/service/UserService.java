package acothon.backend.service;

import acothon.backend.domain.Major;
import acothon.backend.domain.User;
import acothon.backend.dto.request.UserLoginRequestDto;
import acothon.backend.dto.request.UserSignUpRequestDto;
import acothon.backend.dto.request.ValidDuplicateRequestDto;
import acothon.backend.dto.response.UserLoginResponseDto;
import acothon.backend.exception.ApiException;
import acothon.backend.exception.ErrorDefine;
import acothon.backend.repository.MajorRepository;
import acothon.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MajorRepository majorRepository;

    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto){
        User user = userRepository.findByStudentNumber(userLoginRequestDto.studentNumber())
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        return UserLoginResponseDto.of(user);
    }

    public Boolean signup(UserSignUpRequestDto userSignUpRequestDto){
        if (userRepository.existsByStudentNumber(userSignUpRequestDto.studentNumber())) {
            throw new ApiException(ErrorDefine.DUPLICATE_STUDENT_NUMBER);
        }

        Major major = majorRepository.findByName(userSignUpRequestDto.major())
                .orElseThrow(() -> new ApiException(ErrorDefine.MAJOR_NOT_FOUND));

        User user = userSignUpRequestDto.toEntity(major);
        userRepository.save(user);

        return true;
    }

    public Boolean validDuplicate(ValidDuplicateRequestDto validDuplicateRequestDto){
        if (userRepository.existsByStudentNumber(validDuplicateRequestDto.studentNumber())) {
            throw new ApiException(ErrorDefine.DUPLICATE_STUDENT_NUMBER);
        }

        return true;
    }


}
