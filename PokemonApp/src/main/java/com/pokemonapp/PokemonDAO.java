package com.pokemonapp;

import com.pokemonapp.entities.Pokemon;
import com.pokemonapp.entities.Type;
import com.pokemonapp.entities.Move;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {
    public void addPokemon(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO pokemon (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pokemon.getName());
            stmt.executeUpdate();
        }
    }

    public Pokemon getPokemon(int id) throws SQLException {
        String sql = "SELECT * FROM pokemon WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pokemon pokemon = new Pokemon(rs.getInt("id"), rs.getString("name"));
                pokemon.setTypes(getTypesForPokemon(id));
                pokemon.setMoves(getMovesForPokemon(id));
                return pokemon;
            }
        }
        return null;
    }

    private List<Type> getTypesForPokemon(int pokemonId) throws SQLException {
        List<Type> types = new ArrayList<>();
        String sql = "SELECT t.id, t.name FROM type t " +
                     "JOIN pokemon_type pt ON t.id = pt.type_id " +
                     "WHERE pt.pokemon_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pokemonId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                types.add(new Type(rs.getInt("id"), rs.getString("name")));
            }
        }
        return types;
    }

    private List<Move> getMovesForPokemon(int pokemonId) throws SQLException {
        List<Move> moves = new ArrayList<>();
        String sql = "SELECT m.id, m.name FROM move m " +
                     "JOIN pokemon_move pm ON m.id = pm.move_id " +
                     "WHERE pm.pokemon_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pokemonId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                moves.add(new Move(rs.getInt("id"), rs.getString("name")));
            }
        }
        return moves;
    }

    void agregarPokemon(Pokemon pokemon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Pokemon obtenerPokemon(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}