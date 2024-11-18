package org.swing.controller;

import org.swing.dao.EnderecoDAO;
import org.swing.model.Endereco;

import java.util.List;

public class EnderecoController {

    private EnderecoDAO enderecoDAO;

    public EnderecoController() {
        this.enderecoDAO = new EnderecoDAO();
    }
    public void salvar(Endereco endereco) {
        try {
            if (endereco.getId_endereco() == 0) {
                enderecoDAO.save(endereco);
            } else {
                enderecoDAO.update(endereco);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar endereço: " + e.getMessage(), e);
        }
    }
    public void atualizar(Endereco endereco) {
        try {
            enderecoDAO.update(endereco);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar endereço: " + e.getMessage(), e);
        }
    }
    public Endereco buscarPorId(int id) {
        try {
            return enderecoDAO.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar endereço por ID: " + e.getMessage(), e);
        }
    }
    public void deletar(int id) {
        try {
            enderecoDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar endereço: " + e.getMessage(), e);
        }
    }
}
