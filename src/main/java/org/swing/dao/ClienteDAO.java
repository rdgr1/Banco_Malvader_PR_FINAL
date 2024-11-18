package org.swing.dao;

import org.swing.model.Cliente;
import org.swing.model.Endereco;
import org.swing.model.Usuario;

import java.sql.*;

public class ClienteDAO extends BaseDAO {

    public void save(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (id_usuario, senha) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cliente.getId_usuario());
            stmt.setString(2, cliente.getSenha());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId_usuario(rs.getInt(1));
            }
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
}
