package models;

import java.util.Objects;

public class Zoo {
    private int id;
    private String name;
    private String location;

    public Zoo(int id) {
        this.id = id;
    }

    public Zoo(String name) {
        this.name = name;
    }

    public Zoo() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Zoo zoo = (Zoo) o;
        return id == zoo.id && Objects.equals(name, zoo.name) && Objects.equals(location, zoo.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location);
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}