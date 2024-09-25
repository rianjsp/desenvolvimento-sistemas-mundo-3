/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rian Joseph
 */
public class PessoaFisica extends Pessoa {
    // Atributos
    private String cpf;
    private int idade;
    
    // Construtor
    public PessoaFisica(){}
    
    // Construtor completo
    public PessoaFisica(int id, String nome, 
            String cpf, int idade){
        super(id, nome);
        this.cpf = cpf;
        this.idade = idade;
    }
    
    // Getters e Setters
    public String getCpf(){
        return this.cpf;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public int getIdade(){
        return idade;
    }
    
    public void setIdade(int idade){
        this.idade = idade;
    }
    
    // Exibir polimorfico
    @Override
    public void exibir(){
        super.exibir();
        System.out.println("CPF: "+ cpf);
        System.out.println("Idade: "+ idade);
    }
}
