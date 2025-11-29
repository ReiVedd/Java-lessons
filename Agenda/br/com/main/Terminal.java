package br.com.main;
//Importando tudo que será usado.
import br.com.data.BancoDeDados;
import br.com.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BancoDeDados banco = new BancoDeDados();

        System.out.println("--- Iniciando Sistema Bancário ---");

        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Criar Conta Corrente");
            System.out.println("2 - Criar Conta Poupança");
            System.out.println("3 - Listar Contas");
            System.out.println("4 - Transferência Interna");
            System.out.println("5 - Transferência Externa (PIX/TED)");
            System.out.println("6 - Aplicar Taxas/Rendimentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Número da conta: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Titular: ");
                    String tit = sc.nextLine();
                    System.out.print("Limite do cheque especial: ");
                    double lim = sc.nextDouble();
                    banco.adicionarConta(new ContaCorrente(num, tit, lim));
                }

                case 2 -> {
                    System.out.print("Número da conta: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Titular: ");
                    String tit = sc.nextLine();
                    System.out.print("Taxa de juros (%): ");
                    double taxa = sc.nextDouble();
                    banco.adicionarConta(new ContaPoupanca(num, tit, taxa));
                }

                case 3 -> {
                    ArrayList<Conta> contas = banco.listarTodas();
                    if (contas.isEmpty()) {
                        System.out.println("Nenhuma conta cadastrada.");
                    } else {
                        for (Conta c : contas) {
                            System.out.println("Conta " + c.getNumero() + " | Titular: " + c.getTitular() + " | Saldo: R$" + c.getSaldo());
                        }
                    }
                }

                case 4 -> {
                    System.out.print("Conta origem: ");
                    int origem = sc.nextInt();
                    System.out.print("Conta destino: ");
                    int destino = sc.nextInt();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    banco.transferirInterno(origem, destino, valor);
                }

                case 5 -> {
                    System.out.print("Conta origem: ");
                    int origem = sc.nextInt();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Descrição do destino externo (ex: Banco X - Cliente Y): ");
                    String desc = sc.nextLine();
                    banco.transferirExterno(origem, valor, desc);
                }

                case 6 -> {
                    for (Conta c : banco.listarTodas()) {
                        c.aplicarTaxa();
                    }
                }

                case 0 -> System.out.println("Encerrando o sistema...");

                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}