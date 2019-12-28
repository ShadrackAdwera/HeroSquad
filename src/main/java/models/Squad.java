package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String name;
    private int size;
    private String cause;
    private int id;

    public Squad(String name, int size, String cause){
        this.name = name;
        this.size = size;
        this.cause = cause;

    }

    public String getName() {
        return name;
    }

    public int getMaxSize() {
        return size;
    }

    public String getCause() {
        return cause;
    }

    public int getId() {
        return id;
    }
}
