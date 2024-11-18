package org.swing.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaPoupanca extends Conta{
    private double taxaRendimento;

    public ContaPoupanca(int numero, String agencia, double saldo, TipoConta tipo_conta,Cliente cliente, double taxaRendimento) {
        super(numero, agencia, saldo, tipo_conta,cliente);
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void sacar(double valor) {
        if(valor <= saldo){
            saldo -= valor;
        } else{
            System.out.println("Saldo insuficiente para saque.");
        }
    }
    public double calcularRendimento(){
        return saldo*taxaRendimento;
    }
}
