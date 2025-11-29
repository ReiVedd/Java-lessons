//nome do pacote
package br.com.model;

//'extends Conta' faz com que ContaPoupanca HERDE tudo da classe Conta.
public class ContaPoupanca extends Conta {

 // Atributo específico desta classe.
 private double taxaJuros;

 // O construtor da filha.
 public ContaPoupanca(int numero, String titular, double taxaJuros) {
     // 'super()' chama o construtor da classe MÃE (Conta) para inicializar
     // os atributos que foram herdados.
     super(numero, titular);
     this.taxaJuros = taxaJuros;
 }

 
 // Método específico desta classe.
 public void renderJuros() {
     double juros = this.getSaldo() * (this.taxaJuros / 100);
     // Reutiliza o método depositar() herdado da classe mãe.
     this.depositar(juros);
 }
 
 @Override
 public void aplicarTaxa() {
	 this.renderJuros();
	 System.out.println("Rendimento de " + this.taxaJuros + "% aplicado à conta poupança " + getNumero());
 }
}