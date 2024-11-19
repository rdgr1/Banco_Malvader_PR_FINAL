package org.swing.view.mainMenu.funcionarioMenu.alterarDados;

import org.swing.controller.FuncionarioController;

import javax.swing.*;
import java.sql.SQLException;

public class AlterarFuncionarioView extends JFrame {
    private final FuncionarioController funcionarioController;
    public AlterarFuncionarioView() {
        this.funcionarioController = new FuncionarioController();


        setTitle("Alterar Funcionário");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Alterar Funcionário");
        lblTitulo.setBounds(150, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código do Funcionário:");
        lblCodigo.setBounds(50, 50, 150, 30);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(200, 50, 200, 30);
        add(txtCodigo);

        JLabel lblCargo = new JLabel("Novo Cargo:");
        lblCargo.setBounds(50, 100, 150, 30);
        add(lblCargo);

        JTextField txtCargo = new JTextField();
        txtCargo.setBounds(200, 100, 200, 30);
        add(txtCargo);

        JLabel lblTelefone = new JLabel("Novo Telefone:");
        lblTelefone.setBounds(50, 150, 150, 30);
        add(lblTelefone);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setBounds(200, 150, 200, 30);
        add(txtTelefone);

        JLabel lblEndereco = new JLabel("Novo Endereço:");
        lblEndereco.setBounds(50, 200, 150, 30);
        add(lblEndereco);

        JTextField txtEndereco = new JTextField();
        txtEndereco.setBounds(200, 200, 200, 30);
        add(txtEndereco);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 300, 100, 30);
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(270, 300, 100, 30);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            String cargo = txtCargo.getText();
            String telefone = txtTelefone.getText();
            String endereco = txtEndereco.getText();

            if (codigo.isEmpty() || cargo.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean sucesso = funcionarioController.atualizar(codigo, cargo, telefone, endereco);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Funcionário alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao alterar o funcionário. Verifique os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}
