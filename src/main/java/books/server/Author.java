package books.server;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String surname, name;

    Set<Book> books = new HashSet<Book>();

    public Author() {
        surname = " ";
        name = " ";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String toString() {
        return getName() + " " + getSurname();
    }
}
