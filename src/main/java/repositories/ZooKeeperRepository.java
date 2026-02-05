package repositories;

import models.ZooKeeper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ZooKeeperRepository {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "122211Abalichudeda";

    public List<ZooKeeper> findAll() {
        List<ZooKeeper> keepers = new ArrayList<>();
        String sql = "SELECT * FROM zookeepers";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ZooKeeper zk = new ZooKeeper();
                zk.setId(rs.getInt("id"));
                zk.setName(rs.getString("name"));
                zk.setSpecialization(rs.getString("specialization"));
                keepers.add(zk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keepers;
    }

    public ZooKeeper save(ZooKeeper keeper) {
        String sql = "INSERT INTO zookeepers (name, specialization) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, keeper.getName());
            st.setString(2, keeper.getSpecialization());
            st.executeUpdate();

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    keeper.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keeper;
    }
    public ZooKeeper update(int id, ZooKeeper keeper) {
        String sql = "UPDATE zookeepers SET name = ?, specialization = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, keeper.getName());
            st.setString(2, keeper.getSpecialization());
            st.setInt(3, id);

            int rows = st.executeUpdate();
            if (rows == 0) {
                System.out.println("ZooKeeper with id " + id + " not found for update.");
            } else {
                keeper.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keeper;
    }
    public void deleteById(int id) {
        String sql = "DELETE FROM zookeepers WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("ZooKeeper with id " + id + " was deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}