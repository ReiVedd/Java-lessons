//nome do pacote
package br.com.model;

//A classe é PUBLIC para que outras classes de outros pacotes possam enxergá-la.
public abstract class Conta {

 // Atributos PROTEGIDOS com 'private'. Só a própria classe pode acessá-los diretamente.
 private int numero;
 private String titular;
 protected double saldo;

 // Construtor para garantir que toda conta tenha número e titular.
 public Conta(int numero, String titular) {
     this.numero = numero;
     this.titular = titular;
     this.saldo = 0; // Saldo inicial sempre é zero.
 }

 // Métodos PÚBLICOS que servem como interface segura para interagir com os atributos.

 public void depositar(double valor) {
     if (valor > 0) {
         this.saldo += valor;
     } else {
         System.out.println("Erro: Valor de depósito deve ser positivo.");
     }
 }

 public boolean sacar(double valor) {
     if (valor > 0 && this.saldo >= valor) {
         this.saldo -= valor;
         System.out.println("Saque de R$" + valor + " realizado na conta " + numero);
         return true; // Saque bem-sucedido
     } else {
         System.out.println("Erro: Saldo insuficiente ou valor inválido.");
     return false; // Saldo insuficiente ou valor inválido
     }
 }

 // Getters para permitir a LEITURA (mas não a escrita) dos atributos.
 public int getNumero() {
     return numero;
 }

 public String getTitular() {
     return titular;
 }

 public double getSaldo() {
     return saldo;
 }
 
 public abstract void aplicarTaxa();
}