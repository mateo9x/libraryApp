package com.mateo9x.recruitmentapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationCommand {

    private Long id;
    private String reservedAt;
    private String userId;

}
