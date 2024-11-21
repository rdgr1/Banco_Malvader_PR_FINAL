package org.swing.view.mainMenu.funcionarioMenu.consultarDados;

import org.swing.controller.ClienteController;
import org.swing.model.Cliente;
import org.swing.util.GradientPanel;
import org.swing.util.SwingStyleUtil;

import javax.swing.*;
import java.sql.SQLException;

public class ConsultarClienteView extends JFrame {
    private final ClienteController clienteController;

    public ConsultarClienteView() {
        SwingStyleUtil.applyGlobalStyles();
        setContentPane(new GradientPanel());
        this.clienteController = new ClienteController();

        setTitle("Consultar Cliente");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblTitulo = new JLabel("Consultar Cliente");
        lblTitulo.setBounds(150, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblCpf = new JLabel("CPF do Cliente:");
        lblCpf.setBounds(50, 60, 150, 30);
        add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(200, 60, 200, 30);
        add(txtCpf);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(200, 100, 100, 30);
        add(btnConsultar);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 150, 400, 100);
        textArea.setEditable(false);
        add(textArea);

        btnConsultar.addActionListener(e -> {
            String cpf = txtCpf.getText();
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o CPF do cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Cliente cliente = clienteController.buscarPorCpf(cpf);
                if (cliente != null) {
                    textArea.setText("Nome: " + cliente.getNome() + "\n" +
                            "CPF: " + cliente.getCpf() + "\n" +
                            "Data de Nascimento: " + cliente.getData_nascimento() + "\n" +
                            "Telefone: " + cliente.getTelefone() + "\n" +
                            "Endereço: " + cliente.getEndereco());
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao consultar o cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
