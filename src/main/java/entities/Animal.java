package entities;


import java.util.Objects;

public class Animal {
    private int id;
    private String name;
    private String species;

    public Animal(int id) {
        this.id = id;
    }
    public Animal(String name) {
        this.name = name;
    }

    public Animal() {
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && Objects.equals(name, animal.name) && Objects.equals(species, animal.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                '}';
    }
}