package acothon.backend.dto.response;

import acothon.backend.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class ChatListResponseDto {
        private Long chatId;
        private String messageQuestion;
        private String messageAnswer;
        private LocalDate date;

        @Builder
        public ChatListResponseDto(Chat chat){
            this.chatId = chat.getId();
            this.messageQuestion = chat.getQuestion();
            this.messageAnswer = chat.getAnswer();
            this.date = chat.getDate();
        }

        public static ChatListResponseDto of(Chat chat){
            return ChatListResponseDto.builder()
                    .chat(chat)
                    .build();
        }

}
