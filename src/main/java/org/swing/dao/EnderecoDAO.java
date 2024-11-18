package org.swing.dao;

import org.swing.model.Endereco;

import java.sql.*;

public class EnderecoDAO extends BaseDAO{
    public void save(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO endereco (cep, local, numero_casa, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLocal());
            stmt.setInt(3, endereco.getNumero_casa());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getEstado());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                endereco.setId_endereco(rs.getInt(1));
            }
        }
    }
    public void update(Endereco endereco) throws SQLException {
        String sql = "UPDATE endereco SET cep = ?, local = ?, numero_casa = ?, bairro = ?, cidade = ?, estado = ? WHERE id_endereco = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLocal());
            stmt.setInt(3, endereco.getNumero_casa());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getEstado());
            stmt.setInt(7, endereco.getId_endereco());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Endereco findById(int id) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("cep"),
                        rs.getString("local"),
                        rs.getInt("numero_casa"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
            }
        }
        return null;
    }
}
