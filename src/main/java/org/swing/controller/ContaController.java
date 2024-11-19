package org.swing.controller;

import org.swing.dao.ContaDAO;
import org.swing.model.Conta;

import java.sql.SQLException;
import java.util.List;

public class ContaController implements IController<Conta>{
    private ContaDAO contaDAO;

    @Override
    public void salvar(Conta conta) {
        try{
            contaDAO.save(conta);
        } catch (SQLException e) {
            System.err.println("Erro ao salvar Conta" + e.getMessage());
        }
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
}
