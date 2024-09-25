/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;

//Imports 
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Rian Joseph
 */
public class CadastroPOO {

    /**
     * @param args the command line arguments
     */
    
    // Metodo Main
    public static void main(String[] args){
        // Repositorio de pessoas fisicas repo_01
        PessoaFisicaRepo repo_01 = new PessoaFisicaRepo();
        
        // Add 4 pesssoas fisicas utilizando o construtor completo
        repo_01.inserir(new PessoaFisica(1, "Joao Silva", "123.456.789.10", 38));
        repo_01.inserir(new PessoaFisica(2, "Maria Alves", "453.336.459.20", 45));
        repo_01.inserir(new PessoaFisica(3, "Marta Rosa", "673.357.789.10", 26));
        repo_01.inserir(new PessoaFisica(4, "Caio Artur", "231.456.689.23", 24));
        
        // Persistindo os dados do repo_01 em um arqv
        String arquivoFisicas = "pessoas_fisicas.dat";
        try {
            repo_01.persistir(arquivoFisicas);
            System.out.println("Dados de Pessoa fisica armazenados.");
        } catch (IOException e) {
            System.out.println("Aconteceu um erro ao persistir dados de pessoas fisicas: " + e.getMessage());
        }
        
        //Repositorio de pessoas fisicas repo_02
        PessoaFisicaRepo repo_02 = new PessoaFisicaRepo();
        try {
            repo_02.recuperar(arquivoFisicas);
            System.out.println("Dados de Pessoa fisicas Recuperados.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Aconteceu um erro ao recuperar dados de pessoas fisicas: " + e.getMessage());
        }
        
        // Exibindo os dados 
        ArrayList<PessoaFisica> pessoasFisicas = repo_02.obterTodos();
        for(PessoaFisica pessoa : pessoasFisicas)
        {
            pessoa.exibir();
        }
        
        // Repositorio de pessoas juridicas repo_03
        PessoaJuridicaRepo repo_03 = new PessoaJuridicaRepo();
        
        // Adicionando 2 pessoas utilizando o construtor completo
        repo_03.inserir(new PessoaJuridica(1, "Empresa Primeira", "12.345.678/0001-99"));
        repo_03.inserir(new PessoaJuridica(2, "Empresa Segunda", "12.345.678/0002-88"));
        
        // Persistindo os dados do repo_03 em um arqv
        String arquivoJuridicas = "pessoas_juridicas.dat";
        try {
            repo_03.persistir(arquivoJuridicas);
            System.out.println("Dados de pessoas juridicas armazenados.");
        } catch (IOException e) {
            System.out.println("Aconteceu um erro ao armazenar dados de pessoas juridicas: " + e.getMessage());
        }
        
        // Repositorio de pessoas juridicas repo_04
        PessoaJuridicaRepo repo_04 = new PessoaJuridicaRepo();
        try {
            repo_04.recuperar(arquivoJuridicas);
            System.out.println("Dados de pessoas juridicas recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Aconteceu um erro ao recuperar dados de pessoas juridicas: " + e.getMessage());
        }
        
        // Exibindo os dados
        ArrayList<PessoaJuridica> pessoasJuridicas = repo_04.obterTodos();
        for (PessoaJuridica pessoa : pessoasJuridicas) {
            pessoa.exibir();
        }
    }
    
}
//