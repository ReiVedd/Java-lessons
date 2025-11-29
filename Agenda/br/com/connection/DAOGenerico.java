package br.com.connection;

import java.util.List;

import br.com.errors.ValidacaoException;
import br.com.items.ItemAgenda;
import br.com.items.Tarefas;
import br.com.errors.*;

/**
 * Interface genérica para operações de DAO
 * Demonstra: Interface, Generics (Conceito avançado)
 */
public interface DAOGenerico<T extends ItemAgenda> {
    
    /**
     * Salva um item no banco de dados
     */
    void salvar(T item) throws DAOException, ValidacaoException;
    
    /**
     * Atualiza um item existente
     */
    void atualizar(Tarefas t) throws DAOException, ValidacaoException;
    
    /**
     * Remove um item pelo ID
     */
    void remover(int id) throws DAOException;
    
    /**
     * Busca um item pelo ID
     */
    T buscarPorId(int id) throws ItemNaoEncontradoException;
    
    /**
     * Lista todos os itens
     */
    List<T> listar();
    
    /**
     * Lista apenas itens ativos
     */
    List<T> listarAtivos();
}