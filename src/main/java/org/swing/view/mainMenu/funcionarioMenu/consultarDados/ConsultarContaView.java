package org.swing.view.mainMenu.funcionarioMenu.consultarDados;

import org.swing.controller.ContaController;
import org.swing.model.Conta;
import org.swing.model.ContaCorrente;
import org.swing.util.GradientPanel;
import org.swing.util.SwingStyleUtil;

import javax.swing.*;
import java.sql.SQLException;

public class ConsultarContaView extends JFrame {
    private final ContaController contaController;

    public ConsultarContaView() {
        SwingStyleUtil.applyGlobalStyles();
        setContentPane(new GradientPanel());
        setLocationRelativeTo(null);

        this.contaController = new ContaController();

        setTitle("Consultar Conta");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Consultar Conta");
        lblTitulo.setBounds(150, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        lblNumeroConta.setBounds(50, 60, 150, 30);
        add(lblNumeroConta);

        JTextField txtNumeroConta = new JTextField();
        txtNumeroConta.setBounds(200, 60, 200, 30);
        add(txtNumeroConta);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(200, 100, 100, 30);
        add(btnConsultar);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 150, 400, 100);
        textArea.setEditable(false);
        add(textArea);

        btnConsultar.addActionListener(e -> {
            String numeroConta = txtNumeroConta.getText();
            if (numeroConta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o número da conta!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Conta conta = contaController.buscarPorNumero(numeroConta);
            if (conta != null) {
                textArea.setText("Tipo de Conta: " + conta.getTipo_conta() + "\n" +
                        "Nome: " + conta.getCliente().getNome() + "\n" +
                        "CPF: " + conta.getCliente().getCpf() + "\n" +
                        "Saldo: " + conta.getSaldo() + "\n" +
                        "Limite Disponível: " + (conta instanceof ContaCorrente ? ((ContaCorrente) conta).getLimite() : "N/A") + "\n" +
                        "Data de Vencimento: " + (conta instanceof ContaCorrente ? ((ContaCorrente) conta).getDataVencimento() : "N/A"));
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
