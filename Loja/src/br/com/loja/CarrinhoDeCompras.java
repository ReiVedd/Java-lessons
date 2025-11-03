package br.com.loja;

import java.util.ArrayList;

public class CarrinhoDeCompras {
	private  ArrayList<Produto> itens;
	
	public CarrinhoDeCompras() {
		itens = new ArrayList<>();
	}
	
	public void adicionarProduto(Produto produto) {
		itens.add(produto);
	}
	
	public double calcularTotal() {
		double total = 0;
		for (Produto p : itens) {
			total += p.getPrecoFinal();
		}
		return total;
	}
}
