package org.swing.controller;

import org.swing.dao.ContaDAO;
import org.swing.model.Conta;

import java.sql.SQLException;
import java.util.List;

public class ContaController implements IController<Conta>{
    private ContaDAO contaDAO;

    @Override
    public boolean salvar(Conta conta) {
        try{
            contaDAO.save(conta);
        } catch (SQLException e) {
            System.err.println("Erro ao salvar Conta" + e.getMessage());
        }
        return false;
    }

    @Override
    public void atualizar(Conta conta) {
        try {
            contaDAO.update(conta);
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Conta" + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        try {
            contaDAO.delete(id);
        } catch (SQLException e) {
            System.err.println("Erro ao deletar Conta" + e.getMessage());
        }
    }

    @Override
    public Conta buscarPorId(int id) {
        try {
            return contaDAO.findById(id);
        }catch (SQLException e){
            System.err.println("Erro ao buscar Conta" + e.getMessage());
            return null;
        }
    }
    public boolean encerrarConta(String numeroConta) throws SQLException {
        return contaDAO.encerrarConta(numeroConta);
    }
    @Override
    public List<Conta> listarTodos() {
        return List.of();
    }

    public boolean atualizar(String numeroConta, double limite, String vencimento) {
        return false;
    }

    public Conta buscarPorNumero(String numeroConta) {
        return null;
    }
    public double consultarSaldo(String numeroConta, String senha) throws SQLException {
        return contaDAO.consultarSaldo(numeroConta, senha);
    }

    public void depositar(String numeroConta, double valor) throws SQLException {
        contaDAO.depositar(numeroConta, valor);
    }

    public boolean sacar(String numeroConta, String senha, double valor) throws SQLException {
        return contaDAO.sacar(numeroConta, senha, valor);
    }

    public boolean gerarExtrato(String numeroConta, String senha) throws SQLException {
        return contaDAO.gerarExtrato(numeroConta, senha);
    }

    public double consultarLimite(String numeroConta, String senha) throws SQLException {
        return contaDAO.consultarLimite(numeroConta, senha);
    }
}
