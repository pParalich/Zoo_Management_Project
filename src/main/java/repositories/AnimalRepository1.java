package repositories;

import models.Animal;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepository1 {

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "122211Abalichudeda";

    public List<Animal> findAll() {
        String sql = "SELECT id, name, species, age, zoo_id, keeper_id FROM animals ORDER BY id";
        List<Animal> animals = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                animals.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animals;
    }

    public Optional<Animal> findById(int id) {
        String sql = "SELECT id, name, species, age, zoo_id, keeper_id FROM animals WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Animal save(Animal animal) {
        String sql = "INSERT INTO animals (name, species, age, zoo_id, keeper_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, animal.getName());
            st.setString(2, animal.getSpecies());
            st.setInt(3, animal.getAge());
            st.setInt(4, animal.getZooId());

            if (animal.getKeeperId() == null) st.setNull(5, Types.INTEGER);
            else st.setInt(5, animal.getKeeperId());

            st.executeUpdate();

            // вернуть сгенерированный id
            try (ResultSet keys = st.getGeneratedKeys()) {
                if (keys.next()) {
                    animal.setId(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

    public Optional<Animal> update(int id, Animal animal) {
        String sql = "UPDATE animals SET name = ?, species = ?, age = ?, zoo_id = ?, keeper_id = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, animal.getName());
            st.setString(2, animal.getSpecies());
            st.setInt(3, animal.getAge());
            st.setInt(4, animal.getZooId());

            if (animal.getKeeperId() == null) st.setNull(5, Types.INTEGER);
            else st.setInt(5, animal.getKeeperId());

            st.setInt(6, id);

            int rows = st.executeUpdate();
            if (rows == 0) return Optional.empty();

            animal.setId(id);
            return Optional.of(animal);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Animal> updateKeeper(int animalId, Integer keeperId) {
        String sql = "UPDATE animals SET keeper_id = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            if (keeperId == null) st.setNull(1, Types.INTEGER);
            else st.setInt(1, keeperId);

            st.setInt(2, animalId);

            int rows = st.executeUpdate();
            if (rows == 0) return Optional.empty();

            return findById(animalId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM animals WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);
            int rows = st.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Animal mapRow(ResultSet rs) throws SQLException {
        Animal a = new Animal(); // нужен пустой конструктор в Animal!

        a.setId(rs.getInt("id"));
        a.setName(rs.getString("name"));
        a.setSpecies(rs.getString("species"));
        a.setAge(rs.getInt("age"));
        a.setZooId(rs.getInt("zoo_id"));

        int kid = rs.getInt("keeper_id");
        a.setKeeperId(rs.wasNull() ? null : kid);

        return a;
    }
}
