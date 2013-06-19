package books.tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.servlet.context.ServletUtil;
import org.springframework.web.servlet.view.tiles2.TilesView;

import books.server.HibernateUtil;
import books.server.Snake;

public class AutoTilesView extends TilesView {

    private static List snakes = new ArrayList();

    @Override
    protected void renderMergedOutputModel(Map model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ServletContext servletContext = getServletContext();
        TilesContainer container = ServletUtil.getContainer(servletContext);

        if (container == null) {
            throw new ServletException("Tiles container is not initialized. "
                    + "Have you added a TilesConfigurer to your web application context?");
        }

        String snake = (String) servletContext.getAttribute("snake");
        if (snake != null) {
            List<Snake> snakes0 = getSnake(snake);
            servletContext.setAttribute("snakes", snakes0);
        }

        servletContext.setAttribute("login0", HibernateUtil.getUsername());
        super.renderMergedOutputModel(model, request, response);
    }

    public List<Snake> getSnake(String leksem) {
        if (snakes.size() == 1)
            snakes.add("hello");
        int i = snakes.indexOf(leksem);
        if (i == -1)
            snakes.add(leksem);
        else
            while (i + 1 < snakes.size())
                snakes.remove(i + 1);

        List<Snake> snakeList = new ArrayList();
        for (i = 0; i < snakes.size(); i++) {
            snakeList.add(new Snake((String) snakes.get(i)));
        }
        return snakeList;
    }
}
