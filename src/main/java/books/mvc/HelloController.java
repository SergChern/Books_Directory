package books.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import books.server.Snake;
import books.services.ZZZService;

@Controller
public class HelloController extends AbstractController {
    public HelloController() {
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        List<Snake> snakes = zzzService.getSnake("_");
        getServletContext().setAttribute("snakes", snakes);

        return new ModelAndView("hello");
    }

    private ZZZService zzzService;

    public void setZzzService(ZZZService zzzService) {
        this.zzzService = zzzService;
    }
}