package books.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;

import books.server.Author;
import books.server.Book;
import books.server.HibernateUtil;
import books.server.Snake;
import books.services.ZZZService;

@Controller
public class List_AuthorsController extends SimpleFormController {
    public List_AuthorsController() {
        setCommandClass(Author.class);
        setCommandName("author");
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {
        String param = request.getParameter("del");

        if (param != null && !param.isEmpty()) {
            Long id = new Long(param);
            Author author = zzzService.getAuthor(id);

            if (author.getBooks().size() == 0)
                zzzService.delete(author);
            else {
                for (Book book : author.getBooks()) {
                    if (book.getAuthors().size() == 1)
                        zzzService.deleteAuthor(author.getId(), book.getId());
                    else
                        zzzService.deleteAuthor(author.getId(), null);
                }
            }
        }
        Map referenceData = new HashMap();
        List<Snake> snakes = zzzService.getSnake("list_of_authors");
        getServletContext().setAttribute("snakes", snakes);
        getServletContext().setAttribute("login0", HibernateUtil.getUsername());

        List list_Author = zzzService.getAuthors();
        referenceData.put("authors", list_Author);
        return referenceData;
    }

    private ZZZService zzzService;

    public void setZzzService(ZZZService zzzService) {
        this.zzzService = zzzService;
    }
}