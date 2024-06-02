package com.pokemonapp;

import com.pokemonapp.entities.Entrenador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntrenadorDAO {

    private Connection connection;

    public EntrenadorDAO(Connection connection) {
        this.connection = connection;
    }

    EntrenadorDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void agregarEntrenador(Entrenador entrenador) throws SQLException {
        String query = "INSERT INTO Entrenador (nombre) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entrenador.getNombre());
            statement.executeUpdate();
        }
    }

    public Entrenador obtenerEntrenador(int id) throws SQLException {
        String query = "SELECT * FROM Entrenador WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    return new Entrenador(id, nombre);
                }
            }
        }
        return null;
    }
}