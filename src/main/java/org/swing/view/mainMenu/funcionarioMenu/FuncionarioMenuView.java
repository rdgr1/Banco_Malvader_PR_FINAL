package org.swing.view.mainMenu.funcionarioMenu;


import org.swing.view.mainMenu.funcionarioMenu.abrirConta.AberturaContaView;
import org.swing.view.mainMenu.funcionarioMenu.alterarDados.AlteracaoDadosView;
import org.swing.view.mainMenu.funcionarioMenu.cadastroFunc.CadastrarFuncionarioView;
import org.swing.view.mainMenu.funcionarioMenu.consultarDados.ConsultaDadosView;
import org.swing.view.mainMenu.funcionarioMenu.encerrarConta.EncerramentoContaView;
import org.swing.view.mainMenu.funcionarioMenu.gerarRelatorio.GeracaoRelatorioView;

import javax.swing.*;

public class FuncionarioMenuView extends JFrame {
    public FuncionarioMenuView(){
        setTitle("Menu Funcionário");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        // Implementar o menu de opções
        JLabel title = new JLabel("Opções :");
        add(title);
        // Adicionar botões com as opções de cada menu
        JButton botaoAbrirConta = new JButton("Abrir Conta");
        botaoAbrirConta.setBounds(100,100,100,30);
        add(botaoAbrirConta);
        botaoAbrirConta.addActionListener( e -> new AberturaContaView().setVisible(true));
        JButton botaoEncerrarConta = new JButton("Encerrar Conta");
        botaoEncerrarConta.setBounds(100,150,100,30);
        add(botaoEncerrarConta);
        botaoEncerrarConta.addActionListener( e -> new EncerramentoContaView().setVisible(true));
        JButton botaoConsultarDados = new JButton("Consultar Dados");
        botaoConsultarDados.setBounds(100,200,100,30);
        add(botaoConsultarDados);
        botaoConsultarDados.addActionListener(e -> new ConsultaDadosView().setVisible(true));
        JButton botaoAlterarDados = new JButton("Alterar dados");
        botaoAlterarDados.setBounds(100,250,100,30);
        add(botaoAlterarDados);
        botaoAlterarDados.addActionListener( e -> new AlteracaoDadosView().setVisible(true));

        JButton botaoCadastrarFuncionario = new JButton("Cadastrar Funcionario");
        botaoCadastrarFuncionario.setBounds(100,300,100,30);
        add(botaoCadastrarFuncionario);
        botaoCadastrarFuncionario.addActionListener( e -> new CadastrarFuncionarioView().setVisible(true));
        JButton botaoGerarRelatorio = new JButton("Gerar Relatorio");
        botaoGerarRelatorio.setBounds(100,350,100,30);
        add(botaoGerarRelatorio);
        botaoGerarRelatorio.addActionListener( e -> new GeracaoRelatorioView().setVisible(true));
        JButton botaoSair = new JButton("Sair");
        botaoSair.setBounds(100,400,100,30);
        add(botaoSair);
        botaoSair.addActionListener(e -> dispose());
    }
}
