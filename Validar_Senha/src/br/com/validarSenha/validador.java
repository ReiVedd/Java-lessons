package br.com.validarSenha;

public class validador {
	public void validarSenha(String senha) throws senhaInvalidaException {
		if (senha.length() <= 8) { 
			throw new senhaInvalidaException("A senha deve ter mais de 8 caracteres.");  
		}
			System.out.println("Senha válida"); 
	}
}
