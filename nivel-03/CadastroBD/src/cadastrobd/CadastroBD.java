package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import cadastrobd.model.util.ConectorBD;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CadastroBD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConectorBD conectorBD = new ConectorBD();
        
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conectorBD);

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    incluirPessoa(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                    break;

                case 2:
                    alterarPessoa(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                    break;

                case 3:
                    excluirPessoa(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                    break;

                case 4:
                    exibirPessoaPorId(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                    break;

                case 5:
                    exibirTodasPessoas(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                    break;

                case 0:
                    System.out.println("Encerrando o programa.");
                    break;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }

        } while (opcao != 0);
        
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Selecione uma opcao:");
        System.out.println("1. Incluir");
        System.out.println("2. Alterar");
        System.out.println("3. Excluir");
        System.out.println("4. Exibir pelo ID");
        System.out.println("5. Exibir todos");
        System.out.println("0. Sair");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    private static void incluirPessoa(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        String tipo = scanner.nextLine().toUpperCase();

        try {
            if (tipo.equalsIgnoreCase("F")) {
                PessoaFisica pessoaFisica = criarPessoaFisica(scanner);
                pessoaFisicaDAO.incluir(pessoaFisica);
                System.out.println("Pessoa Fisica incluida com sucesso!");
            } else if (tipo.equalsIgnoreCase("J")) {
                PessoaJuridica pessoaJuridica = criarPessoaJuridica(scanner);
                pessoaJuridicaDAO.incluir(pessoaJuridica);
                System.out.println("Pessoa Juridica incluida com sucesso!");
            } else {
                System.out.println("Opcao invalida.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao incluir pessoa: " + e.getMessage());
        }
    }

    private static void alterarPessoa(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        String tipoAlterar = scanner.nextLine().toUpperCase();

        System.out.print("Informe o ID da pessoa: ");
        int idAlterar = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        try {
            if (tipoAlterar.equalsIgnoreCase("F")) {
                PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(idAlterar);
                if (pessoaFisica != null) {
                    exibirDadosAtuais(pessoaFisica);
                    atualizarPessoaFisica(scanner, pessoaFisica);
                    pessoaFisicaDAO.alterar(pessoaFisica);
                    System.out.println("Pessoa Fisica alterada com sucesso!");
                } else {
                    System.out.println("Pessoa Fisica nao encontrada.");
                }
            } else if (tipoAlterar.equalsIgnoreCase("J")) {
                PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(idAlterar);
                if (pessoaJuridica != null) {
                    exibirDadosAtuais(pessoaJuridica);
                    atualizarPessoaJuridica(scanner, pessoaJuridica);
                    pessoaJuridicaDAO.alterar(pessoaJuridica);
                    System.out.println("Pessoa Juridica alterada com sucesso!");
                } else {
                    System.out.println("Pessoa Juridica nao encontrada.");
                }
            } else {
                System.out.println("Opcao invalida.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao alterar pessoa: " + e.getMessage());
        }
    }

    private static void excluirPessoa(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        String tipoExcluir = scanner.nextLine().toUpperCase();

        System.out.print("Informe o ID da pessoa: ");
        int idExcluir = scanner.nextInt();
        scanner.nextLine();

        try {
            if (tipoExcluir.equalsIgnoreCase("F")) {
                PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(idExcluir);
                if (pessoaFisica != null) {
                    pessoaFisicaDAO.excluir(pessoaFisica);
                    System.out.println("Pessoa Fisica excluida com sucesso!");
                } else {
                    System.out.println("Pessoa Fisica nao encontrada.");
                }
            } else if (tipoExcluir.equalsIgnoreCase("J")) {
                PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(idExcluir);
                if (pessoaJuridica != null) {
                    pessoaJuridicaDAO.excluir(pessoaJuridica);
                    System.out.println("Pessoa Juridica excluida com sucesso!");
                } else {
                    System.out.println("Pessoa Juridica nao encontrada.");
                }
            } else {
                System.out.println("Opcao invalida.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir pessoa: " + e.getMessage());
        }
    }

    private static void exibirPessoaPorId(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        String tipoExibirId = scanner.nextLine().toUpperCase();

        System.out.print("Informe o ID da pessoa: ");
        int idExibirId = scanner.nextInt();
        scanner.nextLine();

        try {
            if (tipoExibirId.equalsIgnoreCase("F")) {
                PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(idExibirId);
                if (pessoaFisica != null) {
                    pessoaFisica.exibir();
                } else {
                    System.out.println("Pessoa Fisica nao encontrada.");
                }
            } else if (tipoExibirId.equalsIgnoreCase("J")) {
                PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(idExibirId);
                if (pessoaJuridica != null) {
                    pessoaJuridica.exibir();
                } else {
                    System.out.println("Pessoa Juridica nao encontrada.");
                }
            } else {
                System.out.println("Opcao invalida.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao exibir pessoa: " + e.getMessage());
        }
    }

    private static void exibirTodasPessoas(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) {
        System.out.println("Escolha o tipo (F - Fisica, J - Juridica):");
        String tipoExibirTodos = scanner.nextLine().toUpperCase();

        try {
            if (tipoExibirTodos.equalsIgnoreCase("F")) {
                System.out.println("Exibindo dados de Pessoa Fisica...");
                List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
                for (PessoaFisica pf : pessoasFisicas) {
                    pf.exibir();
                }
            } else if (tipoExibirTodos.equalsIgnoreCase("J")) {
                System.out.println("Exibindo dados de Pessoa Juridica...");
                List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
                for (PessoaJuridica pj : pessoasJuridicas) {
                    pj.exibir();
                }
            } else {
                System.out.println("Opcao invalida.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao exibir todas as pessoas: " + e.getMessage());
        }
    }

    private static PessoaFisica criarPessoaFisica(Scanner scanner) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.print("Nome: ");
        pessoaFisica.setNome(scanner.nextLine());
        System.out.print("CPF: ");
        pessoaFisica.setCpf(scanner.nextLine());
        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        pessoaFisica.setDataNascimento(scanner.nextLine());
        return pessoaFisica;
    }

    private static PessoaJuridica criarPessoaJuridica(Scanner scanner) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.print("Nome Fantasia: ");
        pessoaJuridica.setNome(scanner.nextLine());
        System.out.print("CNPJ: ");
        pessoaJuridica.setCnpj(scanner.nextLine());
        System.out.print("Data de Criacao (dd/mm/aaaa): ");
        pessoaJuridica.setDataCriacao(scanner.nextLine());
        return pessoaJuridica;
    }

    private static void atualizarPessoaFisica(Scanner scanner, PessoaFisica pessoaFisica) {
        System.out.print("Novo Nome: ");
        pessoaFisica.setNome(scanner.nextLine());
        System.out.print("Novo CPF: ");
        pessoaFisica.setCpf(scanner.nextLine());
        System.out.print("Nova Data de Nascimento (dd/mm/aaaa): ");
        pessoaFisica.setDataNascimento(scanner.nextLine());
    }

    private static void atualizarPessoaJuridica(Scanner scanner, PessoaJuridica pessoaJuridica) {
        System.out.print("Novo Nome Fantasia: ");
        pessoaJuridica.setNome(scanner.nextLine());
        System.out.print("Novo CNPJ: ");
        pessoaJuridica.setCnpj(scanner.nextLine());
        System.out.print("Nova Data de Criacao (dd/mm/aaaa): ");
        pessoaJuridica.setDataCriacao(scanner.nextLine());
    }

    private static void exibirDadosAtuais(PessoaFisica pessoaFisica) {
        System.out.println("Dados atuais de Pessoa Fisica:");
        pessoaFisica.exibir();
    }

    private static void exibirDadosAtuais(PessoaJuridica pessoaJuridica) {
        System.out.println("Dados atuais de Pessoa Juridica:");
        pessoaJuridica.exibir();
    }
}
