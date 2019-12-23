package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String name;
    private int maxSize;
    private String cause;
    private List<Hero> heroMembers;
    private static List<Squad> allSquads = new ArrayList<>();

    public Squad(String name, int maxSize, String cause){
        this.name = name;
        this.maxSize = maxSize;
        this.cause = cause;
        this.heroMembers = new ArrayList<>();
        allSquads.add(this);
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

    public void addHeroToSquad(Hero newHero){
        heroMembers.add(newHero);
    }

    public List<Hero> getAllHeroesInSquad(){
        return heroMembers;
    }
    public static List<Squad> getAllSquads() {
        return allSquads;
    }
}
