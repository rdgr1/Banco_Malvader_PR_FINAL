package org.swing.controller;

import org.swing.dao.FuncionarioDAO;
import org.swing.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioController implements IController<Funcionario>{
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    @Override
    public void salvar(Funcionario funcionario) {
    try {funcionarioDAO.save(funcionario);
    }catch(SQLException e){
        System.err.println("Erro ao salvar Funcionario: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        try {
            funcionarioDAO.update(funcionario);
        }catch(SQLException e){
            System.err.println("Erro ao atualizar Funcionario: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        try {
            funcionarioDAO.delete(id);
        }catch(SQLException e){
            System.err.println("Erro ao deletar Funcionario: " + e.getMessage());
        }
    }

    @Override
    public Funcionario buscarPorId(int id) {
        try {
            return funcionarioDAO.findById(id);
        }catch(SQLException e){
            System.err.println("Erro ao buscar Funcionario: " + e.getMessage());
            return null;
        }
    }
    @Override
    public List<Funcionario> listarTodos() {
        return List.of();
    }
}
