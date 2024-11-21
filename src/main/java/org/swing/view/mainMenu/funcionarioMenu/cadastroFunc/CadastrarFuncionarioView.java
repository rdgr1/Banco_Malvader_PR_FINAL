package org.swing.view.mainMenu.funcionarioMenu.cadastroFunc;

import org.swing.controller.FuncionarioController;
import org.swing.dao.FuncionarioDAO;
import org.swing.model.Endereco;
import org.swing.model.Funcionario;
import org.swing.util.GradientPanel;
import org.swing.util.SwingStyleUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class CadastrarFuncionarioView extends JFrame {
    private final FuncionarioController funcionarioController;
    public CadastrarFuncionarioView() {
        SwingStyleUtil.applyGlobalStyles();
        setContentPane(new GradientPanel());
        setLocationRelativeTo(null);

        this.funcionarioController = new FuncionarioController();

        setTitle("Cadastro de Funcionários");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Cadastro de Funcionário");
        lblTitulo.setBounds(150, 10, 200, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(50, 50, 150, 30);
        add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(200, 50, 200, 30);
        add(txtCodigo);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(50, 90, 150, 30);
        add(lblCargo);

        JTextField txtCargo = new JTextField();
        txtCargo.setBounds(200, 90, 200, 30);
        add(txtCargo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(50, 130, 150, 30);
        add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(200, 130, 200, 30);
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(50, 170, 150, 30);
        add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(200, 170, 200, 30);
        add(txtCpf);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        lblDataNascimento.setBounds(50, 210, 150, 30);
        add(lblDataNascimento);

        JTextField txtDataNascimento = new JTextField();
        txtDataNascimento.setBounds(200, 210, 200, 30);
        add(txtDataNascimento);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(50, 250, 150, 30);
        add(lblTelefone);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setBounds(200, 250, 200, 30);
        add(txtTelefone);

        JLabel lblEndereco = new JLabel("Endereço Completo:");
        lblEndereco.setBounds(50, 290, 150, 30);
        add(lblEndereco);

        JTextField txtEndereco = new JTextField();
        txtEndereco.setBounds(200, 290, 200, 30);
        add(txtEndereco);

        JLabel lblSenhaAdmin = new JLabel("Senha do Administrador:");
        lblSenhaAdmin.setBounds(50, 330, 150, 30);
        add(lblSenhaAdmin);

        JPasswordField txtSenhaAdmin = new JPasswordField();
        txtSenhaAdmin.setBounds(200, 330, 200, 30);
        add(txtSenhaAdmin);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(150, 400, 100, 30);
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(270, 400, 100, 30);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            String cargo = txtCargo.getText();
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String dataNascimentoStr = txtDataNascimento.getText();
            String telefone = txtTelefone.getText();
            String enderecoStr = txtEndereco.getText();
            String senhaAdmin = new String(txtSenhaAdmin.getPassword());

            // Verificação de senha de administrador
            if (!senhaAdmin.equals("admin123")) { // Substituir por uma validação real
                JOptionPane.showMessageDialog(this, "Senha de administrador inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (codigo.isEmpty() || cargo.isEmpty() || nome.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty() ||
                    telefone.isEmpty() || enderecoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);
                Endereco endereco = new Endereco(0, enderecoStr, "", 0, "", "", "");
                Funcionario funcionario = new Funcionario(0, nome, cpf, dataNascimento, telefone, endereco, cargo, codigo, "senhaPadrao");

                boolean sucesso = funcionarioController.salvar(funcionario);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar o funcionário!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}
