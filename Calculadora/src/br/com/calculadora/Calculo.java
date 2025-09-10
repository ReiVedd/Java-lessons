package br.com.calculadora;
import java.util.Scanner;


public class Calculo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        Calculadora calc = new Calculadora();

        System.out.print("Digite o primeiro número: ");
        double a = sc.nextDouble();

        System.out.print("Digite o segundo número: ");
        double b = sc.nextDouble();

        System.out.print("Escolha a operação (+, -, *, /): ");
        String op = sc.next();

        double resultado = 0;

        switch (op) {
            case "+": resultado = calc.somar(a, b); break;
            case "-": resultado = calc.subtrair(a, b); break;
            case "*": resultado = calc.multiplicar(a, b); break;
            case "/": resultado = calc.dividir(a, b); break;
            default:
                System.out.println("Operação inválida!");
                sc.close();
                return;
        }

        System.out.println("Resultado: " + resultado);
        sc.close();
    }

}
