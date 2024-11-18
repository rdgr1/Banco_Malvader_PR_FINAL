package org.swing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Endereco {
    private int id_endereco;
    private String cep;
    private String local;
    private int numero_casa;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(int id_endereco, String cep, String local, int numero_casa, String bairro, String cidade, String estado) {
        this.id_endereco = id_endereco;
        this.cep = cep;
        this.local = local;
        this.numero_casa = numero_casa;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }
}
