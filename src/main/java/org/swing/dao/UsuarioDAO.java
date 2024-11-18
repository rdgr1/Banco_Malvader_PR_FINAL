package org.swing.dao;

import org.swing.model.Usuario;
import org.swing.model.Endereco;

import java.sql.*;

public class UsuarioDAO extends BaseDAO {

    public void save(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, id_endereco, tipo_usuario, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setDate(3, Date.valueOf(usuario.getData_nascimento()));
            stmt.setString(4, usuario.getTelefone());
            stmt.setInt(5, usuario.getEndereco().getId_endereco());
            stmt.setString(6, usuario.getTipoUsuario().name());
            stmt.setString(7, usuario.getSenha());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId_usuario(rs.getInt(1));
            }
        }
    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, data_nascimento = ?, telefone = ?, id_endereco = ?, tipo_usuario = ?, senha = ? WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setDate(3, Date.valueOf(usuario.getData_nascimento()));
            stmt.setString(4, usuario.getTelefone());
            stmt.setInt(5, usuario.getEndereco().getId_endereco());
            stmt.setString(6, usuario.getTipoUsuario().name());
            stmt.setString(7, usuario.getSenha());
            stmt.setInt(8, usuario.getId_usuario());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Usuario findById(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                Endereco endereco = enderecoDAO.findById(rs.getInt("id_endereco"));

                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        endereco
                ) {
                    @Override
                    public boolean login(String senha) {
                        return senha.equals(getSenha());
                    }
                };

                usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(rs.getString("tipo_usuario")));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }
        }
        return null;
    }
}
