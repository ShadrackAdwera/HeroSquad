package models;

import java.util.Objects;

public class Squad {
    private String name;
    private int maxSize;
    private String cause;
    private int id;

    public Squad(String name, int maxSize, String cause) {
        this.name = name;
        this.maxSize = maxSize;
        this.cause = cause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Squad)) return false;
        Squad squads = (Squad) o;
        return getMaxSize() == squads.getMaxSize() &&
                getId() == squads.getId() &&
                Objects.equals(getName(), squads.getName()) &&
                Objects.equals(getCause(), squads.getCause());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMaxSize(), getCause(), getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}