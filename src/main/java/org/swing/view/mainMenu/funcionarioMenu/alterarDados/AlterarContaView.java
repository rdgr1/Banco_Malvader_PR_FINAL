package org.swing.view.mainMenu.funcionarioMenu.alterarDados;

import org.swing.controller.ContaController;

import javax.swing.*;
import java.sql.SQLException;

public class AlterarContaView extends JFrame {
    private final ContaController contaController;

    public AlterarContaView() {
        this.contaController = new ContaController();

        setTitle("Alterar Conta");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Alterar Conta");
        lblTitulo.setBounds(150, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        lblNumeroConta.setBounds(50, 50, 150, 30);
        add(lblNumeroConta);

        JTextField txtNumeroConta = new JTextField();
        txtNumeroConta.setBounds(200, 50, 200, 30);
        add(txtNumeroConta);

        JLabel lblLimite = new JLabel("Novo Limite:");
        lblLimite.setBounds(50, 100, 150, 30);
        add(lblLimite);

        JTextField txtLimite = new JTextField();
        txtLimite.setBounds(200, 100, 200, 30);
        add(txtLimite);

        JLabel lblVencimento = new JLabel("Nova Data de Vencimento:");
        lblVencimento.setBounds(50, 150, 150, 30);
        add(lblVencimento);

        JTextField txtVencimento = new JTextField();
        txtVencimento.setBounds(200, 150, 200, 30);
        add(txtVencimento);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 200, 100, 30);
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(270, 200, 100, 30);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> {
            String numeroConta = txtNumeroConta.getText();
            String limiteStr = txtLimite.getText();
            String vencimento = txtVencimento.getText();

            if (numeroConta.isEmpty() || limiteStr.isEmpty() || vencimento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double limite = Double.parseDouble(limiteStr);
                boolean sucesso = contaController.atualizar(numeroConta, limite, vencimento);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Conta alterada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao alterar a conta. Verifique os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro no banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor do limite inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}

