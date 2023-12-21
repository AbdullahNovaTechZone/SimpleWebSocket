package com.enovatechzone.chat.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageDTO {
    private Type type;
    private String content;
    private String sender;
}
