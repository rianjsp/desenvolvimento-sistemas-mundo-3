package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;

import java.sql.SQLException;

public class CadastroBDTeste {
    public static void main(String[] args) {
        try {
            // Instanciar e persistir uma pessoa fisica
            PessoaFisica pessoaFisica = new PessoaFisica(0, "Maria Silva", "Rua A", "Cidade A", "Estado A", "123456789", "maria@exemplo.com", "123.456.789-00");
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            pessoaFisicaDAO.incluir(pessoaFisica);
            System.out.println("Pessoa fisica incluida com sucesso.");

            // Alterar os dados da pessoa fisica
            pessoaFisica.setNome("Maria da Silva");
            pessoaFisicaDAO.alterar(pessoaFisica);
            System.out.println("Pessoa fisica alterada com sucesso.");

            // Consultar todas as pessoas fisicas e listar no console
            System.out.println("Lista de pessoas fisicas:");
            for (PessoaFisica pf : pessoaFisicaDAO.getPessoas()) {
                pf.exibir();
            }

            // Excluir a pessoa fisica
            pessoaFisicaDAO.excluir(pessoaFisica.getId());
            System.out.println("Pessoa fisica excluida com sucesso.");

            // Instanciar e persistir uma pessoa juridica
            PessoaJuridica pessoaJuridica = new PessoaJuridica(0, "Empresa Exemplo", "Av. B", "Cidade B", "Estado B", "987654321", "empresa@exemplo.com", "12.345.678/0001-95");
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            pessoaJuridicaDAO.incluir(pessoaJuridica);
            System.out.println("Pessoa juridica incluida com sucesso.");

            // Alterar os dados da pessoa juridica
            pessoaJuridica.setNome("Empresa Exemplo Ltda");
            pessoaJuridicaDAO.alterar(pessoaJuridica);
            System.out.println("Pessoa juridica alterada com sucesso.");

            // Consultar todas as pessoas juridicas e listar no console
            System.out.println("Lista de pessoas juridicas:");
            for (PessoaJuridica pj : pessoaJuridicaDAO.getPessoas()) {
                pj.exibir();
            }

            // Excluir a pessoa juridica
            pessoaJuridicaDAO.excluir(pessoaJuridica.getId());
            System.out.println("Pessoa juridica excluida com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
