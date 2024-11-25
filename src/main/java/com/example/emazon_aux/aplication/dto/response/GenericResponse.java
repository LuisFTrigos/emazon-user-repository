package com.example.emazon_aux.aplication.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse {

    private String message;
    private LocalDateTime date;
}
