package repositories;

import models.Animal;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepository {
    // Данные для подключения (потом вынесем в application.properties)
    private final String url = "jdbc:postgresql://localhost:5432/OOP";
    private final String user = "localhost";
    private final String password = "122211Abalichudeda";

    public List<Animal> findAll() {
        List<Animal> animals = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM animals");
            while (rs.next()) {
                // Предполагаем, что у Animal есть конструктор (String name, String species, int age)
                Animal a = new Animal(rs.getString("name"), rs.getString("species"), rs.getInt("age"));
                a.setId(rs.getInt("id"));
                animals.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public Animal save(Animal animal) {
        String sql = "INSERT INTO animals (name, species, age) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, animal.getName());
            st.setString(2, animal.getSpecies());
            st.setInt(3, animal.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM animals WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Animal with id " + id + " was deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
