package books.mvc;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import books.server.Author;

@Service
public class AuthorValidator implements Validator {
    public boolean supports(Class clazz) {
        return clazz.equals(Author.class);
    }

    public void validate(Object command, Errors errors) {
        Author author = (Author) command;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "surname_is_required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name_of_is_required");
    }
}
