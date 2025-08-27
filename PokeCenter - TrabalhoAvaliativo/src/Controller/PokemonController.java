package Controller;

import Model.DAO.PokemonDAO;
import Model.Pokemon;
import java.sql.SQLException;
import java.util.List;

public class PokemonController {
    private PokemonDAO pokemonDAO;

    public PokemonController() {
        this.pokemonDAO = new PokemonDAO();
    }

    public void cadastrarPokemon(String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo, int fk_treinador) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        // Exemplo de chamada do Model (já validado):
        Pokemon pokemon = new Pokemon(nome, tipoPrimario, tipoSecundario, nivel, hpMaximo, fk_treinador);
        try {

            // Validações
            if (nome == null || nome.trim().isEmpty()) {
                throw new Exception("O nome do Pokémon é obrigatório.");
            }

            if (tipoPrimario == null || tipoPrimario.trim().isEmpty()) {
                throw new Exception("O tipo primário do Pokémon é obrigatório.");
            }

            if (tipoPrimario.equalsIgnoreCase(tipoSecundario)) {
                throw new Exception("O Tipo Secundário não pode ser igual ao Tipo Primário.");
            }

            if (nivel < 0 || nivel > 100) {
                throw new Exception("O nível não é válido.");
            }

            if (hpMaximo < 0 || hpMaximo > 100) {
                throw new Exception("O HP máximo não é válido.");
            }

            // Validação de unicidade
            List<Pokemon> existentes = pokemonDAO.buscarPorNome(nome);
            if (!existentes.isEmpty()) {
                throw new Exception("Já existe um Pokémon com o nome '" + nome + "'.");
            }

            // Cadastro
            Pokemon pokemon1 = new Pokemon(nome, tipoPrimario, tipoSecundario, nivel, hpMaximo, fk_treinador);

            pokemonDAO.inserir(pokemon1);
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar Pokémon no banco de dados: " + e.getMessage());
        }
    }

    public void atualizarPokemon(int id, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        // Exemplo de chamada do Model (já validado):
        Pokemon pokemon = new Pokemon(id, nome, tipoPrimario, tipoSecundario, nivel, hpMaximo);

        // Validação de existência
        Pokemon existente = pokemonDAO.buscarPorId(id);
        if (existente == null) {
            throw new Exception("Pokémon com ID " + id + " não encontrado para atualização.");
        }

        // Validações
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome do Pokémon é obrigatório.");
        }

        if (tipoPrimario == null || tipoPrimario.trim().isEmpty()) {
            throw new Exception("O tipo primário do Pokémon é obrigatório.");
        }

        if (tipoPrimario.equalsIgnoreCase(tipoSecundario)) {
            throw new Exception("O Tipo Secundário não pode ser igual ao Tipo Primário.");
        }

        if (nivel < 0 || nivel > 100) {
            throw new Exception("O nível não é válido.");
        }

        if (hpMaximo < 0 || hpMaximo > 100) {
            throw new Exception("O HP máximo não é válido.");
        }

        // Validação de unicidade (se o nome for alterado para outro já existente)
        List<Pokemon> pokemonsComMesmoNome = pokemonDAO.buscarPorNome(nome);
        for (Pokemon p : pokemonsComMesmoNome) {
            if (p.getId() != id) {
                throw new Exception("Já existe um Pokémon com o nome '" + nome + "'.");
            }
        }

        // Atualização
        Pokemon pokemonAtualizado = new Pokemon(id, nome, tipoPrimario, tipoSecundario, nivel, hpMaximo);
        try {
            pokemonDAO.atualizar(pokemonAtualizado);
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar Pokémon no banco de dados: " + e.getMessage());
        }
    }

    public void inserirListaPokemons(List<Pokemon> listaPokemon) throws Exception {
        for (Pokemon p : listaPokemon) {
            if (p.getNome() == null || p.getNome().trim().isEmpty()) {
                throw new Exception("Nome do Pokémon é obrigatório.");
            }
            if (p.getTipoPrimario() == null || p.getTipoPrimario().trim().isEmpty()) {
                throw new Exception("Tipo primário é obrigatório.");
            }
            if (p.getTipoPrimario().equalsIgnoreCase(p.getTipoSecundario())) {
                throw new Exception("Tipo secundário não pode ser igual ao primário.");
            }
            if (p.getNivel() < 0 || p.getNivel() > 100) {
                throw new Exception("Nível inválido.");
            }
            if (p.getHpMaximo() < 0 || p.getHpMaximo() > 300) {
                throw new Exception("HP inválido.");
            }
        }

        try {
            pokemonDAO.inserirListaPokemons(listaPokemon);
        } catch (SQLException e) {
            throw new Exception("Erro ao inserir lista: " + e.getMessage());
        }
    }

    public List<Pokemon> listarTodosPokemons() {
        return pokemonDAO.listarTodos();
    }

    public Pokemon buscarPokemonPorId(int id) {
        return pokemonDAO.buscarPorId(id);
    }

    public void removerPokemon(int id) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---

        // Validação de existência
        Pokemon existente = pokemonDAO.buscarPorId(id);
        if (existente == null) {
            throw new Exception("Pokémon com ID " + id + " não encontrado para remoção.");
        }

        try {
            pokemonDAO.remover(id);
        } catch (SQLException e) {
            throw new Exception("Erro ao remover Pokémon: " + e.getMessage());
        }
    }

    public List<Pokemon> buscarPokemonPorNome(String nome) {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        return pokemonDAO.buscarPorNome(nome);
    }
}