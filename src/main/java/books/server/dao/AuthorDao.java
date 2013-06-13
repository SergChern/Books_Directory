package books.server.dao;

import books.server.Author;

public class AuthorDao extends BaseDao<Author> {

    public AuthorDao() {
        super(Author.class);
    }
}
