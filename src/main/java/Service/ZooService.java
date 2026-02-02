package Service;

import models.Animal;
import models.ZooKeeper;

import java.util.List;

public class ZooService {
    public List<Animal> getAllAnimals() {
        return null;
    }

    public Animal addAnimal(Animal animal) {
        return animal;
    }

    public List<ZooKeeper> getAllKeepers() {
        return List.of();
    }

    public void assignKeeperToAnimal(int animalId, int keeperId) {
    }
}
