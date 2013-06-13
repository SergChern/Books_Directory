package books.server;

import java.io.Serializable;

public class Snake implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public Snake() {
    }

    public Snake(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
