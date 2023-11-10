package com.example.challange5.model.Respon;


import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponReportsCustome<T> {
    private UUID id;
    private LocalDate start_date;
    private LocalDate end_date;
    private String name_merchant;
    private Long totalIncome;
    private T listOrder;
}
