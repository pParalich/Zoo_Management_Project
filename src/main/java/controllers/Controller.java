package controllers;

import Service.AnimalService;
import models.Animal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
class Animal_Controller {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public String printMessage(){
        return "Hello world";
    }

    // Получить всех животных
    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    // Добавить новое животное
    @PostMapping
    public Animal createAnimal(@Validated @RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }

    // Удалить животное
    @DeleteMapping ("/{id}")
    public void deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
    }

    // Назначить смотрителя (ZooKeeper) животному
    // Аналог registerParticipant из твоего примера
    @PostMapping("/{animalId}/assign-keeper/{keeperId}")
    public Animal assignKeeper(@PathVariable int animalId, @PathVariable int keeperId) {
        return animalService.assignKeeperToAnimal(animalId, keeperId);
    }
}
