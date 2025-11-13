package br.com.validarSenha;

import java.util.Scanner;

public class login {

	public static void main(String[] args) {
		Scanner Leitor = new Scanner(System.in);
		validador Validador = new validador();
		
		try {
			System.out.print("Digite sua senha: ");
			String senha = Leitor.nextLine();
			
			Validador.validarSenha(senha);
		} catch (senhaInvalidaException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			System.out.println("Fim do teste de senha.");
			Leitor.close();
		}
	}

}
