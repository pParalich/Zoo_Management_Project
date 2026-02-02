package Service;

import models.Animal;
import models.Zoo;
import repositories.AnimalRepository1;
import repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZooService {

    @Autowired
    private ZooRepository zooRepository;

    @Autowired
    private AnimalRepository1 animalRepository;

    public List<Zoo> getAllZoos() {
        return zooRepository.findAll();
    }

    public Zoo getZooById(int id) {
        Optional<Zoo> zooOpt = zooRepository.findById(id);
        return zooOpt.orElseThrow(() -> new RuntimeException("Zoo not found with id: " + id));
    }

    public Zoo createZoo(Zoo zoo) {
        if (zoo.getName() == null || zoo.getName().isBlank()) {
            throw new IllegalArgumentException("Zoo name cannot be empty");
        }
        if (zoo.getLocation() == null || zoo.getLocation().isBlank()) {
            throw new IllegalArgumentException("Zoo location cannot be empty");
        }
        return zooRepository.save(zoo);
    }

    public Zoo updateZoo(int id, Zoo zoo) {
        getZooById(id);

        if (zoo.getName() == null || zoo.getName().isBlank()) {
            throw new IllegalArgumentException("Zoo name cannot be empty");
        }
        if (zoo.getLocation() == null || zoo.getLocation().isBlank()) {
            throw new IllegalArgumentException("Zoo location cannot be empty");
        }

        return zooRepository.update(id, zoo);
    }

    public void deleteZoo(int id) {
        getZooById(id);
        zooRepository.deleteById(id);
    }
}
