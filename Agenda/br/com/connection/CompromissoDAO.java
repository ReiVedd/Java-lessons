package br.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.errors.DAOException;
import br.com.errors.ItemNaoEncontradoException;
import br.com.items.Compromisso;

public class CompromissoDAO {

    
    public void salvar(Compromisso c) throws DAOException {
        String sql = "INSERT INTO compromissos (descricao, data_hora, local) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getDescricao());
            stmt.setTimestamp(2, Timestamp.valueOf(c.getDataHora()));
            stmt.setString(3, c.getLocal());

            stmt.execute();
            

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setId(rs.getInt(1));
                }
            }
            
            System.out.println("✓ Compromisso salvo com sucesso!");

        } catch (SQLException e) {

            e.printStackTrace(); 
            throw new DAOException("Erro ao salvar compromisso: " + e.getMessage());
        }
    }


    public List<Compromisso> listarAtivos() {
        String sql = "SELECT * FROM compromissos ORDER BY data_hora";
        List<Compromisso> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Compromisso c = new Compromisso();
                c.setId(rs.getInt("id"));
                c.setDescricao(rs.getString("descricao"));
                c.setLocal(rs.getString("local"));
                
                Timestamp ts = rs.getTimestamp("data_hora");
                if (ts != null) {
                    c.setDataHora(ts.toLocalDateTime());
                }

                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Compromisso buscarPorId(int id) throws ItemNaoEncontradoException, DAOException {
        String sql = "SELECT * FROM compromissos WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Compromisso c = new Compromisso();
                    c.setId(rs.getInt("id"));
                    c.setDescricao(rs.getString("descricao"));
                    c.setLocal(rs.getString("local"));
                    c.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                    return c;
                }
            }
            
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar", e);
        }
        throw new ItemNaoEncontradoException(id);
    }
    
    public void remover(int id) throws DAOException {
        String sql = "DELETE FROM compromissos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("✓ Compromisso removido.");
        } catch (SQLException e) {
            throw new DAOException("Erro ao remover", e);
        }
    }

    public List<Compromisso> listarProximos() {

        String sql = "SELECT * FROM compromissos WHERE data_hora BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 1 DAY)";
        List<Compromisso> lista = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
             while(rs.next()) {
                 Compromisso c = new Compromisso();
                 c.setId(rs.getInt("id"));
                 c.setDescricao(rs.getString("descricao"));
                 c.setLocal(rs.getString("local"));
                 c.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                 lista.add(c);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(Compromisso c) throws DAOException {
        String sql = "UPDATE compromissos SET descricao=?, data_hora=?, local=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
             stmt.setString(1, c.getDescricao());
             stmt.setTimestamp(2, Timestamp.valueOf(c.getDataHora()));
             stmt.setString(3, c.getLocal());
             stmt.setInt(4, c.getId());
             
             stmt.execute();
             System.out.println("✓ Compromisso atualizado.");
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar", e);
        }
    }
}