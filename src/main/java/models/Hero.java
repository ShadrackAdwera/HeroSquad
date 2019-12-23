package models;

public class Hero {
    private String name;
    private int age;
    private String superPowers;
    private String weakness;
    private boolean squad;
    public Hero(String name, int age, String superPowers, String weakness){
        this.name = name;
        this.age = age;
        this.superPowers = superPowers;
        this.weakness = weakness;
        this.squad = false;
    }


}
