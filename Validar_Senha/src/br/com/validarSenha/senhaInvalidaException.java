package br.com.validarSenha;

public class senhaInvalidaException extends Exception {
	
	public senhaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
