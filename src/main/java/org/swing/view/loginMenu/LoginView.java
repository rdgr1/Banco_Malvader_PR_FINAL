package org.swing.view.loginMenu;

import com.formdev.flatlaf.FlatLightLaf;
import org.swing.controller.UsuarioController;
import org.swing.model.Usuario;
import org.swing.util.FontUtil;
import org.swing.view.mainMenu.MainMenuView;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginView extends JFrame {

    private UsuarioController usuarioController;

    public LoginView() {
        // Configura o Look and Feel e Fonte Personalizada
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            Font customFont = FontUtil.loadFont("/assets/SFPRODISPLAYREGULAR.OTF", 15f);
            FontUtil.applyGlobalFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

// Estiliza o JLabel
        lblTitulo.setFont(new Font("SF Pro Display Regular", Font.BOLD, 17));
        lblTitulo.setBounds(50, 20, 300, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setIconTextGap(10); // Espaçamento entre o texto e o ícone

// Adiciona ao painel principal
        mainPanel.add(lblTitulo);


        // Campo "Usuário (CPF)"
        JLabel lblUsuario = new JLabel("Usuário (CPF):");
        lblUsuario.setBounds(50, 70, 100, 30);
        lblUsuario.setForeground(Color.WHITE);
        mainPanel.add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 70, 200, 30);
        mainPanel.add(txtUsuario);

        // Campo "Senha"
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 120, 100, 30);
        lblSenha.setForeground(Color.WHITE);
        mainPanel.add(lblSenha);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(150, 120, 200, 30);
        mainPanel.add(txtSenha);

        // Botão "Entrar"
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("SF Pro Display Regular", Font.BOLD, 15));
        btnEntrar.setBounds(150, 180, 100, 40);
        btnEntrar.setBackground(new Color(10, 137, 103));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(true);
        mainPanel.add(btnEntrar);

        // Botão "Sair"
        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("SF Pro Display Regular", Font.BOLD, 15));
        btnSair.setBounds(260, 180, 100, 40);
        btnSair.setBackground(new Color(12, 81, 73));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFocusPainted(true);
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
        SwingUtilities.invokeLater(() -> new MainMenuView().setVisible(true));
    }

    // Painel com gradiente
    static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            GradientPaint gradient = new GradientPaint(0, 0, new Color(7, 249, 162), 0, height, new Color(13, 25, 43));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
        }
    }
}
