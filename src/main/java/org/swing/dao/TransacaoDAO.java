package org.swing.dao;

import org.swing.model.Transacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO extends BaseDAO {

    public void save(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacao (conta_id, tipo, valor, data) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, transacao.getContaId());
            stmt.setString(2, transacao.getTipo());
            stmt.setDouble(3, transacao.getValor());
            stmt.setTimestamp(4, new Timestamp(transacao.getData().getTime()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                transacao.setId(rs.getInt(1));
            }
        }
    }

    public void update(Transacao transacao) throws SQLException {
        String sql = "UPDATE transacao SET conta_id = ?, tipo = ?, valor = ?, data = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transacao.getContaId());
            stmt.setString(2, transacao.getTipo());
            stmt.setDouble(3, transacao.getValor());
            stmt.setTimestamp(4, new Timestamp(transacao.getData().getTime()));
            stmt.setInt(5, transacao.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM transacao WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Transacao findById(int id) throws SQLException {
        String sql = "SELECT * FROM transacao WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Transacao(
                        rs.getInt("id"),
                        rs.getInt("conta_id"),
                        rs.getString("tipo"),
                        rs.getDouble("valor"),
                        rs.getTimestamp("data")
                );
            }
        }
        return null;
    }

    public List<Transacao> findAllByContaId(int contaId) throws SQLException {
        String sql = "SELECT * FROM transacao WHERE conta_id = ?";
        List<Transacao> transacoes = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacoes.add(new Transacao(
                        rs.getInt("id"),
                        rs.getInt("conta_id"),
                        rs.getString("tipo"),
                        rs.getDouble("valor"),
                        rs.getTimestamp("data")
                ));
            }
        }
        return transacoes;
    }
    public List<Transacao> listarPorContaId(int contaId) throws SQLException {
        String sql = "SELECT * FROM transacao WHERE conta_id = ?";
        List<Transacao> transacoes = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transacoes.add(new Transacao(
                        rs.getInt("id"), // ID da transação
                        rs.getInt("conta_id"), // ID da conta
                        rs.getString("tipo"), // Tipo da transação (DEPOSITO, SAQUE, TRANSFERENCIA)
                        rs.getDouble("valor"), // Valor da transação
                        rs.getTimestamp("data") // Data da transação
                ));
            }
        }
        return transacoes;
    }
}
