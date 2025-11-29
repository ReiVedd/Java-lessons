package br.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.errors.DAOException;
import br.com.errors.ItemNaoEncontradoException;
import br.com.items.Contato;

public class ContatoDAO {

    public void salvar(Contato c) throws DAOException {
        String sql = "INSERT INTO contatos (nome, telefone, email, endereco) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getEndereco());

            stmt.execute();
            System.out.println("✓ Contato salvo no MySQL!");

        } catch (SQLException e) {
            throw new DAOException("Erro ao salvar contato: " + e.getMessage(), e);
        }
    }

    public List<Contato> listarAtivos() {
        String sql = "SELECT * FROM contatos WHERE ativo = 1 ORDER BY nome";
        List<Contato> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Contato c = mapearContato(rs);
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Contato buscarPorId(int id) throws ItemNaoEncontradoException, DAOException {
        String sql = "SELECT * FROM contatos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearContato(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar", e);
        }
        throw new ItemNaoEncontradoException(id);
    }
    
    public List<Contato> buscarPorNome(String nomeBusca) {
        String sql = "SELECT * FROM contatos WHERE nome LIKE ? AND ativo = 1";
        List<Contato> lista = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nomeBusca + "%");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                lista.add(mapearContato(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void remover(int id) throws DAOException {
       
        String sql = "UPDATE contatos SET ativo = 0 WHERE id = ?";

        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("✓ Contato removido.");
        } catch (SQLException e) {
            throw new DAOException("Erro ao remover", e);
        }
    }


    private Contato mapearContato(ResultSet rs) throws SQLException {
        Contato c = new Contato();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        c.setTelefone(rs.getString("telefone"));
        c.setEmail(rs.getString("email"));
        c.setEndereco(rs.getString("endereco"));
        return c;
    }
}