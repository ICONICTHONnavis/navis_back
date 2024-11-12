package acothon.backend.service;

import acothon.backend.domain.Chat;
import acothon.backend.domain.User;
import acothon.backend.dto.response.ChatListResponseDto;
import acothon.backend.exception.ApiException;
import acothon.backend.exception.ErrorDefine;
import acothon.backend.repository.ChatRepository;
import acothon.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public List<ChatListResponseDto> chatList(Long userId){
        User user = userRepository.findByStudentNumber(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        List<Chat> chats = chatRepository.findByUser(user);

        return chats.stream()
                .map(ChatListResponseDto::of)
                .collect(Collectors.toList());
    }
}
