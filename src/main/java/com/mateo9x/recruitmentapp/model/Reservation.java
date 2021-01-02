package com.mateo9x.recruitmentapp.model;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "RESERVATION")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reserved_at")
    private String reservedAt;
    private String userId;

 /*  @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;*/



    public Reservation(Long id, String reservedAt, String userId) {
        this.id = id;
        this.reservedAt = reservedAt;
        this.userId = userId;
    }

    public Reservation(){}
}
