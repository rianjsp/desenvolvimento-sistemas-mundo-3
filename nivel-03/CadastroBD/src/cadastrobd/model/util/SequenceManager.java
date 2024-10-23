package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    // Função para ter o próximo valor da Sequência
    public static int getValue(String sequenceName) throws SQLException {
        String sql = "SELECT nextval(?)"; 
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, sequenceName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Sequência não encontrada: " + sequenceName);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao acessar a sequência: " + e.getMessage(), e);
        }
    }
}
