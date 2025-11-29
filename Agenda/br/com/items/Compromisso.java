package br.com.items;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.errors.ValidacaoException;

public class Compromisso extends ItemAgenda {
    private static LocalDateTime dataHora;
    private String local;
    private String observacao;

    public Compromisso(String descricao, LocalDateTime dataHora) {
        super(descricao);
        this.dataHora = dataHora;
    }

    public Compromisso(String descricao, LocalDateTime dataHora, String observacao) {
        this(descricao, dataHora);
        this.observacao = observacao;
    }

    public Compromisso() {
        this(descricao, dataHora);
        this.local = local;
    }

    // Getters e Setters
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


    @Override
    public String exibirDetalhes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("=== COMPROMISSO ===\n");
        sb.append("ID: ").append(getId()).append("\n");
        sb.append("Descrição: ").append(getDescricao()).append("\n");
        sb.append("Data/Hora: ").append(dataHora.format(formatter)).append("\n");
        if (local != null && !local.isEmpty()) {
            sb.append("Local: ").append(local).append("\n");
        }
        sb.append("Status: ").append(isAtivo() ? "Ativo" : "Inativo");
        return sb.toString();
    }


    @Override
    public boolean validar() throws ValidacaoException {
        if (getDescricao() == null || getDescricao().trim().isEmpty()) {
            throw new ValidacaoException("Descrição do compromisso não pode estar vazia");
        }
        if (dataHora == null) {
            throw new ValidacaoException("Data/Hora do compromisso é obrigatória");
        }
        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new ValidacaoException("Data/Hora do compromisso não pode ser no passado");
        }
        return true;
    }

    public boolean isProximo() {
        return dataHora.isBefore(LocalDateTime.now().plusHours(24));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return getId() + ": " + getDescricao() + " em " + dataHora.format(formatter) ;
    }
}