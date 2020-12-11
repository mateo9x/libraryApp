package com.mateo9x.recruitmentapp.converters;

import com.mateo9x.recruitmentapp.commands.BookCommand;
import com.mateo9x.recruitmentapp.model.Book;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {

    @Synchronized
    @Nullable
    @Override
    public Book convert (BookCommand source){
        if (source == null) {
            return null;
        }
        final Book book = new Book();
        book.setId(source.getId());
        book.setName(source.getName());
        book.setAuthor(source.getAuthor());
        book.setReleaseDate(source.getReleaseDate());
        book.setComment(source.getComment());
        return book;
    }

}
