package org.swing.view.mainMenu.funcionarioMenu.consultarDados;

import org.swing.util.GradientPanel;
import org.swing.util.SwingStyleUtil;
import org.swing.view.mainMenu.MainMenuView;

import javax.swing.*;

public class ConsultaDadosView extends JFrame {

        public ConsultaDadosView() {
            SwingStyleUtil.applyGlobalStyles();
            setContentPane(new GradientPanel());
            setTitle("Consulta de Dados");
            setSize(500, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);

            JLabel lblTitulo = new JLabel("Consulta de Dados");
            lblTitulo.setBounds(150, 10, 200, 30);
            lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblTitulo);

            JButton btnConsultarConta = new JButton("Consultar Conta");
            btnConsultarConta.setBounds(150, 60, 200, 30);
            add(btnConsultarConta);

            JButton btnConsultarFuncionario = new JButton("Consultar Funcionário");
            btnConsultarFuncionario.setBounds(150, 100, 200, 30);
            add(btnConsultarFuncionario);

            JButton btnConsultarCliente = new JButton("Consultar Cliente");
            btnConsultarCliente.setBounds(150, 140, 200, 30);
            add(btnConsultarCliente);

            JButton btnVoltar = new JButton("Voltar");
            btnVoltar.setBounds(200, 200, 100, 30);
            add(btnVoltar);

            // Ações para os botões
            btnConsultarConta.addActionListener(e -> new ConsultarContaView().setVisible(true));
            btnConsultarFuncionario.addActionListener(e -> new ConsultarFuncionarioView().setVisible(true));
            btnConsultarCliente.addActionListener(e -> new ConsultarClienteView().setVisible(true));
            btnVoltar.addActionListener(e -> {
                dispose();
                new MainMenuView().setVisible(true); // Volta ao menu principal
            });
        }
}
