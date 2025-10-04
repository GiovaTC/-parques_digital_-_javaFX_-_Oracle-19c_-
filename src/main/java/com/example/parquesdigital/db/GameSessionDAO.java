package com.example.parquesdigital.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para manejar las sesiones de juego en la base de datos
 */
public class GameSessionDAO {

    /**
     * Crea una nueva sesión en la tabla GAME_SESSIONS
     * @return el ID de la sesión recién creada
     */
    public int createSession() {
        String sql = "INSERT INTO GAME_SESSIONS (SESSION_ID, START_TIME) VALUES (SEQ_GAME_SESSIONS.NEXTVAL, CURRENT_TIMESTAMP)";
        String sqlReturn = "SELECT SEQ_GAME_SESSIONS.CURRVAL FROM dual";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             PreparedStatement psReturn = conn.prepareStatement(sqlReturn)) {

            // Inserta la sesión
            ps.executeUpdate();

            // Recupera el ID generado
            try (ResultSet rs = psReturn.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Si falla
    }

    /**
     * Finaliza una sesión (marca END_TIME)
     * @param sessionId ID de la sesión a cerrar
     */
    public void endSession(int sessionId) {
        String sql = "UPDATE GAME_SESSIONS SET END_TIME = CURRENT_TIMESTAMP WHERE SESSION_ID = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sessionId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
