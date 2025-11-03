package br.com.loja;

public class Endereco {
	private String rua;
	private String cep;
	
	public Endereco(String rua, String cep) {
		this.rua = rua;
		this.cep = cep;
	}
	
	public String getRua() {
		return rua;
	}
	
	public String getCep() {
		return cep;
	}
}
