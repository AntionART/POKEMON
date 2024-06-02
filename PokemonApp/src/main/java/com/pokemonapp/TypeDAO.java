package com.pokemonapp;

import com.pokemonapp.entities.Type;
import com.pokemonapp.entities.Move;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDAO {
    public void addType(Type type) throws SQLException {
        String sql = "INSERT INTO type (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type.getName());
            stmt.executeUpdate();
        }
    }

    public Type getType(int id) throws SQLException {
        String sql = "SELECT * FROM type WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Type type = new Type(rs.getInt("id"), rs.getString("name"));
                type.setMoves(getMovesForType(id));
                return type;
            }
        }
        return null;
    }

    private List<Move> getMovesForType(int typeId) throws SQLException {
        List<Move> moves = new ArrayList<>();
        String sql = "SELECT m.id, m.name FROM move m " +
                     "JOIN type_move tm ON m.id = tm.move_id " +
                     "WHERE tm.type_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, typeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                moves.add(new Move(rs.getInt("id"), rs.getString("name")));
            }
        }
        return moves;
    }
}