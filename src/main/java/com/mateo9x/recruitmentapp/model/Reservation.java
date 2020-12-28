package com.mateo9x.recruitmentapp.model;


import com.mateo9x.recruitmentapp.repository.BookRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "RESERVATION")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private String reservedAt;
    private String userId;




    public Reservation(Long reservationId, String reservedAt, String userId) {
        this.reservationId = reservationId;
        this.reservedAt = reservedAt;
        this.userId = userId;
    }

    public Reservation(){}
}
