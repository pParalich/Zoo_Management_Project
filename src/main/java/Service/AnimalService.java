package Service;

import models.Animal;
import models.ZooKeeper;
import repositories.AnimalRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository1 animalRepository;

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }


    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }


    public void deleteAnimal(int id) {
        animalRepository.deleteById(id);
    }


    public Animal assignKeeperToAnimal(int animalId, int keeperId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("..."));
        return animalRepository.save(animal);
    }
}