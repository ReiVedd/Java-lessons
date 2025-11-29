package br.com.agendaApp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


import br.com.connection.CompromissoDAO;
import br.com.connection.ContatoDAO;
import br.com.connection.TarefaDAO;
import br.com.errors.DAOException;
import br.com.errors.ItemNaoEncontradoException;
import br.com.errors.ValidacaoException;
import br.com.items.Compromisso;
import br.com.items.Contato;
import br.com.items.Tarefas;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    

    private static CompromissoDAO compromissoDAO = new CompromissoDAO();
    private static ContatoDAO contatoDAO = new ContatoDAO();
    private static TarefaDAO tarefaDAO = new TarefaDAO();

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GERENCIAMENTO DE AGENDA   ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        while (true) {
            try {
                exibirMenuPrincipal();
                int opcao = lerInteiro("Escolha uma opção: ");
                
                switch (opcao) {
                    case 1 -> menuCompromissos();
                    case 2 -> menuContatos();
                    case 3 -> menuTarefas();
                    case 4 -> exibirResumo();
                    case 0 -> {
                        System.out.println("\n👋 Até logo!");
                        return;
                    }
                    default -> System.out.println("❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro inesperado no menu: " + e.getMessage());
                e.printStackTrace(); 
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("           MENU PRINCIPAL");
        System.out.println("=".repeat(40));
        System.out.println("1. 📅 Gerenciar Compromissos");
        System.out.println("2. 👥 Gerenciar Contatos");
        System.out.println("3. ✓ Gerenciar Tarefas");
        System.out.println("4. 📊 Resumo da Agenda");
        System.out.println("0. 🚪 Sair");
        System.out.println("=".repeat(40));
    }

    private static void menuCompromissos() {
        while (true) {
            System.out.println("\n--- COMPROMISSOS ---");
            System.out.println("1. Novo Compromisso");
            System.out.println("2. Listar Compromissos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Remover Compromisso");
            System.out.println("5. Ver Compromissos Próximos");
            System.out.println("0. Voltar");
            
            int opcao = lerInteiro("Escolha: ");
            
            try {
                switch (opcao) {
                    case 1 -> criarCompromisso();
                    case 2 -> listarCompromissos();
                    case 3 -> buscarCompromisso();
                    case 4 -> removerCompromisso();
                    case 5 -> listarCompromissosProximos();
                    case 0 -> { return; }
                    default -> System.out.println("❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }
    }

    
    private static void criarCompromisso() {
         try {
            System.out.println("\n=== NOVO COMPROMISSO ===");
            System.out.print("Descrição: ");
            String descricao = sc.nextLine();
            System.out.print("Data/Hora (dd/MM/yyyy HH:mm): ");
            String dataStr = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataStr, formatter);
            System.out.print("Local (opcional): ");
            String local = sc.nextLine();
            
            Compromisso c = new Compromisso(descricao, dataHora, local);
            compromissoDAO.salvar(c);
        } catch (Exception e) { System.out.println("❌ Erro: " + e.getMessage()); }
    }
    
    private static void listarCompromissos() {
        var lista = compromissoDAO.listarAtivos();
        if (lista.isEmpty()) System.out.println("📭 Vazio.");
        else lista.forEach(System.out::println);
    }
    
    private static void buscarCompromisso() {
        try {
            System.out.println(compromissoDAO.buscarPorId(lerInteiro("ID: ")));
        } catch (Exception e) { System.out.println("❌ " + e.getMessage()); }
    }
    
    private static void removerCompromisso() {
        try {
            compromissoDAO.remover(lerInteiro("ID: "));
        } catch (Exception e) { System.out.println("❌ " + e.getMessage()); }
    }
    
    private static void listarCompromissosProximos() {
        var lista = compromissoDAO.listarProximos();
        if (lista.isEmpty()) System.out.println("📭 Nenhum próximo.");
        else lista.forEach(System.out::println);
    }


    private static void menuTarefas() {
        while (true) {
            System.out.println("\n--- TAREFAS ---");
            System.out.println("1. Nova Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Atualizar Progresso");
            System.out.println("4. Concluir Tarefa");
            System.out.println("5. Ver Tarefas Atrasadas");
            System.out.println("6. Remover Tarefa");
            System.out.println("0. Voltar");
            
            int opcao = lerInteiro("Escolha: ");
            
            try {
                switch (opcao) {
                    case 1 -> criarTarefa();
                    case 2 -> listarTarefas();
                    case 3 -> atualizarProgressoTarefa();
                    case 4 -> concluirTarefa();
                    case 5 -> listarTarefasAtrasadas();
                    case 6 -> removerTarefa();
                    case 0 -> { return; }
                    default -> System.out.println("❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }
    }

    private static void criarTarefa() throws ValidacaoException, DAOException {
        try {
            System.out.println("\n=== NOVA TAREFA ===");
            
            System.out.print("Descrição: ");
            String descricao = sc.nextLine();
            
            System.out.print("Data Limite (dd/MM/yyyy) ou Enter para sem prazo: ");
            String dataStr = sc.nextLine();
            LocalDate dataLimite = null;
            if (!dataStr.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataLimite = LocalDate.parse(dataStr, formatter);
            }
            
            System.out.print("Categoria (opcional): ");
            String categoria = sc.nextLine();
            
            Tarefas t = new Tarefas(descricao, dataLimite, categoria);
            tarefaDAO.salvar(t); 
            
        } catch (DateTimeParseException e) {
            System.out.println("❌ Formato de data inválido!");
        }
    }

    private static void listarTarefas() {
        System.out.println("\n=== SUAS TAREFAS ===");
        var lista = tarefaDAO.listarAtivos(); 
        
        if (lista.isEmpty()) {
            System.out.println("📭 Nenhuma tarefa cadastrada.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private static void atualizarProgressoTarefa() throws ItemNaoEncontradoException, DAOException, ValidacaoException {
        int id = lerInteiro("ID da tarefa: ");
        
        Tarefas t = tarefaDAO.buscarPorId(id);
        
        System.out.println("Tarefa: " + t);
        System.out.println("Progresso atual: " + t.getProgresso() + "%");
        
        int progresso = lerInteiro("Novo progresso (0-100): ");
        t.setProgresso(progresso);
        tarefaDAO.atualizar(t);
    }

    private static void concluirTarefa() throws ItemNaoEncontradoException, DAOException, ValidacaoException {
        int id = lerInteiro("ID da tarefa: ");
        Tarefas t = tarefaDAO.buscarPorId(id);
        
        t.setProgresso(100); 
        t.setAtivo(false);
        
        tarefaDAO.atualizar(t);
        System.out.println("🎉 Tarefa concluída!");
    }

    private static void listarTarefasAtrasadas() {
        System.out.println("\n=== TAREFAS ATRASADAS ===");
        var lista = tarefaDAO.listarAtrasadas();
        

        if (lista.isEmpty()) {
            System.out.println("✓ Nenhuma tarefa atrasada!");
        } else {
            lista.forEach(t -> System.out.println("⚠️ " + t));
        }
    }

    private static void removerTarefa() throws DAOException {
        int id = lerInteiro("ID da tarefa a remover: ");
        tarefaDAO.remover(id);
    }
    private static void menuContatos() {
        while (true) {
            System.out.println("\n--- 👥 GERENCIAR CONTATOS ---");
            System.out.println("1. Novo Contato");
            System.out.println("2. Listar Todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Buscar por Nome");
            System.out.println("5. Remover Contato");
            System.out.println("0. Voltar");
            
            int opcao = lerInteiro("Escolha uma opção: ");
            
            try {
                switch (opcao) {
                    case 1 -> criarContato();
                    case 2 -> listarContatos();
                    case 3 -> buscarContato();
                    case 4 -> buscarContatoPorNome();
                    case 5 -> removerContato();
                    case 0 -> { return; } 
                    default -> System.out.println("❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro no menu de contatos: " + e.getMessage());
            }
        }
    }

    private static void criarContato() {
        try {
            System.out.println("\n=== NOVO CONTATO ===");
            
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            
 
            if (nome.trim().isEmpty()) {
                System.out.println("⚠️ O nome é obrigatório!");
                return;
            }
            
            System.out.print("Telefone: ");
            String telefone = sc.nextLine();
            
            System.out.print("Email (opcional): ");
            String email = sc.nextLine();
            
            System.out.print("Endereço (opcional): ");
            String endereco = sc.nextLine();
            
            System.out.print("Tipo (Ex: Pessoal, Trabalho, Família): ");
            String tipo = sc.nextLine();
            if (tipo.isEmpty()) tipo = "OUTROS";
         
            Contato c = new Contato();
            c.setNome(nome);
            c.setTelefone(telefone);
            c.setEmail(email);
            c.setEndereco(endereco);            
            
            contatoDAO.salvar(c);
            
        } catch (DAOException e) {
            System.out.println("❌ Erro ao salvar contato: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
    }

    private static void listarContatos() {
        System.out.println("\n=== SEUS CONTATOS ===");
        var lista = contatoDAO.listarAtivos();
        
        if (lista.isEmpty()) {
            System.out.println("📭 Nenhum contato encontrado.");
        } else {
          
            lista.forEach(c -> {
                System.out.println("ID: " + c.getId() + " | " + c.getNome() + " (" + c.getTelefone() + ")");
            });
        }
    }

    private static void buscarContato() {
        int id = lerInteiro("Digite o ID do contato: ");
        try {
            Contato c = contatoDAO.buscarPorId(id);
            System.out.println("\n--------- DETALHES ---------");
            System.out.println("Nome: " + c.getNome());
            System.out.println("Tel:  " + c.getTelefone());
            System.out.println("Email:" + c.getEmail());
            System.out.println("End.: " + c.getEndereco());
            System.out.println("----------------------------");
        } catch (ItemNaoEncontradoException e) {
            System.out.println("❌ Contato não encontrado.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar: " + e.getMessage());
        }
    }

    private static void buscarContatoPorNome() {
        System.out.print("Digite o nome (ou parte dele): ");
        String nomeBusca = sc.nextLine();
        
        var lista = contatoDAO.buscarPorNome(nomeBusca);
        
        if (lista.isEmpty()) {
            System.out.println("📭 Ninguém encontrado com esse nome.");
        } else {
            System.out.println("\n=== RESULTADOS DA BUSCA ===");
            for (Contato c : lista) {
                System.out.println("#" + c.getId() + " - " + c.getNome() + " [" + c.getTelefone() + "]");
            }
        }
    }

    private static void removerContato() {
        int id = lerInteiro("Digite o ID do contato para remover: ");
        try {
            System.out.print("Tem certeza que deseja remover o ID " + id + "? (S/N): ");
            String confirmacao = sc.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                contatoDAO.remover(id);
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (DAOException e) {
            System.out.println("❌ Erro ao remover: " + e.getMessage());
        }
    }

    
    private static void exibirResumo() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          RESUMO DA AGENDA             ║");
        System.out.println("╚════════════════════════════════════════╝");
        
 
        int compromissos = compromissoDAO.listarAtivos().size();
        int compromissosProximos = compromissoDAO.listarProximos().size();
        int contatos = contatoDAO.listarAtivos().size();
        
        int tarefas = tarefaDAO.listarAtivos().size(); 
        int tarefasAtrasadas = tarefaDAO.listarAtrasadas().size();
        
        System.out.println("📅 Compromissos: " + compromissos + " (" + compromissosProximos + " próximos)");
        System.out.println("👥 Contatos: " + contatos);
        System.out.println("✓ Tarefas: " + tarefas + " (" + tarefasAtrasadas + " atrasadas)");
        
        if (tarefasAtrasadas > 0) {
            System.out.println("\n⚠️ Atenção! Você tem tarefas atrasadas!");
        }
    }

 
    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = sc.nextLine();
   
                if (entrada.trim().isEmpty()) return 0; 
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, digite um número válido.");
            }
        }
    }
}