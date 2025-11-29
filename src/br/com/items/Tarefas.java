package br.com.items;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.errors.ValidacaoException;

public class Tarefas extends ItemAgenda {
    private LocalDate dataLimite;
    private String categoria;
    private int progresso; 

    public Tarefas() {
        super();
        this.progresso = 0;
    }

    public Tarefas(String descricao, LocalDate dataLimite) {
        super(descricao);
        this.dataLimite = dataLimite;
        this.progresso = 0;
    }

    public Tarefas(String descricao, LocalDate dataLimite, String categoria) {
        this(descricao, dataLimite);
        this.categoria = categoria;
    }

    // Getters e Setters
    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

   
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getProgresso() {
        return progresso;
    }

    public void setProgresso(int progresso) {
        if (progresso < 0) progresso = 0;
        if (progresso > 100) progresso = 100;
        this.progresso = progresso;
        
    }
    
    @Override
    public String exibirDetalhes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("=== TAREFA ===\n");
        sb.append("ID: ").append(getId()).append("\n");
        sb.append("Descrição: ").append(getDescricao()).append("\n");
        if (dataLimite != null) {
            sb.append("Data Limite: ").append(dataLimite.format(formatter)).append("\n");
        }
        sb.append("Progresso: ").append(progresso).append("%\n");
        if (categoria != null && !categoria.isEmpty()) {
            sb.append("Categoria: ").append(categoria).append("\n");
        }
        if (isAtrasada()) {
            sb.append("⚠️ TAREFA ATRASADA!\n");
        }
        return sb.toString();
    }

    @Override
    public boolean validar() throws ValidacaoException {
        if (getDescricao() == null || getDescricao().trim().isEmpty()) {
            throw new ValidacaoException("Descrição da tarefa é obrigatória");
        }
        return true;
    }

   
    public void concluir() {
        this.progresso = 100;
    }

    public void iniciar() {
    }

    public void cancelar() {
 
    }

    public boolean isAtrasada() {
        return dataLimite != null 
               && dataLimite.isBefore(LocalDate.now()) ;
    }

    @Override
    public String toString() {
        String dataStr = dataLimite != null ? dataLimite.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Sem prazo";
        return getId() + ": " + getDescricao() + " - " + " (" + progresso + "%) [" + dataStr + "]";
    }
}