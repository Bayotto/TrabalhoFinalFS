package Model;

import jakarta.persistence.*;

@Entity
@Table(name = "treinador")
public class Treinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_treinador", nullable = false)
    private int id_treinador;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name="cidade",nullable = false)
    private String cidade;

    public Treinador(){};


    public Treinador(int id_treinador, String nome, String cidade) {
        this.id_treinador = id_treinador;
        this.nome = nome;
        this.cidade = cidade;
    }
    public Treinador( String nome, String cidade) {
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

    public String getcidade() {
        return cidade;
    }

    public void setcidade(String cidade) {
        this.cidade = cidade;
    }
}