package Controller;


import Model.DAO.TreinadorDAO;
import Model.DAO.PokemonDAO;

import Model.Pokemon;
import Model.Treinador;

import java.sql.SQLException;
import java.util.List;

public class TreinadorController {

    private TreinadorDAO treinadorDAO;

    public void cadastrarTreinador(String nome, String cidade) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        // Exemplo de chamada do Model (já validado):

        Treinador treinador = new Treinador(nome, cidade);
        try {

            // Validações
            if (nome == null || nome.trim().isEmpty()) {
                throw new Exception("O nome do Pokémon é obrigatório.");
            }

            if (cidade == null || cidade.trim().isEmpty()) {
                throw new Exception("A Regiao do Treinador é obrigatório.");
            }


            // Validação de unicidade
            List<Treinador> existentes = treinadorDAO.buscarPorNome(nome);
            if (!existentes.isEmpty()) {
                throw new Exception("Já existe um Treinador com o nome '" + nome + "'.");
            }

            // Cadastro
            Treinador treinador1 = new Treinador(nome, cidade);

            treinadorDAO.Cadastrar(treinador1);
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar treinador no banco de dados: " + e.getMessage());
        }
    }

    public void atualizarPokemon(int id, String nome, String cidade) throws Exception {

        // --- EXERCÍCIO: Adicionar validações aqui! ---
        // Exemplo de chamada do Model (já validado):

        Treinador treinador = new Treinador(id, nome, cidade);

        // Validação de existência
        Treinador existente = treinadorDAO.buscarPorId(id);
        if (existente == null) {
            throw new Exception("Treinador com ID " + id + " não encontrado para atualização.");
        }

        // Validações
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome do Pokémon é obrigatório.");
        }

        if (cidade == null || cidade.trim().isEmpty()) {
            throw new Exception("O cidade do Treinador é obrigatório.");
        }


        // Validação de unicidade (se o nome for alterado para outro já existente)
        List<Treinador> treinadorComMesmoNome = treinadorDAO.buscarPorNome(nome);
        for (Treinador t : treinadorComMesmoNome) {
            if (t.getId_treinador() != id) {
                throw new Exception("Já existe um Treinador com o nome '" + nome + "'.");
            }
        }

        // Atualização
        Treinador treinadorAtualizado = new Treinador(id, nome, cidade);
        try {
            treinadorDAO.atualizar(treinadorAtualizado);
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar treinador no banco de dados: " + e.getMessage());
        }
    }

    public void inserirListaTreinadores(List<Treinador> listaTreinador) throws Exception {
        for (Treinador t : listaTreinador) {
            if (t.getNome() == null || t.getNome().trim().isEmpty()) {
                throw new Exception("Nome do Pokémon é obrigatório.");
            }
            if (t.getCidade() == null || t.getCidade().trim().isEmpty()) {
                throw new Exception("cidade é obrigatório.");
            }

        }

        try {
            treinadorDAO.cadastrarListaDeTreinador(listaTreinador);
        } catch (SQLException e) {
            throw new Exception("Erro ao inserir lista: " + e.getMessage());
        }
    }

    public List<Treinador> listarTodosTreinadores() {
        return treinadorDAO.listarTodos();
    }

    public Treinador buscarTreinadorPorId(int id) {
        return treinadorDAO.buscarPorId(id);
    }

    public void removerPokemon(int id) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        // Validação de existência

        Treinador existente = treinadorDAO.buscarPorId(id);
        if (existente == null) {
            throw new Exception("Treinador com ID " + id + " não encontrado para remoção.");
        }

        try {
            treinadorDAO.remover(id);
        } catch (SQLException e) {
            throw new Exception("Erro ao remover Treinador: " + e.getMessage());
        }
    }

    public List<Treinador> buscarTreinadorPorNome(String nome) {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        return treinadorDAO.buscarPorNome(nome);
    }
}

