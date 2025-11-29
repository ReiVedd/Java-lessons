package br.com.connection;

import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        System.out.println("Tentando conectar...");
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            System.out.println("SUCESSO! Conexão aberta com o MySQL.");
        } catch (Exception e) {
            System.out.println("FALHA! O erro é:");
            e.printStackTrace();
        }
    }
}