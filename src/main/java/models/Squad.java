package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String name;
    private int maxSize;
    private String cause;
    private Hero newSquadMember;

    public Squad(String name, int maxSize, String cause, Hero newSquadMember){
        this.name = name;
        this.maxSize = maxSize;
        this.cause = cause;
        this.newSquadMember = newSquadMember;
    }

    public String getName() {
        return name;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getCause() {
        return cause;
    }

    public Hero getSquadMember() {
        return newSquadMember;
    }
}
