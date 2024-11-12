package acothon.backend.controller;

import acothon.backend.dto.response.ChatListResponseDto;
import acothon.backend.dto.response.ResponseDto;
import acothon.backend.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat/{userId}")
    public ResponseDto<List<ChatListResponseDto>> chatList(@PathVariable Long userId){
        return new ResponseDto<>(chatService.chatList(userId));
    }

}
