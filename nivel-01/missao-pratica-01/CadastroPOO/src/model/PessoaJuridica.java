package model;

/**
 *
 * @author Rian Joseph
 */
public class PessoaJuridica extends Pessoa {
    // Atributos
    private String cnpj;
    
    // Construtor
    public PessoaJuridica() {}
    
    // Construtor Completo
    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }
    
    // Getters e Setters
    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    // Exibir polimorfico
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }

    // Sobrescreve o metodo toString para exibir informações úteis
    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "id=" + getId() + 
                ", nome='" + getNome() + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
