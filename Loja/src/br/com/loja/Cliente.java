package br.com.loja;

public class Cliente {
	private String nome;
	private Endereco endereco;
	private CarrinhoDeCompras carrinho;
	
	public Cliente(String nome, String rua, String cep) {
		this.nome = nome;
		this.endereco = new Endereco(rua, cep);
		this.carrinho = new CarrinhoDeCompras();
	}
	
	public String getNome() {
		return nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
	}
}