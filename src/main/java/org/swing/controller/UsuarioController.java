package org.swing.controller;

import org.swing.dao.UsuarioDAO;
import org.swing.model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController implements IController<Usuario> {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    @Override
    public void salvar(Usuario usuario) {
        try {
            usuarioDAO.save(usuario);
            System.out.println("Usuário salvo com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        try {
            usuarioDAO.update(usuario);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o usuário: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        try {
            usuarioDAO.delete(id);
            System.out.println("Usuário deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o usuário: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        try {
            return usuarioDAO.findById(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o usuário: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        // Implementação opcional, se for necessário
        return new ArrayList<>(); // Substituir com lógica para listar todos os usuários
    }
}
