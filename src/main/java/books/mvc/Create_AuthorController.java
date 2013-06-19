package books.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import books.server.Author;
import books.services.ZZZService;

@Controller
public class Create_AuthorController extends SimpleFormController {
    private Long id = null;

    public Create_AuthorController() {
        setCommandClass(Author.class);
        setCommandName("author");
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String param = request.getParameter("id");
        Author author = null;
        if (param != null && !param.isEmpty()) {
            id = new Long(param);
            author = zzzService.getAuthor(id);
            request.setAttribute("id", null);
        } else
            author = (Author) super.formBackingObject(request);
        return author;
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();
        getServletContext().setAttribute("snake", "create_the_author");
        return referenceData;
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        
        Author author = (Author) command;
        zzzService.saveOrUpdate(author);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    private ZZZService zzzService;

    public void setZzzService(ZZZService zzzService) {
        this.zzzService = zzzService;
    }
}