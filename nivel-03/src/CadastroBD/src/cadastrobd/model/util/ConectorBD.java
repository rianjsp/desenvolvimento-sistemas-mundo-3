package cadastrobd.model.util;

import java.sql.*;

public class ConectorBD {
    // Função para obter a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost\\MSSQLSERVER01;databaseName=Loja;encrypt=false;trustServerCertificate=true";
        String usuario = "loja";
        String senha = "loja";
        return DriverManager.getConnection(url, usuario, senha);
    }

    // Função para obter um PreparedStatement
    public static PreparedStatement getPrepared(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    // Funções para fechar recursos
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
