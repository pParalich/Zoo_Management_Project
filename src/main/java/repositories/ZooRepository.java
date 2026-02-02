package repositories;

import models.Zoo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ZooRepository {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "122211Abalichudeda";

    public List<Zoo> findAll() {
        List<Zoo> zoos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM zoos");

            while (rs.next()) {
                Zoo z = new Zoo();
                z.setId(rs.getInt("id"));
                z.setName(rs.getString("name"));
                z.setLocation(rs.getString("location"));
                zoos.add(z);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zoos;
    }

    public Zoo save(Zoo zoo) {
        String sql = "INSERT INTO zoos (name, location) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, zoo.getName());
            st.setString(2, zoo.getLocation());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zoo;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM zoos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Zoo with id " + id + " was deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Zoo> findById(int id) {
        String sql = "SELECT * FROM zoos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Zoo z = new Zoo();
                z.setId(rs.getInt("id"));
                z.setName(rs.getString("name"));
                z.setLocation(rs.getString("location"));
                return Optional.of(z);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Zoo update(int id, Zoo zoo) {
        String sql = "UPDATE zoos SET name = ?, location = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, zoo.getName());
            st.setString(2, zoo.getLocation());
            st.setInt(3, id);

            st.executeUpdate();
            zoo.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zoo;
    }

}
