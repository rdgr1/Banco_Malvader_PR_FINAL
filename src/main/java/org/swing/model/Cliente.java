package org.swing.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Cliente extends Usuario{
    private String senha;

    public Cliente() {
        super(0, null, null, null, null, null);
        this.senha = null;
    }

    public Cliente(int id_usuario, String nome, String cpf, LocalDate data_nascimento, String telefone, Endereco endereco, String senha) {
        super(id_usuario, nome, cpf, data_nascimento, telefone, endereco);
        this.senha = senha;
    }

    // Métodos específicos de Cliente
    public void consultarSaldo() {
        // Implementação para consultar saldo
    }

    public void depositar(double valor) {
        // Implementação para depósito
    }

    public void sacar(double valor) {
        // Implementação para saque
    }

    public void consultarExtrato() {
        // Implementação para consulta de extrato
    }

    @Override
    public boolean login(String senha) {
        return false;
    }
}
