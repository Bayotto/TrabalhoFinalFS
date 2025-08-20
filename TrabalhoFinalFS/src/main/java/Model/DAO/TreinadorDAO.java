package Model.DAO;

import Conexao.ConexaoPostgresDB;
import Model.Treinador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinadorDAO {

    public void cadastrar(Treinador treinador) throws SQLException {
        String sql = "INSERT INTO treinadores (nome, cidade) VALUES (?, ?)";
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

    public Treinador buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_treinador, nome, cidade FROM treinadores WHERE id_treinador = ?";
        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Treinador treinador = new Treinador();
                    treinador.setId_treinador(rs.getInt("id_treinador"));
                    treinador.setNome(rs.getString("nome"));
                    treinador.setCidade(rs.getString("cidade"));
                    return treinador;
                }
            }
        }
        return null;
    }

    public List<Treinador> listarTodos() throws SQLException {
        List<Treinador> lista = new ArrayList<>();
        String sql = "SELECT id_treinador, nome, cidade FROM treinadores";

        try (Connection conn = ConexaoPostgresDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Treinador treinador = new Treinador();
                treinador.setId_treinador(rs.getInt("id_treinador"));
                treinador.setNome(rs.getString("nome"));
                treinador.setCidade(rs.getString("cidade"));
                lista.add(treinador);
            }
        }
        return lista;
    }
}
