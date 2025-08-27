package Model;

public class Treinador {
    private int id_treinador;
    private String nome;
    private String cidade;

    public Treinador(int id_treinador, String nome, String cidade) {
        this.id_treinador = id_treinador;
        this.nome = nome;
        this.cidade = cidade;
    }

    public Treinador(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    public int getId_treinador() {
        return id_treinador;
    }

    public void setId_treinador(int id_treinador) {
        this.id_treinador = id_treinador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
