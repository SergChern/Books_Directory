package books.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import books.server.Author;
import books.server.Book;
import books.server.HibernateUtil;
import books.server.Snake;
import books.services.ZZZService;

@Controller
public class List_BooksController extends SimpleFormController {
    String seekName = null;

    public List_BooksController() {
        setCommandClass(Book.class);
        setCommandName("book");
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        if (HibernateUtil.isName(request, "name"))
            seekName = HibernateUtil.getValueName(request, "name");

        Book book = new Book();

        if (seekName != null && !seekName.isEmpty())
            book.setName(seekName);

        return book;
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {
        String param = request.getParameter("del");

        if (param != null && !param.isEmpty()) {
            Long id = new Long(param);
            Book book = zzzService.getBook(id);
            zzzService.delete(book);
        }

        Map referenceData = new HashMap();
        List<Snake> snakes = zzzService.getSnake("list_of_books");
        getServletContext().setAttribute("snakes", snakes);

        List<Book> list_Book = null;
        if (seekName != null && !seekName.isEmpty())
            list_Book = zzzService.findBooks(seekName);
        else
            list_Book = zzzService.getBooks();

        for (Book book : list_Book)
            if (book.getAuthors().isEmpty())
                book.getAuthors().add(new Author());

        getServletContext().setAttribute("login0", HibernateUtil.getUsername());
        referenceData.put("books", list_Book);
        return referenceData;
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        Book book = (Book) command;
        if (HibernateUtil.isName(request, "seek"))
            return new ModelAndView(new RedirectView(getSuccessView()));
        else
            return null;
    }

    private ZZZService zzzService;

    public void setZzzService(ZZZService zzzService) {
        this.zzzService = zzzService;
    }
}