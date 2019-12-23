package models;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String name;
    private int age;
    private String superPowers;
    private String weakness;
    private boolean squad;
    private static List<Hero> allHeroes = new ArrayList<>();
    public Hero(String name, int age, String superPowers, String weakness){
        this.name = name;
        this.age = age;
        this.superPowers = superPowers;
        this.weakness = weakness;
        this.squad = false;
        allHeroes.add(this);
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSuperPowers() {
        return superPowers;
    }

    public String getWeakness() {
        return weakness;
    }

    public boolean squadMember() {
        return squad;
    }
    public static List<Hero> all(){
        return allHeroes;
    }


}
