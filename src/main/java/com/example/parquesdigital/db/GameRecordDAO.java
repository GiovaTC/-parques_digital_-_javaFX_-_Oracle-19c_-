package com.example.parquesdigital.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameRecordDAO {

    // Guarda un movimiento en la tabla MOVES
    public void saveMove(int sessionId, String playerName, int moveNumber, int from, int to, int diceValue) {
        // Primero aseguro que el jugador exista
        savePlayerIfNotExists(playerName);

        String sql = "INSERT INTO MOVES (SESSION_ID, PLAYER_ID, MOVE_NUMBER, FROM_POS, TO_POS, DICE_VALUE) " +
                "VALUES (?, (SELECT PLAYER_ID FROM PLAYERS WHERE USERNAME = ?), ?, ?, ?, ?)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sessionId);
            ps.setString(2, playerName);
            ps.setInt(3, moveNumber);
            ps.setInt(4, from);
            ps.setInt(5, to);
            ps.setInt(6, diceValue);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Inserta al jugador solo si no existe en la tabla PLAYERS
    public void savePlayerIfNotExists(String playerName) {
        String sql = "MERGE INTO PLAYERS p " +
                "USING (SELECT ? AS USERNAME FROM dual) src " +
                "ON (p.USERNAME = src.USERNAME) " +
                "WHEN NOT MATCHED THEN " +
                "INSERT (PLAYER_ID, USERNAME) VALUES (SEQ_PLAYERS.NEXTVAL, src.USERNAME)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, playerName);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
