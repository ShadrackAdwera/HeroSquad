package models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Squad)) return false;
        Squad squad = (Squad) o;
        return size == squad.size &&
                getId() == squad.getId() &&
                Objects.equals(getName(), squad.getName()) &&
                Objects.equals(getCause(), squad.getCause());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), size, getCause(), getId());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setId(int id) {
        this.id = id;
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
