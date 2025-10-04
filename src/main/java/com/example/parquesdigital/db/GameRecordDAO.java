package com.example.parquesdigital.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class GameRecordDAO {

    public void saveMove(int sessionId, String playerName, int moveNumber, int from, int to, int diceValue) {
        String sql = "INSERT INTO MOVES (SESSION_ID, PLAYER_ID, MOVE_NUMBER, FROM_POS, TO_POS, DICE_VALUE)" +
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
}
