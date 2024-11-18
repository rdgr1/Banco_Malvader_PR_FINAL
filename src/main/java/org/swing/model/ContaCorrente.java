package org.swing.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaCorrente extends Conta{
    private double limite;
    private String dataVencimento;

    public ContaCorrente(int numero, String agencia, double saldo,TipoConta tipo_conta, Cliente cliente, double limite, String dataVencimento) {
        super(numero, agencia, saldo, tipo_conta,cliente);
        this.limite = limite;
        this.dataVencimento = dataVencimento;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void sacar(double valor) {
        if(valor <= saldo + limite){
            saldo -= valor;
        } else{
            System.out.println("Limite insuficiente para saque!");
        }
    }
    public double consultarLimite(){
        return limite;
    }
}
