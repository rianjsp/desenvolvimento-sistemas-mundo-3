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
public class PessoaJuridicaRepo {
    // Definindo um array privado
    private ArrayList<PessoaJuridica> listaPessoaJuridica = new ArrayList<>();
    
    // Metodo inserir uma nova PessoaJuridica
    public void inserir(PessoaJuridica  pessoaJuridica){
        listaPessoaJuridica.add(pessoaJuridica);
    }
    
    // Metodo alterar uma pessoaJuridica existente
    public void alterar(PessoaJuridica pessoaJuridica){
        for(int i = 0; i < listaPessoaJuridica.size(); i++)
        {
            if(listaPessoaJuridica.get(i).getId() == pessoaJuridica.getId()){
                listaPessoaJuridica.set(i, pessoaJuridica);
                break;
            }
        }
    }
    
    // Metodo excluir PessoaJuridica pelo id
    public void excluir(int id){
        listaPessoaJuridica.removeIf(p -> p.getId() == id);
    }
    
    // Metodo obetr pessoaJuridica pelo id
    public PessoaJuridica obter(int id){
        for(PessoaJuridica p : listaPessoaJuridica)
        {
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    // Metodo obter todas pessoasJuridicas
    public ArrayList<PessoaJuridica> obterTodos() {
        return listaPessoaJuridica;
    }
    
    // Metodo persistencia de dados
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream
        (new FileOutputStream(nomeArquivo))) {
            oos.writeObject(listaPessoaJuridica);
        }
    }
    
    // Metodo para recuperar os dados do arquivo
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream
        (new FileInputStream(nomeArquivo))) {
            listaPessoaJuridica = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }
}
