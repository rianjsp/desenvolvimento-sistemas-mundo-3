package cadastrobd.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import cadastrobd.model.util.ConectorBD;

public class PessoaJuridicaDAO {
    private ConectorBD conectorBD;

    // Construtor com ConectorBD
    public PessoaJuridicaDAO(ConectorBD conectorBD) {
        this.conectorBD = conectorBD;
    }

    public PessoaJuridica getPessoa(int id) throws SQLException {
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaJuridica ON Pessoa.id = PessoaJuridica.id WHERE Pessoa.id = ?";
        try (Connection conn = conectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PessoaJuridica(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("dataAbertura")
                    );
                }
            }
        }
        return null;
    }

    public List<PessoaJuridica> getPessoas() throws SQLException {
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaJuridica ON Pessoa.id = PessoaJuridica.id";
        List<PessoaJuridica> pessoas = new ArrayList<>();
        try (Connection conn = conectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pessoas.add(new PessoaJuridica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj"),
                    rs.getString("dataAbertura")
                ));
            }
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pessoa) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (id, cnpj, dataAbertura) VALUES (?, ?, ?)";
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
                    try (PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica)) {
                        stmtPessoaJuridica.setInt(1, idGerado);
                        stmtPessoaJuridica.setString(2, pessoa.getCnpj());
                        stmtPessoaJuridica.setString(3, pessoa.getDataAbertura());
                        stmtPessoaJuridica.executeUpdate();
                    }
                }
            }
        }
    }

    public void alterar(PessoaJuridica pessoa) throws SQLException {
        String sqlPessoa = "UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj=?, dataAbertura=? WHERE id=?";
        try (Connection conn = conectorBD.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica)) {
            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setString(2, pessoa.getLogradouro());
            stmtPessoa.setString(3, pessoa.getCidade());
            stmtPessoa.setString(4, pessoa.getEstado());
            stmtPessoa.setString(5, pessoa.getTelefone());
            stmtPessoa.setString(6, pessoa.getEmail());
            stmtPessoa.setInt(7, pessoa.getId());
            stmtPessoa.executeUpdate();

            stmtPessoaJuridica.setString(1, pessoa.getCnpj());
            stmtPessoaJuridica.setString(2, pessoa.getDataAbertura());
            stmtPessoaJuridica.setInt(3, pessoa.getId());
            stmtPessoaJuridica.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE id=?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id=?";
        try (Connection conn = conectorBD.getConnection();
             PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {
            stmtPessoaJuridica.setInt(1, id);
            stmtPessoaJuridica.executeUpdate();

            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();
        }
    }
}
