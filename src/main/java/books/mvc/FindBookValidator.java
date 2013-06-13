package books.mvc;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import books.server.Book;

@Service
public class FindBookValidator implements Validator {
    public boolean supports(Class clazz) {
        return clazz.equals(Book.class);
    }

    public void validate(Object command, Errors errors) {
        Book book = (Book) command;
    }
}
