package Model;

public class Pokemon {
    private int id;
    private String nome;
    private String tipoPrimario;
    private String tipoSecundario; // Pode ser null
    private int nivel;
    private int hpMaximo;
    private int fk_Treinador;



    public Pokemon(int id, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) {
        this.id = id;
        this.nome = nome;
        this.tipoPrimario = tipoPrimario;
        this.tipoSecundario = tipoSecundario;
        // As validações são chamadas aqui pelos setters
        setNivel(nivel);
        setHpMaximo(hpMaximo);
    }

    public Pokemon(String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo, int fk_Treinador) {
        this.nome = nome;
        this.tipoPrimario = tipoPrimario;
        this.tipoSecundario = tipoSecundario;
        this.nivel = nivel;
        this.hpMaximo = hpMaximo;
        this.fk_Treinador = fk_Treinador;
    }

    public Pokemon(int idPokemon, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo, int fkTreinador) {
    }

    public Pokemon(int idPokemon, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo, Object o, int fkTreinador) {
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getTipoPrimario() { return tipoPrimario; }
    public String getTipoSecundario() { return tipoSecundario; }
    public int getNivel() { return nivel; }
    public int getHpMaximo() { return hpMaximo; }

    // Setters com validação (implementadas aqui!)
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTipoPrimario(String tipoPrimario) { this.tipoPrimario = tipoPrimario; }
    public void setTipoSecundario(String tipoSecundario) { this.tipoSecundario = tipoSecundario; }
    public int getFk_Treinador() {
        return fk_Treinador;
    }

    public void setFk_Treinador(int fk_Treinador) {
        this.fk_Treinador = fk_Treinador;
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
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoPrimario='" + tipoPrimario + '\'' +
                ", tipoSecundario='" + tipoSecundario + '\'' +
                ", nivel=" + nivel +
                ", hpMaximo=" + hpMaximo +
                '}';
    }
}