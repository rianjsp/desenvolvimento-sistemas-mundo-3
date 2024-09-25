/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

// Importações
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Rian Joseph
 */
public class PessoaFisicaRepo {
    // Definindo um array privado
    private ArrayList<PessoaFisica> listaPessoaFisica = new ArrayList<>();
    
    // Metodo inserir nova pessoa
    public void inserir(PessoaFisica pessoaFisica){
        listaPessoaFisica.add(pessoaFisica);
    }
    
    // Metodo alterar uma pessoasFisica existente
    public void alterar(PessoaFisica pessoaFisica){
        for(int i = 0; i < listaPessoaFisica.size(); i++)
        {
            if(listaPessoaFisica.get(i).getId() == pessoaFisica.getId()){
                listaPessoaFisica.set(i, pessoaFisica);
                break;
            }
        }
    }
    
    // Metodo excluir pessoasFisica por id
    public void excluir(int id){
        listaPessoaFisica.removeIf(p -> p.getId() == id);
    }
    
    // Metodo obter pessoasFisica pelo id
    public PessoaFisica obter(int id){
        for(PessoaFisica p : listaPessoaFisica)
        {
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    // Metodo obter todas as pessoasFisica
    public ArrayList<PessoaFisica> obterTodos(){
        return listaPessoaFisica;
    }
    
    // Metodo para persistencia de dados em arquivos
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream
        (new FileOutputStream(nomeArquivo))) {
            oos.writeObject(listaPessoaFisica);
        }
    }
    
    // Metodo para recuperar os dados
    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream
        (new FileInputStream(nomeArquivo))) {
            listaPessoaFisica = (ArrayList<PessoaFisica>) ois.readObject();
        }
    }
}
