package org.swing.controller;

import org.swing.dao.TransacaoDAO;
import org.swing.model.Transacao;

import java.sql.SQLException;
import java.util.List;

public class TransacaoController implements IController<Transacao> {
    private TransacaoDAO transacaoDAO;

    public TransacaoController() {
        this.transacaoDAO = new TransacaoDAO();
    }

    @Override
    public void salvar(Transacao transacao) {
        try {
            transacaoDAO.save(transacao);
            System.out.println("Transação registrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao registrar a transação: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Transacao transacao) {
        try {
            transacaoDAO.update(transacao);
            System.out.println("Transação atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a transação: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        try {
            transacaoDAO.delete(id);
            System.out.println("Transação deletada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar a transação: " + e.getMessage());
        }
    }

    @Override
    public Transacao buscarPorId(int id) {
        try {
            return transacaoDAO.findById(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar a transação: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Transacao> listarTodos() {
        System.out.println("Listar todos não é necessário para transações específicas.");
        return null;
    }

    public List<Transacao> listarPorContaId(int contaId) {
        try {
            return transacaoDAO.listarPorContaId(contaId);
        } catch (SQLException e) {
            System.err.println("Erro ao listar transações da conta: " + e.getMessage());
            return null;
        }
    }
}
