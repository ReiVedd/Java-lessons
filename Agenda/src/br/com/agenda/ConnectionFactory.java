package br.com.agenda; // <--- CONFIRA SE O NOME DO SEU PACOTE É ESSE MESMO

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


    private static final String URL = "jdbc:mysql://localhost:3306/agenda_db?serverTimezone=UTC";
    private static final String USER = "reivedd";
    private static final String PASS = "KNC4Vtsod!f@aQJs"; // <--- SUA SENHA AQUI

    public static Connection getConnection() {
        try {
            // Passo 1: Forçar o carregamento do driver (Resolve o erro "No suitable driver")
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Passo 2: Tentar conectar
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            // Erro: O arquivo .jar do MySQL não está no projeto
            throw new RuntimeException("ERRO: O Driver do MySQL não foi encontrado! Adicione o JAR ao projeto.", e);
        } catch (SQLException e) {
            // Erro: Banco desligado, senha errada ou banco inexistente
            throw new RuntimeException("ERRO: Não foi possível conectar ao banco. Verifique senha e se o MySQL está rodando.", e);
        }
    }
}