package models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getAge() == hero.getAge() &&
                squad == hero.squad &&
                Objects.equals(getName(), hero.getName()) &&
                Objects.equals(getSuperPowers(), hero.getSuperPowers()) &&
                Objects.equals(getWeakness(), hero.getWeakness());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getSuperPowers(), getWeakness(), squad);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSuperPowers(String superPowers) {
        this.superPowers = superPowers;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public void setSquad(boolean squad) {
        this.squad = squad;
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


}
