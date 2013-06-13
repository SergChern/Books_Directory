package books.server;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name, brief_Description, year_Of_Publication;
    Set<Author> authors = new HashSet<Author>();

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief_Description() {
        return brief_Description;
    }

    public void setBrief_Description(String brief_Description) {
        this.brief_Description = brief_Description;
    }

    public String getYear_Of_Publication() {
        return year_Of_Publication;
    }

    public void setYear_Of_Publication(String year_Of_Publication) {
        this.year_Of_Publication = year_Of_Publication;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
