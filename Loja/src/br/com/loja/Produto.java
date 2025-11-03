package br.com.loja;

public abstract class Produto {
	protected String nome;
	protected double precoBase;
	
	public Produto(String nome, double precoBase) {
		this.nome = nome;
		this.precoBase = precoBase;
		}
	
	public abstract double getPrecoFinal();
	
	public String getNome() {
		return nome;
	}
	
	public double getPrecoBase() {
		return precoBase;
	}
}
