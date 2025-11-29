package br.com.connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.errors.DAOException;
import br.com.errors.ItemNaoEncontradoException;
import br.com.items.Tarefas;

public class TarefaDAO {

    public void salvar(Tarefas t) throws DAOException {
        String sql = "INSERT INTO tarefas (descricao, data_limite, categoria, progresso, ativo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, t.getDescricao());
            
            if (t.getDataLimite() != null) {
                stmt.setDate(2, Date.valueOf(t.getDataLimite()));
            } else {
                stmt.setNull(2, java.sql.Types.DATE);
            }
            
            stmt.setString(3, t.getCategoria());
            stmt.setInt(4, t.getProgresso());
            stmt.setInt(5, 1);

            stmt.execute();
            
            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()) t.setId(rs.getInt(1));
            }
            
            System.out.println("✓ Tarefa salva no MySQL!");

        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar tarefa", e);
        }
    }

    public List<Tarefas> listarAtivos() {
        String sql = "SELECT * FROM tarefas WHERE ativo = 1 ORDER BY data_limite";
        List<Tarefas> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearTarefa(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Tarefas> listarAtrasadas() {

        String sql = "SELECT * FROM tarefas WHERE ativo = 1 AND data_limite < CURRENT_DATE";
        List<Tarefas> lista = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while(rs.next()) lista.add(mapearTarefa(rs));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Tarefas buscarPorId(int id) throws ItemNaoEncontradoException, DAOException {
        String sql = "SELECT * FROM tarefas WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapearTarefa(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar", e);
        }
        throw new ItemNaoEncontradoException(id);
    }

    public void atualizar(Tarefas t) throws DAOException {
        String sql = "UPDATE tarefas SET descricao=?, progresso=?, ativo=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, t.getDescricao());
            stmt.setInt(2, t.getProgresso());
            stmt.setInt(3, t.isAtivo() ? 1 : 0); 
            stmt.setInt(4, t.getId());
            
            stmt.execute();
            System.out.println("✓ Tarefa atualizada.");
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar", e);
        }
    }

    public void remover(int id) throws DAOException {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("✓ Tarefa removida.");
        } catch (SQLException e) {
            throw new DAOException("Erro ao remover", e);
        }
    }

    private Tarefas mapearTarefa(ResultSet rs) throws SQLException {
        Tarefas t = new Tarefas();
        t.setId(rs.getInt("id"));
        t.setDescricao(rs.getString("descricao"));
        
        Date dataSQL = rs.getDate("data_limite");
        if(dataSQL != null) t.setDataLimite(dataSQL.toLocalDate());
        
        t.setCategoria(rs.getString("categoria"));
        t.setProgresso(rs.getInt("progresso"));
        t.setAtivo(rs.getInt("ativo") == 1); // 1 = true
        return t;
    }
}