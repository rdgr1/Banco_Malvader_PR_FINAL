package org.swing.view.mainMenu.clienteMenu;

import org.swing.controller.ContaController;
import org.swing.util.GradientPanel;
import org.swing.util.SwingStyleUtil;

import javax.swing.*;

public class MenuClienteView extends JFrame {
    private final ContaController contaController;

    public MenuClienteView() {
        this.contaController = new ContaController();

        // Configurações da janela
        setTitle("Menu do Cliente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Usando layout null
        SwingStyleUtil.applyGlobalStyles();
        setContentPane(new GradientPanel());
        setLocationRelativeTo(null);

        // Criar os componentes
        JLabel lblTitulo = new JLabel("Menu do Cliente - Opções:");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(18f)); // Aumenta a fonte

        JButton btnOperacoes = new JButton("Operações em Conta");
        JButton btnSair = new JButton("Voltar");
        JButton btnEncerrar = new JButton("Sair");

        // Adicionar os componentes à janela
        add(lblTitulo);
        add(btnOperacoes);
        add(btnSair);
        add(btnEncerrar);

        // Ajustar posições ao exibir a janela
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int width = getWidth();
                int height = getHeight();

                // Centraliza o título
                lblTitulo.setBounds((width - 300) / 2, 50, 300, 30);

                // Centraliza os botões
                btnOperacoes.setBounds((width - 300) / 2, 120, 300, 40);
                btnSair.setBounds((width - 300) / 2, 180, 300, 40);
                btnEncerrar.setBounds((width - 300) / 2, 240, 300, 40);
                btnOperacoes.setFocusPainted(false);
                btnOperacoes.setFocusable(false);
                btnSair.setFocusPainted(false);
                btnSair.setFocusable(false);
                btnEncerrar.setFocusPainted(false);
                btnEncerrar.setFocusable(false);
            }
        });

        // Ações dos botões
        btnOperacoes.addActionListener(e -> new OperacoesView().setVisible(true));
        btnSair.addActionListener(e -> dispose());
        btnEncerrar.addActionListener(e -> System.exit(0));
    }
}
