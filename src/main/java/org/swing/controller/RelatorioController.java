package org.swing.controller;

import org.swing.dao.RelatorioDAO;
import org.swing.model.Relatorio;

import java.sql.SQLException;
import java.util.List;

public class RelatorioController implements IController<Relatorio> {
    private RelatorioDAO relatorioDAO;

    public RelatorioController() {
        this.relatorioDAO = new RelatorioDAO();
    }

    @Override
    public void salvar(Relatorio relatorio) {
        try {
            relatorioDAO.save(relatorio);
            System.out.println("Relatório gerado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Relatorio relatorio) {
        try {
            relatorioDAO.update(relatorio);
            System.out.println("Relatório atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o relatório: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        try {
            relatorioDAO.delete(id);
            System.out.println("Relatório deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o relatório: " + e.getMessage());
        }
    }

    @Override
    public Relatorio buscarPorId(int id) {
        try {
            return relatorioDAO.findById(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o relatório: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Relatorio> listarTodos() {
        System.out.println("Listar todos não é aplicável para relatórios específicos.");
        return null;
    }

    public List<Relatorio> listarPorFuncionario(int funcionarioId) {
        try {
            return relatorioDAO.listarPorFuncionario(funcionarioId);
        } catch (SQLException e) {
            System.err.println("Erro ao listar relatórios do funcionário: " + e.getMessage());
            return null;
        }
    }
}
