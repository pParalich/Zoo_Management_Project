package controllers;

import Service.ZooService;
import models.Animal;
import models.Zoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zoos")
public class ZooController {

    @Autowired
    private ZooService zooService;

    @GetMapping
    public List<Zoo> getAllZoos() {
        return zooService.getAllZoos();
    }

    @GetMapping("/{id}")
    public Zoo getZooById(@PathVariable int id) {
        return zooService.getZooById(id);
    }

    @PostMapping
    public Zoo createZoo(@RequestBody Zoo zoo) {
        return zooService.createZoo(zoo);
    }

    @PutMapping("/{id}")
    public Zoo updateZoo(@PathVariable int id, @RequestBody Zoo zoo) {
        return zooService.updateZoo(id, zoo);
    }

    @DeleteMapping("/{id}")
    public void deleteZoo(@PathVariable int id) {
        zooService.deleteZoo(id);
    }

}
