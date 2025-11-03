package br.com.loja;

public class Eletronico extends Produto{
	private int garantiaMeses;
	
	public Eletronico(String nome, double precoBase, int garantiaMeses) {
		super(nome, precoBase);
		this.garantiaMeses = garantiaMeses;
	}
	
	@Override
	public double getPrecoFinal() {
		return precoBase * 1.15;
		}
	
	public int getGarantiaMeses() {
		return garantiaMeses;
	}
}
