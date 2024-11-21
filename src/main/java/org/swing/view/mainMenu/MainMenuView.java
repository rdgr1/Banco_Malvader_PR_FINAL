package org.swing.view.mainMenu;

import org.swing.controller.ClienteController;
import org.swing.controller.FuncionarioController;
import org.swing.util.FontUtil;
import org.swing.util.GradientPanel;
import org.swing.util.RoundedBorder;
import org.swing.util.SwingStyleUtil;
import org.swing.view.mainMenu.clienteMenu.MenuClienteView;
import org.swing.view.mainMenu.funcionarioMenu.FuncionarioMenuView;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
    private FuncionarioController fun;
    private ClienteController client;
    public MainMenuView() {
        SwingStyleUtil.applyGlobalStyles();
        setContentPane(new GradientPanel());
        setTitle("Menu Principal");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        int buttonWidth = 200;
        int buttonHeight = 40;
        int centerX = (getWidth() - buttonWidth) / 2;
        JButton button1 = new JButton("Funcionario");
        button1.setBounds(centerX, 100, 200, 40);
        button1.setBackground(new Color(0,0,0,0));
        add(button1);
        button1.addActionListener(e -> {
            new FuncionarioMenuView().setVisible(true);
        });

        JButton button2 = new JButton("Cliente");
        button2.setBounds(centerX, 150, 200, 40);
        add(button2);
        button2.addActionListener(e -> {
            client = new ClienteController(); // Assuming ClienteController does not require parameters
            new MenuClienteView().setVisible(true);
        });

        JButton button3 = new JButton("Sair");
        button3.setBounds(centerX+50, 200, 100, 30);
        button3.setBackground(new Color(0,0,0,0));
        button3.setBorder(new RoundedBorder(20));
        add(button3);
        button3.addActionListener(e -> {
            System.exit(0);
        });

        setVisible(true);
    }
}
