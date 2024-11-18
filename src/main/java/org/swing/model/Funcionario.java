package org.swing.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class Funcionario extends Usuario{
    private String codigo;
    private String cargo;
    private String senha;

    public Funcionario(int id_usuario, String nome, String cpf, LocalDate data_nascimento, String telefone,Endereco endereco, String cargo, String codigo, String senha) {
        super(id_usuario, nome, cpf, data_nascimento, telefone,endereco);
        this.cargo = cargo;
        this.codigo = codigo;
        this.senha = senha;
    }

    @Override
    public boolean login(String senha) {
        return false;
    }
    // Métodos específicos de Funcionario
    public void abrirConta() {
        // Implementação para abertura de conta
    }

    public void encerrarConta() {
        // Implementação para encerramento de conta
    }

    public void gerarRelatorioMovimentacao() {
        // Implementação para gerar relatório de movimentação
    }
}
