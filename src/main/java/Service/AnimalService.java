package Service;

import models.Animal;

import java.util.List;

public class AnimalService {
    public List<Animal> getAllAnimals() {
        return List.of();
    }

    public Animal saveAnimal(Animal animal) {
        return animal;
    }

    public void deleteAnimal(int id) {
    }

    public Animal assignKeeperToAnimal(int animalId, int keeperId) {
        return null;
    }
}
