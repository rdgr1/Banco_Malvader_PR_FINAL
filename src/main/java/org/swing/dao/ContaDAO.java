package org.swing.dao;

import org.swing.model.Conta;

import java.sql.*;

public class ContaDAO extends BaseDAO {

    public void save(Conta conta) throws SQLException {
        if (conta.getCliente() == null || conta.getCliente().getId_usuario() == 0) {
            throw new SQLException("Cliente inválido. Certifique-se de que o cliente está salvo no banco de dados.");
        }

        String sql = "INSERT INTO conta (numero, agencia, saldo, tipo_conta, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conta.getNumero());
            stmt.setString(2, conta.getAgencia());
            stmt.setDouble(3, conta.getSaldo());
            stmt.setString(4, conta.getTipo_conta().name());
            stmt.setInt(5, conta.getCliente().getId_usuario());
            stmt.executeUpdate();
        }
    }

    public void update(Conta conta) throws SQLException {
        String sql = "UPDATE conta SET agencia = ?, saldo = ?, tipo_conta = ?, id_cliente = ? WHERE numero = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conta.getAgencia());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setString(3, conta.getTipo_conta().name());
            stmt.setInt(4, conta.getCliente().getId_usuario());
            stmt.setInt(5, conta.getNumero());
            stmt.executeUpdate();
        }
    }

    public void delete(int numero) throws SQLException {
        String sql = "DELETE FROM conta WHERE numero = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            stmt.executeUpdate();
        }
    }

    public Conta findById(int numero) throws SQLException {
        String sql = "SELECT * FROM conta WHERE numero = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ClienteDAO clienteDAO = new ClienteDAO();
                Conta conta = new Conta(
                        rs.getInt("numero"),
                        rs.getString("agencia"),
                        rs.getDouble("saldo"),
                        Conta.TipoConta.valueOf(rs.getString("tipo_conta")),
                        clienteDAO.findById(rs.getInt("id_cliente"))
                ) {
                    @Override
                    public void depositar(double valor) {
                        valor += saldo;
                    }

                    @Override
                    public void sacar(double valor) {
                        valor -= saldo;
                    }
                };
                return conta;
            }
        }
        return null;
    }
    public boolean encerrarConta(String numeroConta) throws SQLException {
        String sql = "DELETE FROM conta WHERE numero = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se a conta foi encerrada
        }
    }
    public double consultarSaldo(String numeroConta, String senha) throws SQLException {
        String sql = "SELECT saldo FROM conta INNER JOIN usuario ON conta.id_cliente = usuario.id_usuario " +
                "WHERE conta.numero = ? AND usuario.senha = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("saldo");
            }
        }
        throw new SQLException("Conta ou senha inválida.");
    }

    public void depositar(String numeroConta, double valor) throws SQLException {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE numero = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setString(2, numeroConta);
            stmt.executeUpdate();
        }
    }

    public boolean sacar(String numeroConta, String senha, double valor) throws SQLException {
        String sqlSaldo = "SELECT saldo FROM conta INNER JOIN usuario ON conta.id_cliente = usuario.id_usuario " +
                "WHERE conta.numero = ? AND usuario.senha = ?";
        String sqlSaque = "UPDATE conta SET saldo = saldo - ? WHERE numero = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmtSaldo = conn.prepareStatement(sqlSaldo);
             PreparedStatement stmtSaque = conn.prepareStatement(sqlSaque)) {

            stmtSaldo.setString(1, numeroConta);
            stmtSaldo.setString(2, senha);
            ResultSet rs = stmtSaldo.executeQuery();

            if (rs.next() && rs.getDouble("saldo") >= valor) {
                stmtSaque.setDouble(1, valor);
                stmtSaque.setString(2, numeroConta);
                stmtSaque.executeUpdate();
                return true;
            }
        }
        return false;
    }

    public boolean gerarExtrato(String numeroConta, String senha) throws SQLException {
        return true; // Implemente aqui a lógica para Excel.
    }

    public double consultarLimite(String numeroConta, String senha) throws SQLException {
        String sql = "SELECT limite FROM conta_corrente INNER JOIN conta ON conta_corrente.id_conta = conta.id_conta " +
                "INNER JOIN usuario ON conta.id_cliente = usuario.id_usuario " +
                "WHERE conta.numero = ? AND usuario.senha = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("limite");
            }
        }
        throw new SQLException("Conta ou senha inválida.");
    }
}
