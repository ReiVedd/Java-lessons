package br.com.agenda;
import java.time.LocalDateTime;

public class compromisso {
    private int id;
    private String descricao;
    private LocalDateTime dataHora;
    private String observacao;

    public compromisso() {}

    public compromisso(String descricao, LocalDateTime dataHora, String observacao) {
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.observacao = observacao;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    @Override
    public String toString() {
        return id + ": " + descricao + " em " + dataHora;
    }
}