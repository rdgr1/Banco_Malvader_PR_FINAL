package org.swing.view.mainMenu.funcionarioMenu;


import org.swing.util.GradientPanel;
import org.swing.util.SwingStyleUtil;
import org.swing.view.mainMenu.funcionarioMenu.abrirConta.AberturaContaView;
import org.swing.view.mainMenu.funcionarioMenu.alterarDados.AlteracaoDadosView;
import org.swing.view.mainMenu.funcionarioMenu.cadastroFunc.CadastrarFuncionarioView;
import org.swing.view.mainMenu.funcionarioMenu.consultarDados.ConsultaDadosView;
import org.swing.view.mainMenu.funcionarioMenu.encerrarConta.EncerramentoContaView;
import org.swing.view.mainMenu.funcionarioMenu.gerarRelatorio.GeracaoRelatorioView;

import javax.swing.*;

public class FuncionarioMenuView extends JFrame {
    public FuncionarioMenuView(){
        SwingStyleUtil.applyGlobalStyles();
        setContentPane(new GradientPanel());
        setTitle("Menu Funcionário");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        int buttonWidth = 200;
        int buttonHeight = 40;
        int centerX = (getWidth() - buttonWidth) / 2;
        // Implementar o menu de opções
        JLabel title = new JLabel("Menu Funcionario - Opções :");
        add(title);
        // Adicionar botões com as opções de cada menu
        JButton botaoAbrirConta = new JButton("Abrir Conta");
        botaoAbrirConta.setBounds(centerX,100,200,30);
        botaoAbrirConta.setFocusable(false);
        botaoAbrirConta.setFocusPainted(false);
        add(botaoAbrirConta);
        botaoAbrirConta.addActionListener( e -> new AberturaContaView().setVisible(true));
        JButton botaoEncerrarConta = new JButton("Encerrar Conta");
        botaoEncerrarConta.setBounds(centerX,150,200,30);
        botaoEncerrarConta.setFocusable(false);
        botaoEncerrarConta.setFocusPainted(false);
        add(botaoEncerrarConta);
        botaoEncerrarConta.addActionListener( e -> new EncerramentoContaView().setVisible(true));
        JButton botaoConsultarDados = new JButton("Consultar Dados");
        botaoConsultarDados.setBounds(centerX,200,200,30);
        botaoConsultarDados.setFocusable(false);
        botaoConsultarDados.setFocusPainted(false);
        add(botaoConsultarDados);
        botaoConsultarDados.addActionListener(e -> new ConsultaDadosView().setVisible(true));
        JButton botaoAlterarDados = new JButton("Alterar dados");
        botaoAlterarDados.setBounds(centerX,250,200,30);
        botaoAlterarDados.setFocusPainted(false);
        botaoAlterarDados.setFocusable(false);
        add(botaoAlterarDados);
        botaoAlterarDados.addActionListener( e -> new AlteracaoDadosView().setVisible(true));

        JButton botaoCadastrarFuncionario = new JButton("Cadastrar Funcionario");
        botaoCadastrarFuncionario.setBounds(centerX,300,200,30);
        botaoCadastrarFuncionario.setFocusPainted(false);
        botaoCadastrarFuncionario.setFocusable(false);
        add(botaoCadastrarFuncionario);
        botaoCadastrarFuncionario.addActionListener( e -> new CadastrarFuncionarioView().setVisible(true));
        JButton botaoGerarRelatorio = new JButton("Gerar Relatorio");
        botaoGerarRelatorio.setBounds(centerX,350,200,30);
        botaoGerarRelatorio.setFocusPainted(false);
        botaoGerarRelatorio.setFocusable(false);
        add(botaoGerarRelatorio);
        botaoGerarRelatorio.addActionListener( e -> new GeracaoRelatorioView().setVisible(true));
        JButton botaoEncerrar = new JButton("Voltar");
        botaoEncerrar.setBounds(centerX+30,400,150,30);
        botaoEncerrar.setFocusable(false);
        botaoEncerrar.setFocusPainted(false);
        add(botaoEncerrar);
        botaoEncerrar.addActionListener(e -> dispose());
        JButton botaoSair = new JButton("Sair");
        botaoSair.setBounds(centerX+50,450,100,30);
        botaoSair.setFocusable(false);
        botaoSair.setFocusPainted(false);
        add(botaoSair);
        botaoSair.addActionListener(e -> System.exit(0));
    }
}
