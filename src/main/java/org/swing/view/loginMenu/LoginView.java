package org.swing.view.loginMenu;

import com.formdev.flatlaf.FlatLightLaf;
import org.swing.controller.UsuarioController;
import org.swing.model.Usuario;
import org.swing.util.FontUtil;
import org.swing.util.GradientPanel;
import org.swing.util.RoundedBorder;
import org.swing.util.SwingStyleUtil;
import org.swing.view.mainMenu.MainMenuView;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginView extends JFrame {

    private UsuarioController usuarioController;

    public LoginView() {
        SwingStyleUtil.applyGlobalStyles();
        usuarioController = new UsuarioController();

        // Configurações do Frame
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel com gradiente
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        // Label com o texto "Bem-vindo ao Sistema Bancário!"
        // Fonte: SF Pro Display Regular (https://fonts.google.com/specimen/SF+Pro+Display)
        // Tamanho: 15px
        // Título
        // Carrega o ícone da pasta de recursos (certifique-se de ter o arquivo "icon.png" em "/src/main/resources/icons/")
        ImageIcon icon = new ImageIcon(getClass().getResource("/org/swing/assets/icons8-bank-48.png"));

// Cria o JLabel com texto e ícone
        JLabel lblTitulo = new JLabel("Bem-vindo ao Banco Malvader!", icon, SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(46, 202, 226));
// Estiliza o JLabel
        lblTitulo.setFont(new Font("SF Pro Display Regular", Font.BOLD, 17));
        lblTitulo.setBounds(50, 20, 300, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setIconTextGap(10); // Espaçamento entre o texto e o ícone

// Adiciona ao painel principal
        mainPanel.add(lblTitulo);


        // Campo "Usuário (CPF)"
        JLabel lblUsuario = new JLabel("Usuário (CPF):");
        lblUsuario.setForeground(new Color(46, 202, 226));
        lblUsuario.setBounds(50, 70, 100, 30);
        mainPanel.add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(170, 70, 200, 30);
        txtUsuario.setBackground(new Color(0,0,0,0));
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setBorder(new RoundedBorder(10));
        mainPanel.add(txtUsuario);

        // Campo "Senha"
        JLabel lblSenha = new JLabel("Senha : ");
        lblSenha.setForeground(new Color(46, 202, 226));
        lblSenha.setBounds(50, 120, 100, 30);
        mainPanel.add(lblSenha);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(170, 120, 200, 30);
        txtSenha.setBackground(new Color(0,0,0,0));
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setBorder(new RoundedBorder(10));
        mainPanel.add(txtSenha);

        // Botão "Entrar"
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("SF Pro Display Regular", Font.BOLD, 15));
        btnEntrar.setBounds(150, 180, 100, 40);
        mainPanel.add(btnEntrar);

        // Botão "Sair"
        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("SF Pro Display Regular", Font.BOLD, 15));
        btnSair.setBounds(260, 180, 100, 40);
        mainPanel.add(btnSair);
        // Ação do botão "Entrar"
        btnEntrar.addActionListener(e -> {
            String cpf = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (cpf.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha os campos de login!", "ERRO", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Usuario usuario = usuarioController.buscarPorCpf(cpf);
                if (usuario != null && usuario.login(senha)) {
                    JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                    new MainMenuView().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Credenciais Inválidas!", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados!", "ERRO", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Ação do botão "Sair"
        btnSair.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
    }

