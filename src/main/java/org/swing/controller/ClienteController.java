package org.swing.controller;

import org.swing.dao.ClienteDAO;
import org.swing.dao.UsuarioDAO;
import org.swing.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteController implements IController<Cliente>{
    private ClienteDAO clienteDAO;
    private UsuarioDAO usuarioDAO;
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public boolean salvar(Cliente cliente) {
        try {
            usuarioDAO.save(cliente);
            clienteDAO.save(cliente);
        } catch (SQLException e) {
            System.err.println("Erro ao salvar cliente" + e.getMessage());
        }
        return false;
    }

    @Override
    public void atualizar(Cliente cliente) {
        try {clienteDAO.update(cliente);
        }catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente" + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        try {
            clienteDAO.delete(id);
        } catch (SQLException e) {
            System.err.println("Erro ao deletar cliente" + e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorId(int id) {
        try {
            return clienteDAO.findById(id);
        }
        catch (SQLException e) {
            System.err.println("Erro ao buscar cliente" + e.getMessage());
            return null;
        }
    }
    public Cliente buscarPorCpf(String cpf) throws SQLException {
        try {
            return clienteDAO.findByCpf(cpf);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente" + e.getMessage());
            return null;
        }
    };
    @Override
    public List<Cliente> listarTodos() {
        return List.of();
    }
    public boolean atualizarCliente(String cpf, String telefone, String endereco) throws SQLException {
        return clienteDAO.atualizarCliente(cpf, telefone, endereco);
    }
}
