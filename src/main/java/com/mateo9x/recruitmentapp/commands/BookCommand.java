package com.mateo9x.recruitmentapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class BookCommand {

    private Long id;
    private String name;
    private String author;
    private String releaseDate;
    private String comment;

}
