package br.com.agenda;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        compromissoDAO dao = new compromissoDAO();
        
        while (true) {
            System.out.println("\n--- AGENDA ---");
            System.out.println("1. Novo Compromisso");
            System.out.println("2. Listar Compromissos");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            if (opcao == 1) {
                System.out.print("Descrição: ");
                String desc = scanner.nextLine();
                
                System.out.print("Data/Hora (dd/MM/yyyy HH:mm): ");
                String dataStr = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime dataHora = LocalDateTime.parse(dataStr, formatter);

                System.out.print("Observação: ");
                String obs = scanner.nextLine();

                compromisso c = new compromisso(desc, dataHora, obs);
                dao.salvar(c);
            } else if (opcao == 2) {
                System.out.println("\n--- Seus Compromissos ---");
                for (compromisso c : dao.listar()) {
                    System.out.println(c);
                }
            } else if (opcao == 3) {
                break;
            }
        }
        scanner.close();
    }
}