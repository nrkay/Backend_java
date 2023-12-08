package com.example.binarchat.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private String sender;
    private String content;
    private String timestamp;


}
