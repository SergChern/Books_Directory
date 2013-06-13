package books.mvc;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import books.server.Book;

@Service
public class BookValidator implements Validator {
    public boolean supports(Class clazz) {
        return clazz.equals(Book.class);
    }

    public void validate(Object command, Errors errors) {
        Book book = (Book) command;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brief_Description",
                "brief_Description_is_required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name_is_required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year_Of_Publication",
                "year_Of_Publication_is_required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "authors",
                "year_Of_Publication_is_required");
    }
}
