package controllers;

import Service.AnimalService;
import models.Animal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/animals")
class Animal_Controller {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @PostMapping
    public Animal createAnimal(@Validated @RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }

    @DeleteMapping ("/{id}")
    public void deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
    }

    @PostMapping("/{animalId}/assign-keeper/{keeperId}")
    public Animal assignKeeper(@PathVariable int animalId, @PathVariable int keeperId) {
        return animalService.assignKeeperToAnimal(animalId, keeperId);
    }
}
