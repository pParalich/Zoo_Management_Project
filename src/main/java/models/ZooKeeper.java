package models;

import java.util.Objects;

public class ZooKeeper {
    private int id;
    private String name;
    private String specialization;

    public ZooKeeper(int id) {
        this.id = id;
    }

    public ZooKeeper(String name) {
        this.name = name;
    }

    public ZooKeeper() {
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ZooKeeper that = (ZooKeeper) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(specialization, that.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, specialization);
    }

    @Override
    public String toString() {
        return "ZooKeeper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}