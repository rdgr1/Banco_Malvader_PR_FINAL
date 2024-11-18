package org.swing.model;


import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.time.LocalDate;

@Setter
@Getter
public abstract class Usuario {
    private int id_usuario;
    private String nome;
    private String cpf;
    private LocalDate data_nascimento;
    private String telefone;
    private Endereco endereco;
    private TipoUsuario tipoUsuario;
    private String senha;
    public enum TipoUsuario{
        FUNCIONARIO,CLIENTE
    }
    public Usuario(int id_usuario, String nome, String cpf, LocalDate data_nascimento, String telefone, Endereco endereco) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.senha = senha;
    }
    public abstract boolean login(String senha) throws SQLException;
    public void logout(){
        System.out.println("Usuário " + nome + " desconectado.");
    }
    public String consultarDados(){
        return "Nome: " + nome + "\nCPF: " + cpf + "\nTelefone: " + telefone + "\nEndereco: " + endereco;
    }
}