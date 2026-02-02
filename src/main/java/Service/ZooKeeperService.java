package Service;

import models.ZooKeeper;
import repositories.ZooKeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZooKeeperService {

    @Autowired
    private ZooKeeperRepository keeperRepository;

    public List<ZooKeeper> getAllKeepers() {
        return keeperRepository.findAll();
    }

    public ZooKeeper saveKeeper(ZooKeeper keeper) {
        return keeperRepository.save(keeper);
    }
    public ZooKeeper updateKeeper(int id, ZooKeeper keeper) {
        return keeperRepository.update(id, keeper);
    }

    public void deleteKeeper(int id) {
        keeperRepository.deleteById(id);
    }

}