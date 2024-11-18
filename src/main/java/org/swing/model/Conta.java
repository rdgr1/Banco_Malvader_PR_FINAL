package org.swing.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Conta {
    protected int numero;
    protected String agencia;
    protected double saldo;
    protected TipoConta tipo_conta;
    protected Cliente cliente;

    public enum TipoConta {
        POUPANCA,CORRENTE
    }

    public TipoConta getTipo_conta() {
        return tipo_conta;
    }

    public Conta(int numero, String agencia, double saldo, TipoConta tipo_conta, Cliente cliente) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
        this.tipo_conta = tipo_conta;
        this.cliente = cliente;
    }
    public abstract void depositar(double valor);

    public abstract void sacar(double valor);

    public double consultarSaldo() {
        return saldo;
    }
}
