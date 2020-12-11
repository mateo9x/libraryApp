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
    private Long reservationId;
    @Column(name = "reserved_at")
    private Date reservedAt;
    private String userId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;



    public Reservation(Long reservationId, String userId) {
        this.reservationId = reservationId;
        this.userId = userId;
    }

    public Reservation(){}
}
