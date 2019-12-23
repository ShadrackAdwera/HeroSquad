package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String name;
    private int maxSize;
    private String cause;

    public Squad(String name, int maxSize, String cause){
        this.name = name;
        this.maxSize = maxSize;
        this.cause = cause;
    }
}
