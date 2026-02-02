package controllers;

import Service.ZooService;
import models.Animal;
import models.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/zoo")
public class ZooController {

    @Autowired
    private ZooService zooService;

    // Получить всех животных в зоопарке
    @GetMapping("/animals")
    public List<Animal> getAllAnimals() {
        return zooService.getAllAnimals();
    }

    // Добавить животное в зоопарк
    @PostMapping("/animals")
    public Animal addAnimal(@RequestBody Animal animal) {
        return zooService.addAnimal(animal);
    }

    // Получить всех смотрителей
    @GetMapping("/keepers")
    public List<ZooKeeper> getAllKeepers() {
        return zooService.getAllKeepers();
    }

    // Назначить конкретного смотрителя конкретному животному
    @PostMapping("/assign/{animalId}/{keeperId}")
    public String assign(@PathVariable int animalId, @PathVariable int keeperId) {
        zooService.assignKeeperToAnimal(animalId, keeperId);
        return "Success: Keeper " + keeperId + " now looks after animal " + animalId;
    }
}