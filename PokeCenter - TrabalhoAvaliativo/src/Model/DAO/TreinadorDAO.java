package Model.DAO;

import Conexao.ConexaoPostgresDB;
import Model.Treinador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinadorDAO {

    ///  int id_treinador, String nome, String regiao

    public void Cadastrar(Treinador treinador) throws SQLException {
        String sql = "INSERT INTO treinador (nome, cidade) VALUES (?, ?)";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, treinador.getNome());
            stmt.setString(2, treinador.getCidade());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    treinador.setId_treinador(rs.getInt(1));
                }
            }
        }
    }

    public void cadastrarListaDeTreinador(List<Treinador> listaDeTreinador) throws SQLException {
        String sql = "INSERT INTO treinador (nome, cidade) VALUES (?, ?)";
        Connection conn = ConexaoPostgresDB.conectar();
        conn.setAutoCommit(false);
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        final int Batch_size = 1000;
        for(int i = 0; i < listaDeTreinador.size(); i++){
            Treinador meuTreinador = listaDeTreinador.get(i);
            stmt.setString(1, meuTreinador.getNome());
            stmt.setString(2, meuTreinador.getCidade());
            stmt.executeUpdate(); // adiciona a intrucao ao lote
            if(( i+1 ) == listaDeTreinador.size() || (i+1) % Batch_size == 0){
                stmt.executeBatch();
                stmt.clearBatch();
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    meuTreinador.setId_treinador(rs.getInt(1));
                }
            }
        }
        conn.commit();
        conn.setAutoCommit(true);
    }

    public void atualizar(Treinador treinador) throws SQLException {
        String sql = "UPDATE treinador SET nome = ?, cidade = ?  WHERE id_treinador = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, treinador.getNome());
            stmt.setString(2, treinador.getCidade());
            stmt.executeUpdate();
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM treinador WHERE id_treinador = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Treinador buscarPorId(int id) {
        String sql = "SELECT * FROM treinador WHERE id_treinador = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Treinador(
                            rs.getInt("id_treinador"),
                            rs.getString("nome"),
                            rs.getString("cidade")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Treinador por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Treinador> listarTodos() {
        List<Treinador> treinadores = new ArrayList<>();
        String sql = "SELECT * FROM treinador ORDER BY nome";
        try (Connection conn = ConexaoPostgresDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                treinadores.add(new Treinador(
                        rs.getInt("id_treinador"),
                        rs.getString("nome"),
                        rs.getString("cidade")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todos os Treinador: " + e.getMessage());
        }
        return treinadores;
    }

    public List<Treinador> buscarPorNome(String nomeBusca) {
        List<Treinador> treinadores = new ArrayList<>();
        String sql = "SELECT * FROM treinador WHERE LOWER(nome) LIKE LOWER(?) ORDER BY nome";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nomeBusca + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    treinadores.add(new Treinador(
                            rs.getInt("id_treinador"),
                            rs.getString("nome"),
                            rs.getString("cidade")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar treinador por nome: " + e.getMessage());
        }
        return treinadores;
    }

    public boolean treinadorJaExiste(String nome) throws SQLException {
        String sql = "SELECT COUNT(*) FROM treinador WHERE LOWER(nome) = LOWER(?)";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }


}

