package com.mateo9x.recruitmentapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationCommand {

    private Long reservationId;
    private String reservedAt;
    private String userId;

}
