package controllers;

import Service.ZooKeeperService;
import models.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keepers")
class ZooKeeperController {

    @Autowired
    private ZooKeeperService keeperService;

    @GetMapping
    public List<ZooKeeper> getAllKeepers() {
        return keeperService.getAllKeepers();
    }

    @PostMapping
    public ZooKeeper createKeeper(@RequestBody ZooKeeper keeper) {
        return keeperService.saveKeeper(keeper);
    }

    @PutMapping("/{id}")
    public ZooKeeper updateKeeper(@PathVariable int id, @RequestBody ZooKeeper keeper) {
        return keeperService.updateKeeper(id, keeper);
    }

    @DeleteMapping("/{id}")
    public void deleteKeeper(@PathVariable int id) {
        keeperService.deleteKeeper(id);
    }

}