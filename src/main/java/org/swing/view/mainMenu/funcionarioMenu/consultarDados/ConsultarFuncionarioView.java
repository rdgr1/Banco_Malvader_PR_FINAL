package org.swing.view.mainMenu.funcionarioMenu.consultarDados;

import org.swing.controller.FuncionarioController;
import org.swing.model.Funcionario;

import javax.swing.*;
import java.sql.SQLException;

public class ConsultarFuncionarioView extends JFrame {
    private final FuncionarioController funcionarioController;

    public ConsultarFuncionarioView() {
        this.funcionarioController = new FuncionarioController();

        setTitle("Consultar Funcionário");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Consultar Funcionário");
        lblTitulo.setBounds(150, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código do Funcionário:");
        lblCodigo.setBounds(50, 60, 150, 30);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(200, 60, 200, 30);
        add(txtCodigo);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(200, 100, 100, 30);
        add(btnConsultar);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 150, 400, 100);
        textArea.setEditable(false);
        add(textArea);

        btnConsultar.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o código do funcionário!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Funcionario funcionario = funcionarioController.buscarPorCodigo(codigo);
                if (funcionario != null) {
                    textArea.setText("Código: " + funcionario.getCodigo() + "\n" +
                            "Cargo: " + funcionario.getCargo() + "\n" +
                            "Nome: " + funcionario.getNome() + "\n" +
                            "CPF: " + funcionario.getCpf() + "\n" +
                            "Data de Nascimento: " + funcionario.getData_nascimento() + "\n" +
                            "Telefone: " + funcionario.getTelefone() + "\n" +
                            "Endereço: " + funcionario.getEndereco());
                } else {
                    JOptionPane.showMessageDialog(this, "Funcionário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao consultar o funcionário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
