package org.swing.view.mainMenu.funcionarioMenu.encerrarConta;

import org.swing.controller.ContaController;

import javax.swing.*;
import java.sql.SQLException;

public class EncerramentoContaView extends JFrame {
    private final ContaController contaController;

    public EncerramentoContaView() {
        this.contaController = new ContaController();

        setTitle("Encerramento de Conta");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Encerramento de Conta");
        lblTitulo.setBounds(150, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        lblNumeroConta.setBounds(50, 50, 150, 30);
        add(lblNumeroConta);

        JTextField txtNumeroConta = new JTextField();
        txtNumeroConta.setBounds(200, 50, 200, 30);
        add(txtNumeroConta);

        JLabel lblSenhaAdmin = new JLabel("Senha do Administrador:");
        lblSenhaAdmin.setBounds(50, 100, 150, 30);
        add(lblSenhaAdmin);

        JPasswordField txtSenhaAdmin = new JPasswordField();
        txtSenhaAdmin.setBounds(200, 100, 200, 30);
        add(txtSenhaAdmin);

        JButton btnEncerrar = new JButton("Encerrar Conta");
        btnEncerrar.setBounds(150, 150, 150, 30);
        add(btnEncerrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, 150, 100, 30);
        add(btnCancelar);

        // Ação do botão Encerrar Conta
        btnEncerrar.addActionListener(e -> {
            String numeroConta = txtNumeroConta.getText();
            String senhaAdmin = new String(txtSenhaAdmin.getPassword());

            if (numeroConta.isEmpty() || senhaAdmin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validação da senha de administrador (substitua por uma lógica real)
            if (!senhaAdmin.equals("admin123")) { // Substituir com a validação real
                JOptionPane.showMessageDialog(this, "Senha de administrador inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Você tem certeza que deseja encerrar a conta número " + numeroConta + "?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    boolean sucesso = contaController.encerrarConta(numeroConta);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(this, "Conta encerrada com sucesso!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao encerrar a conta. Verifique o número informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro no banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Cancelar
        btnCancelar.addActionListener(e -> dispose());
    }
}
