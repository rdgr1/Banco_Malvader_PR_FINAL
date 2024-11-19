package org.swing.view.mainMenu.clienteMenu;

import org.swing.controller.ContaController;

import javax.swing.*;

public class MenuClienteView extends JFrame {
    private final ContaController contaController;

    public MenuClienteView() {
        this.contaController = new ContaController();

        setTitle("Menu do Cliente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Menu do Cliente");
        lblTitulo.setBounds(100, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JButton btnOperacoes = new JButton("Operações em Conta");
        btnOperacoes.setBounds(50,200,300,30);
        add(btnOperacoes);


        JButton botaoSair = new JButton("Sair");
        botaoSair.setBounds(50,250,300,30);
        add(botaoSair);

        JButton btnEncerrar = new JButton("Encerrar Programa");
        btnEncerrar.setBounds(50, 290, 300, 30);
        add(btnEncerrar);

        btnOperacoes.addActionListener(e -> new OperacoesView().setVisible(true));

        botaoSair.addActionListener(e -> dispose());
        btnEncerrar.addActionListener(e -> System.exit(0));
    }
}
