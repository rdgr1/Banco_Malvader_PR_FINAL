package org.swing.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Relatorio {
    private int id; // Adiciona o campo id para corresponder à tabela relatorio no banco
    private String tipo;
    private LocalDateTime dataGeracao;
    private String dados;
    private int idFuncionario; // ID do funcionário que gerou o relatório

    public Relatorio(String tipo, LocalDateTime dataGeracao, String dados, int idFuncionario) {
        this.tipo = tipo;
        this.dataGeracao = dataGeracao;
        this.dados = dados;
        this.idFuncionario = idFuncionario;
    }

    public Relatorio(int id, String tipo, LocalDateTime dataGeracao, String dados, int idFuncionario) {
        this.id = id;
        this.tipo = tipo;
        this.dataGeracao = dataGeracao;
        this.dados = dados;
        this.idFuncionario = idFuncionario;
    }
}
