/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;

/**
 *
 * @author Rian Joseph
 */
public class Pessoa implements Serializable {
    // Atributos
    private int id;
    private String nome;
    
    // Construtor
    public Pessoa(){}
    
    // Construtor completo
    public Pessoa(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    // Getters e Setters
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    // Exibir separado
    public void exibir(){
        System.out.println("ID: "+ id);
        System.out.println("Nome: "+ nome);
    }
}
