package br.com.calculadora;

public class Calculadora {
	public double somar(double a, double b) {
		return a + b;
	}
	public double subtrair(double a, double b) {
	        return a - b;
	    }

	public double multiplicar(double a, double b) {
	        return a * b;
	    }

	 public double dividir(double a, double b) {
	        if (b == 0) {
	            System.out.println("Erro: divisão por zero!");
	            return 0;
	        }
	        return a / b;
	 }
}
