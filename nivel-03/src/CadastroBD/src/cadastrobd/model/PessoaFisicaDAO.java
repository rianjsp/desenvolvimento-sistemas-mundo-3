package cadastrobd.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import cadastrobd.model.util.ConectorBD;

public class PessoaFisicaDAO {
    private final ConectorBD conectorBD;

    public PessoaFisicaDAO(ConectorBD conectorBD) {
        this.conectorBD = conectorBD;
    }

    // Retorna pessoa física por ID
    public PessoaFisica getPessoa(int id) throws SQLException {
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.id = PessoaFisica.id WHERE Pessoa.id = ?";
        try (Connection conn = conectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PessoaFisica(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                    );
                }
            }
        }
        return null;
    }

    // Retorna lista de pessoas físicas
    public List<PessoaFisica> getPessoas() throws SQLException {
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.id = PessoaFisica.id";
        List<PessoaFisica> pessoas = new ArrayList<>();
        try (Connection conn = conectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pessoas.add(new PessoaFisica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                ));
            }
        }
        return pessoas;
    }

    // Inclui nova pessoa física
    public void incluir(PessoaFisica pessoa) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";
        try (Connection conn = conectorBD.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)) {
            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setString(2, pessoa.getLogradouro());
            stmtPessoa.setString(3, pessoa.getCidade());
            stmtPessoa.setString(4, pessoa.getEstado());
            stmtPessoa.setString(5, pessoa.getTelefone());
            stmtPessoa.setString(6, pessoa.getEmail());
            stmtPessoa.executeUpdate();

            try (ResultSet rs = stmtPessoa.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    try (PreparedStatement stmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica)) {
                        stmtPessoaFisica.setInt(1, idGerado);
                        stmtPessoaFisica.setString(2, pessoa.getCpf());
                        stmtPessoaFisica.executeUpdate();
                    }
                }
            }
        }
    }

    // Altera pessoa física
    public void alterar(PessoaFisica pessoa) throws SQLException {
        String sqlPessoa = "UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf=? WHERE id=?";
        try (Connection conn = conectorBD.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica)) {
            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setString(2, pessoa.getLogradouro());
            stmtPessoa.setString(3, pessoa.getCidade());
            stmtPessoa.setString(4, pessoa.getEstado());
            stmtPessoa.setString(5, pessoa.getTelefone());
            stmtPessoa.setString(6, pessoa.getEmail());
            stmtPessoa.setInt(7, pessoa.getId());
            stmtPessoa.executeUpdate();

            stmtPessoaFisica.setString(1, pessoa.getCpf());
            stmtPessoaFisica.setInt(2, pessoa.getId());
            stmtPessoaFisica.executeUpdate();
        }
    }

    // Exclui pessoa física
    public void excluir(int id) throws SQLException {
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE id=?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id=?";
        try (Connection conn = conectorBD.getConnection();
             PreparedStatement stmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {
            stmtPessoaFisica.setInt(1, id);
            stmtPessoaFisica.executeUpdate();

            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();
        }
    }
}
