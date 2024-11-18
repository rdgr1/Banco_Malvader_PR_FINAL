package org.swing.dao;

import org.swing.model.Relatorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO extends BaseDAO {

    public void save(Relatorio relatorio) throws SQLException {
        String sql = "INSERT INTO relatorio (tipo, data_geracao, dados, id_funcionario) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, relatorio.getTipo());
            stmt.setTimestamp(2, Timestamp.valueOf(relatorio.getDataGeracao()));
            stmt.setString(3, relatorio.getDados());
            stmt.setInt(4, relatorio.getIdFuncionario());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                relatorio.setId(rs.getInt(1));
            }
        }
    }

    public void update(Relatorio relatorio) throws SQLException {
        String sql = "UPDATE relatorio SET tipo = ?, data_geracao = ?, dados = ?, id_funcionario = ? WHERE id_relatorio = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, relatorio.getTipo());
            stmt.setTimestamp(2, Timestamp.valueOf(relatorio.getDataGeracao()));
            stmt.setString(3, relatorio.getDados());
            stmt.setInt(4, relatorio.getIdFuncionario());
            stmt.setInt(5, relatorio.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM relatorio WHERE id_relatorio = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Relatorio findById(int id) throws SQLException {
        String sql = "SELECT * FROM relatorio WHERE id_relatorio = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Relatorio(
                        rs.getInt("id_relatorio"), // Agora inclui o ID do relatório
                        rs.getString("tipo"),
                        rs.getTimestamp("data_geracao").toLocalDateTime(),
                        rs.getString("dados"),
                        rs.getInt("id_funcionario")
                );
            }
        }
        return null;
    }


    public List<Relatorio> findAllByFuncionarioId(int funcionarioId) throws SQLException {
        String sql = "SELECT * FROM relatorio WHERE id_funcionario = ?";
        List<Relatorio> relatorios = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, funcionarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                relatorios.add(new Relatorio(
                        rs.getInt("id_relatorio"), // Agora inclui o ID do relatório
                        rs.getString("tipo"),
                        rs.getTimestamp("data_geracao").toLocalDateTime(),
                        rs.getString("dados"),
                        rs.getInt("id_funcionario")
                ));
            }
        }
        return relatorios;
    }

    public List<Relatorio> listarPorFuncionario(int funcionarioId) throws SQLException {
        String sql = "SELECT * FROM relatorio WHERE id_funcionario = ?";
        List<Relatorio> relatorios = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, funcionarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                relatorios.add(new Relatorio(
                        rs.getInt("id_relatorio"), // ID do relatório
                        rs.getString("tipo"), // Tipo de relatório
                        rs.getTimestamp("data_geracao").toLocalDateTime(), // Data de geração
                        rs.getString("dados"), // Dados do relatório
                        rs.getInt("id_funcionario") // ID do funcionário
                ));
            }
        }
        return relatorios;
    }

}
