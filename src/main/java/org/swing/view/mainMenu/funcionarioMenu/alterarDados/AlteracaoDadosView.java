package org.swing.view.mainMenu.funcionarioMenu.alterarDados;

import org.swing.view.mainMenu.MainMenuView;

import javax.swing.*;

public class AlteracaoDadosView extends JFrame {

    public AlteracaoDadosView() {
        setTitle("Alteração de Dados");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Alteração de Dados");
        lblTitulo.setBounds(150, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JButton btnAlterarConta = new JButton("Alterar Conta");
        btnAlterarConta.setBounds(150, 60, 200, 30);
        add(btnAlterarConta);

        JButton btnAlterarFuncionario = new JButton("Alterar Funcionário");
        btnAlterarFuncionario.setBounds(150, 100, 200, 30);
        add(btnAlterarFuncionario);

        JButton btnAlterarCliente = new JButton("Alterar Cliente");
        btnAlterarCliente.setBounds(150, 140, 200, 30);
        add(btnAlterarCliente);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 200, 100, 30);
        add(btnVoltar);

        // Ações para os botões
        btnAlterarConta.addActionListener(e -> new AlterarContaView().setVisible(true));
        btnAlterarFuncionario.addActionListener(e -> new AlterarFuncionarioView().setVisible(true));
        btnAlterarCliente.addActionListener(e -> new AlterarClienteView().setVisible(true));
        btnVoltar.addActionListener(e -> {
            dispose();
            new MainMenuView().setVisible(true); // Volta ao menu principal
        });
    }
}
