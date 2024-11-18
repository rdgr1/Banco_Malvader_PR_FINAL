package org.swing.dao;

import org.swing.model.Funcionario;

import java.sql.*;

public class FuncionarioDAO extends BaseDAO {

    public void save(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (id_usuario, codigo, cargo, senha) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, funcionario.getId_usuario());
            stmt.setString(2, funcionario.getCodigo());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getSenha());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                funcionario.setId_usuario(rs.getInt(1));
            }
        }
    }

    public void update(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET codigo = ?, cargo = ?, senha = ? WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getCodigo());
            stmt.setString(2, funcionario.getCargo());
            stmt.setString(3, funcionario.getSenha());
            stmt.setInt(4, funcionario.getId_usuario());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Funcionario findById(int id) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE id_usuario = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Funcionario funcionario = (Funcionario) usuarioDAO.findById(id);
                funcionario.setCodigo(rs.getString("codigo"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSenha(rs.getString("senha"));
                return funcionario;
            }
        }
        return null;
    }
}
