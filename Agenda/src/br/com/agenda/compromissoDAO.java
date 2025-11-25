package br.com.agenda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class compromissoDAO {

 
    public void salvar(compromisso c) {
        String sql = "INSERT INTO compromissos (descricao, data_hora, observacao) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getDescricao());
           
            
            stmt.setTimestamp(2, Timestamp.valueOf(c.getDataHora()));
            stmt.setString(3, c.getObservacao());

            stmt.execute();
            System.out.println("Compromisso salvo com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar: " + e.getMessage());
        }
    }

    public List<compromisso> listar() {
        String sql = "SELECT * FROM compromissos";
        List<compromisso> agenda = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                compromisso c = new compromisso();
                c.setId(rs.getInt("id"));
                c.setDescricao(rs.getString("descricao"));
                c.setObservacao(rs.getString("observacao"));

                c.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());

                agenda.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage());
        }
        return agenda;
    }
}