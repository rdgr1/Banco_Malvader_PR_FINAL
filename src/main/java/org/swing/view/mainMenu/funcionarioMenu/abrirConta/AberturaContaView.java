package org.swing.view.mainMenu.funcionarioMenu.abrirConta;

import org.swing.controller.ClienteController;
import org.swing.controller.ContaController;
import org.swing.model.*;

import javax.swing.*;
import java.time.LocalDate;

public class AberturaContaView extends JFrame {

    private final ClienteController clienteController = new ClienteController();
    private final ContaController contaController = new ContaController();

    public AberturaContaView() {
        setTitle("Abertura de Conta");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Abertura de Conta Bancária");
        lblTitulo.setBounds(150, 10, 300, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblTipoConta = new JLabel("Tipo de Conta:");
        lblTipoConta.setBounds(50, 50, 100, 30);
        add(lblTipoConta);

        String[] tiposConta = {"Conta Corrente", "Conta Poupança"};
        JComboBox<String> cbTipoConta = new JComboBox<>(tiposConta);
        cbTipoConta.setBounds(150, 50, 200, 30);
        add(cbTipoConta);

        JLabel lblAgencia = new JLabel("Agência:");
        lblAgencia.setBounds(50, 100, 100, 30);
        add(lblAgencia);

        JTextField txtAgencia = new JTextField();
        txtAgencia.setBounds(150, 100, 200, 30);
        add(txtAgencia);

        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        lblNumeroConta.setBounds(50, 150, 150, 30);
        add(lblNumeroConta);

        JTextField txtNumeroConta = new JTextField();
        txtNumeroConta.setBounds(200, 150, 150, 30);
        add(txtNumeroConta);

        JLabel lblNomeCliente = new JLabel("Nome do Cliente:");
        lblNomeCliente.setBounds(50, 200, 150, 30);
        add(lblNomeCliente);

        JTextField txtNomeCliente = new JTextField();
        txtNomeCliente.setBounds(200, 200, 150, 30);
        add(txtNomeCliente);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(50, 250, 150, 30);
        add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(200, 250, 150, 30);
        add(txtCpf);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(50, 300, 150, 30);
        add(lblTelefone);

        JTextField txtTelefone = new JTextField();
        txtTelefone.setBounds(200, 300, 150, 30);
        add(txtTelefone);

        JLabel lblEndereco = new JLabel("Endereço Completo:");
        lblEndereco.setBounds(50, 350, 150, 30);
        add(lblEndereco);

        JTextField txtEndereco = new JTextField();
        txtEndereco.setBounds(200, 350, 350, 30);
        add(txtEndereco);

        JLabel lblLimite = new JLabel("Limite (Corrente):");
        lblLimite.setBounds(50, 400, 150, 30);
        add(lblLimite);

        JTextField txtLimite = new JTextField();
        txtLimite.setBounds(200, 400, 150, 30);
        txtLimite.setEnabled(false); // Desabilitado por padrão
        add(txtLimite);

        JLabel lblVencimento = new JLabel("Vencimento (CC):");
        lblVencimento.setBounds(50, 450, 150, 30);
        add(lblVencimento);

        JTextField txtVencimento = new JTextField();
        txtVencimento.setBounds(200, 450, 150, 30);
        txtVencimento.setEnabled(false); // Desabilitado por padrão
        add(txtVencimento);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(200, 500, 100, 30);
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, 500, 100, 30);
        add(btnCancelar);

        // Habilitar campos específicos para Conta Corrente
        cbTipoConta.addActionListener(e -> {
            if (cbTipoConta.getSelectedItem().equals("Conta Corrente")) {
                txtLimite.setEnabled(true);
                txtVencimento.setEnabled(true);
            } else {
                txtLimite.setEnabled(false);
                txtVencimento.setEnabled(false);
            }
        });

        // Ação do botão "Salvar"
        btnSalvar.addActionListener(e -> {
            try {
                String tipoConta = (String) cbTipoConta.getSelectedItem();
                String agencia = txtAgencia.getText();
                String numeroConta = txtNumeroConta.getText();
                String nomeCliente = txtNomeCliente.getText();
                String cpf = txtCpf.getText();
                String telefone = txtTelefone.getText();
                String enderecoCompleto = txtEndereco.getText();
        
                // Validação básica
                if (nomeCliente.isEmpty() || cpf.isEmpty() || enderecoCompleto.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                // Verificar cliente existente
                Cliente cliente = clienteController.buscarPorCpf(cpf);
                if (cliente == null) {
                    cliente = new Cliente(0, nomeCliente, cpf, LocalDate.now(), telefone, new Endereco(0, enderecoCompleto, "", 0, "", "", ""), "senha123");
                    clienteController.salvar(cliente);
                }
        
                if (tipoConta.equals("Conta Poupança")) {
                    ContaPoupanca poupanca = new ContaPoupanca(0, agencia, 0.0, Conta.TipoConta.POUPANCA, cliente, 0.01);
                    contaController.salvar(poupanca);
                } else if (tipoConta.equals("Conta Corrente")) {
                    double limite = Double.parseDouble(txtLimite.getText());
                    String vencimento = txtVencimento.getText();
                    ContaCorrente corrente = new ContaCorrente(0, agencia, 0.0, Conta.TipoConta.CORRENTE, cliente, limite, vencimento);
                    contaController.salvar(corrente);
                }
        
                JOptionPane.showMessageDialog(this, "Conta criada com sucesso!");
                dispose();
        
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação do botão "Cancelar"
        btnCancelar.addActionListener(e -> dispose());
    }
}
