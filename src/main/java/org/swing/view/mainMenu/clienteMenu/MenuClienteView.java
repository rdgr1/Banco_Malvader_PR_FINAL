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

        // Centralizar os elementos
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        int centerX = (windowWidth - 300) / 2;

        // Título
        JLabel lblTitulo = new JLabel("Menu do Cliente");
        lblTitulo.setBounds(centerX, 50, 300, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(18f)); // Aumenta a fonte
        add(lblTitulo);

        // Botão Operações
        JButton btnOperacoes = new JButton("Operações em Conta");
        btnOperacoes.setBounds(centerX, 120, 300, 40); // Posição e tamanho ajustados
        add(btnOperacoes);

        // Botão Sair
        JButton botaoSair = new JButton("Sair");
        botaoSair.setBounds(centerX, 180, 300, 40); // Posição e tamanho ajustados
        add(botaoSair);

        // Botão Encerrar Programa
        JButton btnEncerrar = new JButton("Encerrar Programa");
        btnEncerrar.setBounds(centerX, 240, 300, 40); // Posição e tamanho ajustados
        add(btnEncerrar);

        // Ações dos botões
        btnOperacoes.addActionListener(e -> new OperacoesView().setVisible(true));
        botaoSair.addActionListener(e -> dispose());
        btnEncerrar.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuClienteView().setVisible(true));
    }
}
