package org.swing.dao;

import org.swing.model.Conta;

import java.sql.*;

public class ContaDAO extends BaseDAO {

    public void save(Conta conta) throws SQLException {
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
}
