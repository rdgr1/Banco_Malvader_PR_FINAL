package org.swing.view.mainMenu.funcionarioMenu.abrirConta;

import org.swing.controller.ClienteController;
import org.swing.controller.ContaController;
import org.swing.model.*;
import org.swing.util.GradientPanel;
import org.swing.util.SwingStyleUtil;

import javax.swing.*;
import java.time.LocalDate;

public class AberturaContaView extends JFrame {
    private final ContaController contaController;
    private final ClienteController clienteController;

    public AberturaContaView() {
        setContentPane(new GradientPanel());
        SwingStyleUtil.applyGlobalStyles();
        this.contaController = new ContaController();
        this.clienteController = new ClienteController();
        setTitle("Abertura de Conta Bancária");
        setSize(800, 900); // Aumentei o tamanho para garantir que todos os elementos fiquem visíveis
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblTipoConta = new JLabel("Tipo de Conta:");
        lblTipoConta.setBounds(50, 20, 150, 30);
        add(lblTipoConta);

        String[] tiposConta = {"Conta Poupança", "Conta Corrente"};
        JComboBox<String> cbTipoConta = new JComboBox<>(tiposConta);
        cbTipoConta.setBounds(200, 20, 200, 30);
        add(cbTipoConta);

        JLabel lblAgencia = new JLabel("Agência:");
        lblAgencia.setBounds(50, 70, 150, 30);
        add(lblAgencia);

        JTextField txtAgencia = new JTextField();
        txtAgencia.setBounds(200, 70, 200, 30);
        add(txtAgencia);

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        lblNumeroConta.setBounds(50, 120, 150, 30);
        add(lblNumeroConta);

        JTextField txtNumeroConta = new JTextField();
        txtNumeroConta.setBounds(200, 120, 200, 30);
        add(txtNumeroConta);

        JLabel lblNomeCliente = new JLabel("Nome do Cliente:");
        lblNomeCliente.setBounds(50, 170, 150, 30);
        add(lblNomeCliente);

        JTextField txtNomeCliente = new JTextField();
        txtNomeCliente.setBounds(200, 170, 200, 30);
        add(txtNomeCliente);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(50, 220, 150, 30);
        add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(200, 220, 200, 30);
        add(txtCpf);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(50, 270, 150, 30);
        add(lblTelefone);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setBounds(200, 270, 200, 30);
        add(txtTelefone);

        JLabel lblCep = new JLabel("CEP:");
        lblCep.setBounds(50, 320, 150, 30);
        add(lblCep);

        JTextField txtCep = new JTextField();
        txtCep.setBounds(200, 320, 200, 30);
        add(txtCep);

        JLabel lblLocal = new JLabel("Logradouro:");
        lblLocal.setBounds(50, 370, 150, 30);
        add(lblLocal);

        JTextField txtLocal = new JTextField();
        txtLocal.setBounds(200, 370, 200, 30);
        add(txtLocal);

        JLabel lblNumeroCasa = new JLabel("Número da Casa:");
        lblNumeroCasa.setBounds(50, 420, 150, 30);
        add(lblNumeroCasa);

        JTextField txtNumeroCasa = new JTextField();
        txtNumeroCasa.setBounds(200, 420, 200, 30);
        add(txtNumeroCasa);

        JLabel lblBairro = new JLabel("Bairro:");
        lblBairro.setBounds(50, 470, 150, 30);
        add(lblBairro);

        JTextField txtBairro = new JTextField();
        txtBairro.setBounds(200, 470, 200, 30);
        add(txtBairro);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(50, 520, 150, 30);
        add(lblCidade);

        JTextField txtCidade = new JTextField();
        txtCidade.setBounds(200, 520, 200, 30);
        add(txtCidade);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(50, 570, 150, 30);
        add(lblEstado);

        JTextField txtEstado = new JTextField();
        txtEstado.setBounds(200, 570, 200, 30);
        add(txtEstado);

        JLabel lblLimite = new JLabel("Limite (Corrente):");
        lblLimite.setBounds(50, 620, 150, 30);
        add(lblLimite);

        JTextField txtLimite = new JTextField();
        txtLimite.setBounds(200, 620, 200, 30);
        txtLimite.setEnabled(false);
        add(txtLimite);

        JLabel lblVencimento = new JLabel("Vencimento (CC):");
        lblVencimento.setBounds(50, 670, 150, 30);
        add(lblVencimento);

        JTextField txtVencimento = new JTextField();
        txtVencimento.setBounds(200, 670, 200, 30);
        txtVencimento.setEnabled(false);
        add(txtVencimento);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 720, 150, 30);
        add(lblSenha);

        JTextField txtSenha = new JTextField();
        txtSenha.setBounds(200, 720, 200, 30);
        add(txtSenha);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(200, 780, 100, 40);
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(310, 780, 100, 40);
        add(btnCancelar);
        btnCancelar.addActionListener(e -> dispose());

        cbTipoConta.addActionListener(e -> {
            boolean isCorrente = cbTipoConta.getSelectedItem().equals("Conta Corrente");
            txtLimite.setEnabled(isCorrente);
            txtVencimento.setEnabled(isCorrente);
        });

        btnSalvar.addActionListener(e -> {
            if (txtCpf.getText().length() != 11) {
                JOptionPane.showMessageDialog(this, "O CPF deve conter 11 dígitos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                Endereco endereco = new Endereco(
                        0,
                        txtCep.getText(),
                        txtLocal.getText(),
                        Integer.parseInt(txtNumeroCasa.getText()),
                        txtBairro.getText(),
                        txtCidade.getText(),
                        txtEstado.getText()
                );

                Cliente cliente = clienteController.buscarPorCpf(txtCpf.getText());
                if (cliente == null) {
                    String senha = txtSenha.getText();
                    if (senha.isEmpty()) {
                        senha = "senha123"; // Use uma senha padrão se nenhuma for fornecida
                    }

                    cliente = new Cliente(
                            0,
                            txtNomeCliente.getText(),
                            txtCpf.getText(),
                            LocalDate.now(),
                            txtTelefone.getText(),
                            endereco,
                            senha
                    );
                    clienteController.salvar(cliente);
                }

                if (cbTipoConta.getSelectedItem().equals("Conta Poupança")) {
                    ContaPoupanca poupanca = new ContaPoupanca(
                            0,
                            txtAgencia.getText(),
                            0.0,
                            Conta.TipoConta.POUPANCA,
                            cliente,
                            0.01
                    );
                    contaController.salvar(poupanca);
                } else if (cbTipoConta.getSelectedItem().equals("Conta Corrente")) {
                    ContaCorrente corrente = new ContaCorrente(
                            0,
                            txtAgencia.getText(),
                            0.0,
                            Conta.TipoConta.CORRENTE,
                            cliente,
                            Double.parseDouble(txtLimite.getText()),
                            txtVencimento.getText()
                    );
                    contaController.salvar(corrente);
                }

                JOptionPane.showMessageDialog(this, "Conta criada com sucesso!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
