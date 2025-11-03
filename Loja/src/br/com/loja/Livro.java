package br.com.loja;

public class Livro extends Produto{
	private String autor;
	
	public Livro(String nome, double precoBase, String autor) {
		super(nome, precoBase);
		this.autor = autor;
	}
	
	@Override
	public double getPrecoFinal() {
		return precoBase * 0.90;
		}
	
	public String getAutor() {
		return autor;
	}
}
