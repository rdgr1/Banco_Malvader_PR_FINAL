package org.swing.controller;

import java.util.List;

public interface IController <T>{
    void salvar(T entity);
    void atualizar(T entity);
    void deletar (int id);
    T buscarPorId(int id);
    List<T> listarTodos();
}
