//nome do pacote
package br.com.data;

//Precisamos importar as classes que vamos usar.
import br.com.model.Conta;
import java.util.ArrayList; // O "vetor inteligente"

public class BancoDeDados {

 // Atributo private que vai guardar todas as contas.
 // É uma lista do tipo 'Conta', mas pode guardar qualquer classe FILHA de Conta.
 private ArrayList<Conta> contas;

 // Construtor que inicializa a lista vazia.
 public BancoDeDados() {
     this.contas = new ArrayList<>();
 }

 // Método para adicionar uma conta ao "banco de dados".
 public void adicionarConta(Conta c) {
     this.contas.add(c);
     System.out.println("Conta de " + c.getTitular() + " adicionada com sucesso.");
 }

 // Método para buscar uma conta pelo número.
 public Conta buscarConta(int numero) {
     // Loop 'for-each' que percorre a lista de contas.
     for (Conta conta : this.contas) {
         if (conta.getNumero() == numero) {
             return conta; // Retorna a conta se encontrar o número.
         }
     }
     return null; // Retorna null se não encontrar.
 }

 // Método para listar todas as contas cadastradas.
 public ArrayList<Conta> listarTodas() {
     return this.contas;
 }
 
 public void transferirInterno(int numeroOrigem, int numeroDestino, double valor) {
	 Conta origem = buscarConta(numeroOrigem);
	 Conta destino = buscarConta(numeroDestino);
	 
	 if(origem == null || destino == null) {
		 System.out.println("Erro: Uma das contas não existe.");
         return;
	 }
	 
	 if (origem.sacar(valor)) {
		 destino.depositar(valor);
		 System.out.println("Transferência interna de R$" + valor + " realizada com sucesso!");
	 } else {
         System.out.println("Erro ao transferir: saldo insuficiente.");
	 }
 }
	 public void transferirExterno(int numeroOrigem, double valor, String destinoDescricao) {
	        Conta origem = buscarConta(numeroOrigem);

	        if (origem == null) {
	            System.out.println("Erro: Conta de origem não encontrada.");
	            return;
	        }

	        if (origem.sacar(valor)) {
	            System.out.println("Transferência externa de R$" + valor + " enviada para " + destinoDescricao + ".");
	        } else {
	            System.out.println("Erro: saldo insuficiente para transferência externa.");
	        }
	    
 }
}