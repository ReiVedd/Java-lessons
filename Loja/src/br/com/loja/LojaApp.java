package br.com.loja;

public class LojaApp {

	public static void main(String[] args) {
		Cliente cliente = new Cliente("Gabriel", "Rua do Amparo", "12345-678");
		
		Livro livro = new Livro("Asylum", 25.0, "Madeleine Roux");
		
		Eletronico celular = new Eletronico("S25", 3800.0, 12);
		
		cliente.getCarrinho().adicionarProduto(livro);
		cliente.getCarrinho().adicionarProduto(celular);
		
		double total = cliente.getCarrinho().calcularTotal();
		System.out.println("Total da compra: R$ " + total);
	}

}
