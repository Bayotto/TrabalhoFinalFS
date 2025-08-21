package Model;
import jakarta.persistence.*;

@Entity
@Table(name="pokemons")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pokemon",nullable = false)
    private int id_pokemon;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="tipo_primario", nullable = false)
    private String tipoPrimario;

    @Column(name="tipo_secundario",nullable = true)
    private String tipoSecundario;

    @Column(name="nivel",nullable = false)
    private int nivel;

    @Column(name="hp_maximo",nullable = false)
    private int hpMaximo;

    @Column(name="fk_treinador",nullable = false)
    private int FK_Treinador;

    public Pokemon(){};


    public Pokemon(int id, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo, int FK_Treinador) {
        this.id_pokemon = id;
        this.nome = nome;
        this.tipoPrimario = tipoPrimario;
        this.tipoSecundario = tipoSecundario;
        this.FK_Treinador = FK_Treinador;
        // As validações são chamadas aqui pelos setters
        setNivel(nivel);
        setHpMaximo(hpMaximo);
    }

    public Pokemon(String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo,int FK_Treinador) {
        this(-1, nome, tipoPrimario, tipoSecundario, nivel, hpMaximo, FK_Treinador);
    }

    public Pokemon(String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) {
    }

    // Getters
    public int getId() { return id_pokemon; }
    public String getNome() { return nome; }
    public String getTipoPrimario() { return tipoPrimario; }
    public String getTipoSecundario() { return tipoSecundario; }
    public int getNivel() { return nivel; }
    public int getHpMaximo() { return hpMaximo; }
    public int getFK_Treinador() {
        return FK_Treinador;
    }



    // Setters com validação (implementadas aqui!)
    public void setId(int id) { this.id_pokemon = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTipoPrimario(String tipoPrimario) { this.tipoPrimario = tipoPrimario; }
    public void setTipoSecundario(String tipoSecundario) { this.tipoSecundario = tipoSecundario; }
    public void setFK_Treinador(int FK_Treinador) {
        this.FK_Treinador = FK_Treinador;
    }
    public void setNivel(int nivel) {
        if (nivel < 1 || nivel > 100) {
            throw new IllegalArgumentException("O Nível do Pokémon deve estar entre 1 e 100.");
        }
        this.nivel = nivel;
    }

    public void setHpMaximo(int hpMaximo) {
        if (hpMaximo <= 0) {
            throw new IllegalArgumentException("O HP Máximo do Pokémon deve ser maior que 0.");
        }
        this.hpMaximo = hpMaximo;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id_pokemon +
                ", nome='" + nome + '\'' +
                ", tipoPrimario='" + tipoPrimario + '\'' +
                ", tipoSecundario='" + tipoSecundario + '\'' +
                ", nivel=" + nivel +
                ", hpMaximo=" + hpMaximo +
                '}';
    }
}