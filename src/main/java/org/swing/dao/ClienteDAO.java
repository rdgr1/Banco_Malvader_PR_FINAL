package org.swing.dao;

import org.swing.model.Cliente;
import org.swing.model.Endereco;
import org.swing.model.Usuario;

import java.sql.*;

public class ClienteDAO extends BaseDAO {

    public void save(Cliente cliente) throws SQLException {
        // Salva primeiro o usuário
        String sqlUsuario = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, id_endereco, tipo_usuario, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
            stmtUsuario.setString(1, cliente.getNome());
            stmtUsuario.setString(2, cliente.getCpf());
            stmtUsuario.setDate(3, Date.valueOf(cliente.getData_nascimento()));
            stmtUsuario.setString(4, cliente.getTelefone());
            stmtUsuario.setInt(5, cliente.getEndereco().getId_endereco());
            stmtUsuario.setString(6, "CLIENTE"); // Define como CLIENTE
            stmtUsuario.setString(7, cliente.getSenha());

            stmtUsuario.executeUpdate();

            // Recupera o ID gerado
            ResultSet rs = stmtUsuario.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId_usuario(rs.getInt(1));
            }
        }

        // Salva o cliente
        String sqlCliente = "INSERT INTO cliente (id_usuario) VALUES (?)";

        try (Connection conn = getConnection(); PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente)) {
            stmtCliente.setInt(1, cliente.getId_usuario());
            stmtCliente.executeUpdate();
        }
    }


    public void update(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET senha = ? WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getSenha());
            stmt.setInt(2, cliente.getId_usuario());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Cliente findById(int id) throws SQLException {
        String sql = "SELECT u.*, c.senha AS cliente_senha, " +
                "e.cep, e.local, e.numero_casa, e.bairro, e.cidade, e.estado " +
                "FROM usuario u " +
                "JOIN cliente c ON u.id_usuario = c.id_usuario " +
                "JOIN endereco e ON u.id_endereco = e.id_endereco " +
                "WHERE u.id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("cep"),
                        rs.getString("local"),
                        rs.getInt("numero_casa"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );

                return new Cliente(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        endereco,
                        rs.getString("cliente_senha")
                );
            }
        }
        return null;
    }
    public Cliente findByCpf(String cpf) throws SQLException {
        String sql = """
                     SELECT u.*, e.cep, e.local, e.numero_casa, e.bairro, e.cidade, e.estado
                     FROM usuario u
                     JOIN cliente c ON u.id_usuario = c.id_usuario
                     LEFT JOIN endereco e ON u.id_endereco = e.id_endereco
                     WHERE u.cpf = ?""";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("cep"),
                        rs.getString("local"),
                        rs.getInt("numero_casa"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );

                return new Cliente(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        endereco,
                        rs.getString("senha")
                );
            }
        }
        return null; // Retorna null se o cliente não for encontrado
    }
    public boolean atualizarCliente(String cpf, String telefone, String endereco) throws SQLException {
        String sql = "UPDATE usuario u " +
                "INNER JOIN cliente c ON u.id_usuario = c.id_usuario " +
                "SET u.telefone = ?, u.endereco_completo = ? " +
                "WHERE u.cpf = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, telefone);
            stmt.setString(2, endereco);
            stmt.setString(3, cpf);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se a atualização foi realizada
        }
    }
}
