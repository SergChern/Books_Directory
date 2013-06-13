package books.server.dao;

import books.server.Book;

public class BookDao extends BaseDao<Book> {

    public BookDao() {
        super(Book.class);
    }
}
