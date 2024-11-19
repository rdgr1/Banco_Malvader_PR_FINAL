package org.swing.view.mainMenu.funcionarioMenu.alterarDados;
import org.swing.controller.ClienteController;
import org.swing.model.Cliente;

import javax.swing.*;
import java.sql.SQLException;

public class AlterarClienteView extends JFrame {
    private final ClienteController clienteController;

    public AlterarClienteView() {
        this.clienteController = new ClienteController();

        setTitle("Alterar Cliente");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Alterar Cliente");
        lblTitulo.setBounds(150, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblCpf = new JLabel("CPF do Cliente:");
        lblCpf.setBounds(50, 50, 150, 30);
        add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(200, 50, 200, 30);
        add(txtCpf);

        JLabel lblTelefone = new JLabel("Novo Telefone:");
        lblTelefone.setBounds(50, 100, 150, 30);
        add(lblTelefone);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setBounds(200, 100, 200, 30);
        add(txtTelefone);

        JLabel lblEndereco = new JLabel("Novo Endereço:");
        lblEndereco.setBounds(50, 150, 150, 30);
        add(lblEndereco);

        JTextField txtEndereco = new JTextField();
        txtEndereco.setBounds(200, 150, 200, 30);
        add(txtEndereco);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 250, 100, 30);
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(270, 250, 100, 30);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> {
            String cpf = txtCpf.getText();
            String telefone = txtTelefone.getText();
            String endereco = txtEndereco.getText();

            if (cpf.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}