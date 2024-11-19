package org.swing.dao;

import org.swing.model.Relatorio;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public boolean validarSenhaFuncionario(String senha) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM usuario WHERE tipo_usuario = 'FUNCIONARIO' AND senha = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        }
        return false;
    }

    public boolean gerarRelatorioGeral() throws SQLException, IOException {
        String sql = "SELECT c.numero, c.agencia, c.saldo, t.tipo, t.valor, t.data_hora " +
                "FROM conta c " +
                "INNER JOIN transacao t ON c.id_conta = t.id_conta";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Relatório Geral");

            // Cabeçalhos
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Número da Conta");
            header.createCell(1).setCellValue("Agência");
            header.createCell(2).setCellValue("Saldo");
            header.createCell(3).setCellValue("Tipo de Transação");
            header.createCell(4).setCellValue("Valor");
            header.createCell(5).setCellValue("Data/Hora");

            // Dados
            int rowIndex = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(rs.getString("numero"));
                row.createCell(1).setCellValue(rs.getString("agencia"));
                row.createCell(2).setCellValue(rs.getDouble("saldo"));
                row.createCell(3).setCellValue(rs.getString("tipo"));
                row.createCell(4).setCellValue(rs.getDouble("valor"));
                row.createCell(5).setCellValue(rs.getTimestamp("data_hora").toString());
            }

            // Exporta para arquivo Excel
            try (FileOutputStream fileOut = new FileOutputStream("RelatorioGeral.xlsx")) {
                workbook.write(fileOut);
            }

            workbook.close();
            return true;
        }
    }
}
