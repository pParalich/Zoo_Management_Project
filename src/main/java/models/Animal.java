package models;

import java.util.Objects;

public class Animal {
    private int id;
    private String name;
    private String species;
    private int age;

    private Integer keeperId; // nullable
    private int zooId;         // not null

    public Animal() {}

    public Animal(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }


    public Animal(String name, String species, int age, int zooId) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.zooId = zooId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Integer getKeeperId() { return keeperId; }
    public void setKeeperId(Integer keeperId) { this.keeperId = keeperId; }

    public int getZooId() { return zooId; }
    public void setZooId(int zooId) { this.zooId = zooId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                age == animal.age &&
                zooId == animal.zooId &&
                Objects.equals(name, animal.name) &&
                Objects.equals(species, animal.species) &&
                Objects.equals(keeperId, animal.keeperId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species, age, zooId, keeperId);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", zooId=" + zooId +
                ", keeperId=" + keeperId +
                '}';
    }
}
