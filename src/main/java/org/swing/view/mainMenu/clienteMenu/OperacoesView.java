package org.swing.view.mainMenu.clienteMenu;

import org.swing.controller.ContaController;

import javax.swing.*;
import java.sql.SQLException;

public class OperacoesView extends JFrame {
    ContaController contaController;
    OperacoesView(){
        this.contaController = new ContaController();
        setTitle("Operaçẽos do Cliente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JButton btnSaldo = new JButton("Consultar Saldo");
        btnSaldo.setBounds(50, 50, 300, 30);
        add(btnSaldo);

        JButton btnDeposito = new JButton("Depósito");
        btnDeposito.setBounds(50, 90, 300, 30);
        add(btnDeposito);

        JButton btnSaque = new JButton("Saque");
        btnSaque.setBounds(50, 130, 300, 30);
        add(btnSaque);

        JButton btnExtrato = new JButton("Extrato");
        btnExtrato.setBounds(50, 170, 300, 30);
        add(btnExtrato);

        JButton btnLimite = new JButton("Consultar Limite");
        btnLimite.setBounds(50, 210, 300, 30);
        add(btnLimite);
        btnSaldo.addActionListener(e -> consultarSaldo());
        btnDeposito.addActionListener(e -> realizarDeposito());
        btnSaque.addActionListener(e -> realizarSaque());
        btnExtrato.addActionListener(e -> consultarExtrato());
        btnLimite.addActionListener(e -> consultarLimite());
    }
    private void consultarSaldo() {
        String numeroConta = JOptionPane.showInputDialog(this, "Digite o número da conta:");
        String senha = JOptionPane.showInputDialog(this, "Digite a senha:");

        try {
            double saldo = contaController.consultarSaldo(numeroConta, senha);
            JOptionPane.showMessageDialog(this, "Saldo atual: R$ " + saldo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar saldo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarDeposito() {
        String numeroConta = JOptionPane.showInputDialog(this, "Digite o número da conta:");
        String valorStr = JOptionPane.showInputDialog(this, "Digite o valor do depósito:");

        try {
            double valor = Double.parseDouble(valorStr);
            contaController.depositar(numeroConta, valor);
            JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao realizar depósito: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarSaque() {
        String numeroConta = JOptionPane.showInputDialog(this, "Digite o número da conta:");
        String senha = JOptionPane.showInputDialog(this, "Digite a senha:");
        String valorStr = JOptionPane.showInputDialog(this, "Digite o valor do saque:");

        try {
            double valor = Double.parseDouble(valorStr);
            boolean sucesso = contaController.sacar(numeroConta, senha, valor);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao realizar saque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarExtrato() {
        String numeroConta = JOptionPane.showInputDialog(this, "Digite o número da conta:");
        String senha = JOptionPane.showInputDialog(this, "Digite a senha:");

        try {
            boolean sucesso = contaController.gerarExtrato(numeroConta, senha);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Extrato gerado com sucesso! Arquivo: ExtratoConta.xlsx");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao gerar extrato. Verifique as credenciais.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar extrato: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarLimite() {
        String numeroConta = JOptionPane.showInputDialog(this, "Digite o número da conta:");
        String senha = JOptionPane.showInputDialog(this, "Digite a senha:");

        try {
            double limite = contaController.consultarLimite(numeroConta, senha);
            JOptionPane.showMessageDialog(this, "Limite disponível: R$ " + limite);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar limite: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
