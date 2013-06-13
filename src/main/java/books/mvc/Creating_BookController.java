package books.mvc;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import books.server.Author;
import books.server.Book;
import books.server.Snake;
import books.services.ZZZService;

@Controller
public class Creating_BookController extends SimpleFormController {
    private Long id = null;

    public Creating_BookController() {
        setCommandClass(Book.class);
        setCommandName("book");
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String param = request.getParameter("id");
        Book book = null;
        if (param != null && !param.isEmpty()) {
            id = new Long(param);
            book = zzzService.getBook(id);
        } else
            book = (Book) super.formBackingObject(request);
        return book;
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();
        List<Snake> snakes = null;

        if (id == null)
            snakes = zzzService.getSnake("creating_a_of_the_book");
        else
            snakes = zzzService.getSnake("modification_book");

        getServletContext().setAttribute("snakes", snakes);

        List aList = zzzService.getAuthorAll(id);
        getServletContext().setAttribute("allAuthors", aList);
        return referenceData;
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        Book book = (Book) command;
        zzzService.saveOrUpdate(book);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws Exception {
        binder.registerCustomEditor(Author.class, "authors", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                id = new Long(text);
                Author type = zzzService.getAuthor(id);
                setValue(type);
            }
        });
    }

    private ZZZService zzzService;

    public void setZzzService(ZZZService zzzService) {
        this.zzzService = zzzService;
    }
}