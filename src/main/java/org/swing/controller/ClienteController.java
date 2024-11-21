package org.swing.controller;

import org.swing.dao.ClienteDAO;
import org.swing.dao.EnderecoDAO;
import org.swing.dao.UsuarioDAO;
import org.swing.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteController implements IController<Cliente>{
    private ClienteDAO clienteDAO;
    private EnderecoDAO enderecoDAO;
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
        this.enderecoDAO = new EnderecoDAO();
    }

    public boolean salvar(Cliente cliente) {
        try {
            if (cliente.getEndereco() != null && cliente.getEndereco().getId_endereco() == 0) {
                enderecoDAO.save(cliente.getEndereco());
            }

            if (cliente.getSenha() == null || cliente.getSenha().isEmpty()) {
                cliente.setSenha("senha123"); // Defina um valor padrão para a senha
            }

            clienteDAO.save(cliente);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao salvar Cliente: " + e.getMessage());
            return false;
        }
    }


    public Cliente buscarPorCpf(String cpf) {
        try {
            return clienteDAO.findByCpf(cpf);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Cliente por CPF: " + e.getMessage());
            return null;
        }
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
    @Override
    public List<Cliente> listarTodos() {
        return List.of();
    }
    public boolean atualizarCliente(String cpf, String telefone, String endereco) throws SQLException {
        return clienteDAO.atualizarCliente(cpf, telefone, endereco);
    }
}
