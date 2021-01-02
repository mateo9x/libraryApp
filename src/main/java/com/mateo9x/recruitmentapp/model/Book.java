package com.mateo9x.recruitmentapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name="BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "comment")
    private String comment;
    @Column(name = "releasedate")
    private String releaseDate;

  /* @OneToOne(mappedBy = "book")
    private Reservation reservation;*/


    public Book(Long id, String name, String author, String releaseDate, String comment) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
        this.comment = comment;
    }

    public Book(){}


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", shortComment='" + comment + '\'' +
                '}';
    }


}
