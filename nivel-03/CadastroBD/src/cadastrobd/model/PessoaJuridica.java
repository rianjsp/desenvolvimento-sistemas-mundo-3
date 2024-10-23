package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
    private String cnpj;
    private String dataAbertura;

    // Construtor com parametros
    public PessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj, String dataAbertura) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
        this.dataAbertura = dataAbertura;
    }

    // Construtor padrão
    public PessoaJuridica() {
    }

    // Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    // Metodo para exibir os dados da pessoa jurídica
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Data de Abertura: " + dataAbertura);
    }
}
