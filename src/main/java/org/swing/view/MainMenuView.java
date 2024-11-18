package org.swing.view;

import org.swing.controller.ClienteController;
import org.swing.controller.FuncionarioController;
import org.swing.dao.FuncionarioDAO;

import javax.swing.*;

public class MainMenuView extends JFrame {
    private FuncionarioController fun;
    private ClienteController client;
    public MainMenuView() {
        setTitle("Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    
        JButton button1 = new JButton("Funcionario");
        button1.setBounds(100, 100, 100, 30);
        add(button1);
        button1.addActionListener(e -> {
            fun = new FuncionarioController(new FuncionarioDAO()); // Pass the required FuncionarioDAO instance
            new FuncionarioMenuView(fun).setVisible(true);
        });
    
        JButton button2 = new JButton("Cliente");
        button2.setBounds(100, 150, 100, 30);
        add(button2);
        button2.addActionListener(e -> {
            client = new ClienteController(); // Assuming ClienteController does not require parameters
            new ClienteMenuView(client).setVisible(true);
        });
    
        JButton button3 = new JButton("Sair");
        button3.setBounds(100, 200, 100, 30);
        add(button3);
        button3.addActionListener(e -> {
            System.exit(0);
        });
    
        setVisible(true);
    }
}
