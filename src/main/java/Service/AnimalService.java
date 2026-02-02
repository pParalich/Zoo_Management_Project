package Service;

import models.Animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AnimalRepository;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository; // Внедряем "руки", которые работают с БД

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll(); // Теперь возвращаем не пустой список, а данные из БД
    }

    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public void deleteAnimal(int id) {
        animalRepository.deleteById(id);
    }

    public Animal assignKeeperToAnimal(int animalId, int keeperId) {
        return null;
    }
}