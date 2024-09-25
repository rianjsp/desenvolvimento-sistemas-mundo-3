package cadastropoo;

import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroPOO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;
        do {
            System.out.println("===============================");
            System.out.println("Escolha uma opcao:");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Persistir dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Finalizar programa");
            System.out.println("===============================");

            while (true) {
                try {
                    opcao = scanner.nextInt();
                    scanner.nextLine(); 
                    break; 
                } catch (InputMismatchException e) {
                    System.out.println("Insira um numero valido.");
                    scanner.nextLine(); 
                }
            }

            switch (opcao) {
                case 1: // Incluir
                    System.out.println("F - Fisica | J - Juridica):");
                    String tipoInclusao = scanner.nextLine().toUpperCase();
                    if (tipoInclusao.equals("F")) {
                        System.out.print("Digite o ID da pessoa: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        if (repoFisica.obter(id) != null) {
                            System.out.println("ID ja existe. Escolha um ID unico.");
                            break;
                        }

                        System.out.print("Digite o nome da pessoa: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o CPF da pessoa: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Digite a idade da pessoa: ");
                        int idade = scanner.nextInt();
                        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                        System.out.println("Pessoa Fisica cadastrada com sucesso!");
                    } else if (tipoInclusao.equals("J")) {
                        System.out.print("Digite o ID da pessoa: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        if (repoJuridica.obter(id) != null) {
                            System.out.println("ID ja existe. Escolha um ID unico.");
                            break;
                        }

                        System.out.print("Digite o nome da pessoa: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o CNPJ da pessoa: ");
                        String cnpj = scanner.nextLine();
                        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                        System.out.println("Pessoa Juridica cadastrada com sucesso!");
                    } else {
                        System.out.println("Tipo invalido. Escolha 'F' ou 'J'.");
                    }
                    break;

                case 2: // Alterar
                    System.out.println("F - Fisica | J - Juridica):");
                    String tipoAlteracao = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o ID: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine();
                    if (tipoAlteracao.equals("F")) {
                        PessoaFisica pessoa = repoFisica.obter(idAlterar);
                        if (pessoa != null) {
                            System.out.println("Dados atuais: " + pessoa);
                            System.out.print("Digite o novo nome: ");
                            String novoNome = scanner.nextLine();
                            System.out.print("Digite o novo CPF: ");
                            String novoCpf = scanner.nextLine();
                            System.out.print("Digite a nova idade: ");
                            int novaIdade = scanner.nextInt();
                            pessoa.setNome(novoNome);
                            pessoa.setCpf(novoCpf);
                            pessoa.setIdade(novaIdade);
                            repoFisica.alterar(pessoa);
                            System.out.println("Pessoa Fisica alterada com sucesso!");
                        } else {
                            System.out.println("Pessoa nao encontrada.");
                        }
                    } else if (tipoAlteracao.equals("J")) {
                        PessoaJuridica pessoa = repoJuridica.obter(idAlterar);
                        if (pessoa != null) {
                            System.out.println("Dados atuais: " + pessoa);
                            System.out.print("Digite o novo nome: ");
                            String novoNome = scanner.nextLine();
                            System.out.print("Digite o novo CNPJ: ");
                            String novoCnpj = scanner.nextLine();
                            pessoa.setNome(novoNome);
                            pessoa.setCnpj(novoCnpj);
                            repoJuridica.alterar(pessoa);
                            System.out.println("Pessoa Juridica alterada com sucesso!");
                        } else {
                            System.out.println("Pessoa nao encontrada.");
                        }
                    } else {
                        System.out.println("Tipo invalido. Escolha 'F' ou 'J'.");
                    }
                    break;

                case 3: // Excluir
                    System.out.println("F - Fisica | J - Juridica):");
                    String tipoExclusao = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o ID: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (tipoExclusao.equals("F")) {
                        repoFisica.excluir(idExcluir);
                        System.out.println("Pessoa Fisica excluida.");
                    } else if (tipoExclusao.equals("J")) {
                        repoJuridica.excluir(idExcluir);
                        System.out.println("Pessoa Juridica excluida.");
                    } else {
                        System.out.println("Tipo invalido. Escolha 'F' ou 'J'.");
                    }
                    break;

                case 4: // Exibir pelo ID
                    System.out.println("Escolha o tipo (F - Fisica | J - Juridica):");
                    String tipoExibir = scanner.nextLine().toUpperCase();
                    System.out.print("Digite o ID: ");
                    int idExibir = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (tipoExibir.equals("F")) {
                        PessoaFisica pessoa = repoFisica.obter(idExibir);
                        System.out.println(pessoa != null ? pessoa : "Pessoa nao encontrada.");
                    } else if (tipoExibir.equals("J")) {
                        PessoaJuridica pessoa = repoJuridica.obter(idExibir);
                        System.out.println(pessoa != null ? pessoa : "Pessoa nao encontrada.");
                    } else {
                        System.out.println("Tipo invalido. Escolha 'F' ou 'J'.");
                    }
                    break;

                case 5: // Exibir todos
                    System.out.println("Escolha o tipo (F - Fisica | J - Juridica):");
                    String tipoExibirTodos = scanner.nextLine().toUpperCase();
                    if (tipoExibirTodos.equals("F")) {
                        ArrayList<PessoaFisica> pessoasFisicas = repoFisica.obterTodos();
                        if (pessoasFisicas.isEmpty()) {
                            System.out.println("Nenhuma pessoa fisica cadastrada.");
                        } else {
                            for (PessoaFisica pessoa : pessoasFisicas) {
                                System.out.println(pessoa);
                            }
                        }
                    } else if (tipoExibirTodos.equals("J")) {
                        ArrayList<PessoaJuridica> pessoasJuridicas = repoJuridica.obterTodos();
                        if (pessoasJuridicas.isEmpty()) {
                            System.out.println("Nenhuma pessoa juridica cadastrada.");
                        } else {
                            for (PessoaJuridica pessoa : pessoasJuridicas) {
                                System.out.println(pessoa);
                            }
                        }
                    } else {
                        System.out.println("Tipo invalido. Escolha 'F' ou 'J'.");
                    }
                    break;

                case 6: // Salvar dados
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixoSalvar = scanner.nextLine();
                    try {
                        repoFisica.persistir(prefixoSalvar + ".fisica.bin");
                        repoJuridica.persistir(prefixoSalvar + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar dados: " + e.getMessage());
                    }
                    break;

                case 7: // Recuperar dados
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixoRecuperar = scanner.nextLine();
                    try {
                        repoFisica.recuperar(prefixoRecuperar + ".fisica.bin");
                        repoJuridica.recuperar(prefixoRecuperar + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao recuperar dados: " + e.getMessage());
                    }
                    break;

                case 0: // Sair
                    System.out.println("Sistema finalizado.");
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
