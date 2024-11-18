package org.swing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Transacao {
    private int id;
    private int contaId;
    private String tipo;
    private double valor;
    private Date data;

    public Transacao(int id, int contaId, String tipo, double valor, Date data) {
        this.id = id;
        this.contaId = contaId;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

}
