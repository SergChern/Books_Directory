package books.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import books.server.Book;
import books.server.Snake;
import books.services.ZZZService;

@Controller
public class List_Books_AuthorController extends AbstractController {
    public List_Books_AuthorController() {
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        List<Snake> snakes = zzzService.getSnake("list_of_books_by_the_author");
        getServletContext().setAttribute("snakes", snakes);

        String param = request.getParameter("list");
        List list_Books_Author = null;
        if (param != null && !param.isEmpty()) {
            Long id = new Long(param);
            list_Books_Author = zzzService.getBooks(id);
        } else
            list_Books_Author = new ArrayList<Book>();

        return new ModelAndView("list_of_books_by_the_author", "books", list_Books_Author);
    }

    private ZZZService zzzService;

    public void setZzzService(ZZZService zzzService) {
        this.zzzService = zzzService;
    }
}