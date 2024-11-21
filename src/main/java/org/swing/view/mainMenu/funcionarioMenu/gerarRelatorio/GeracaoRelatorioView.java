package org.swing.view.mainMenu.funcionarioMenu.gerarRelatorio;

import org.swing.controller.RelatorioController;
import org.swing.util.GradientPanel;
import org.swing.util.SwingStyleUtil;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class GeracaoRelatorioView extends JFrame {
    private final RelatorioController relatorioController;

    public GeracaoRelatorioView() {
        SwingStyleUtil.applyGlobalStyles();
        setContentPane(new GradientPanel());
        this.relatorioController = new RelatorioController();

        setTitle("Geração de Relatórios");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        JLabel lblTitulo = new JLabel("Geração de Relatório Geral");
        lblTitulo.setBounds(80, 10, 250, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblSenha = new JLabel("Senha do Funcionário:");
        lblSenha.setBounds(50, 50, 150, 30);
        add(lblSenha);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(200, 50, 150, 30);
        add(txtSenha);

        JButton btnGerarRelatorio = new JButton("Gerar Relatório");
        btnGerarRelatorio.setBounds(100, 120, 150, 30);
        add(btnGerarRelatorio);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(260, 120, 100, 30);
        add(btnCancelar);

        btnGerarRelatorio.addActionListener(e -> {
            String senha = new String(txtSenha.getPassword());

            if (senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Insira a senha do funcionário!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                boolean autenticado = relatorioController.validarSenhaFuncionario(senha);
                if (!autenticado) {
                    JOptionPane.showMessageDialog(this, "Senha inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean sucesso = relatorioController.gerarRelatorioGeral();
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Relatório gerado e exportado para Excel com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao gerar o relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}
