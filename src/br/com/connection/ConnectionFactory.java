package br.com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


    private static final String URL = "jdbc:mysql://localhost:3306/agenda_db?serverTimezone=UTC";
    private static final String USER = "reivedd";
    private static final String PASS = "KNC4Vtsod!f@aQJs";
    public static Connection getConnection() {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");


            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException e) {
          
            throw new RuntimeException("ERRO: O Driver do MySQL não foi encontrado! Adicione o JAR ao projeto.", e);
        } catch (SQLException e) {

            throw new RuntimeException("ERRO: Não foi possível conectar ao banco. Verifique senha e se o MySQL está rodando.", e);
        }
    }
}