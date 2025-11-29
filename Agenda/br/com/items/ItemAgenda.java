package br.com.items;

import java.time.LocalDateTime;
import br.com.errors.ValidacaoException;

public abstract class ItemAgenda {
	
	    private int id;
	    protected static String descricao;
	    private LocalDateTime dataCriacao;
	    private boolean ativo;

	  
	    public ItemAgenda() {
	        this.dataCriacao = LocalDateTime.now();
	        this.ativo = true;
	    }


	    public ItemAgenda(String descricao) {
	        this();
	        this.descricao = descricao;
	    }


	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    public LocalDateTime getDataCriacao() {
	        return dataCriacao;
	    }

	    public boolean isAtivo() {
	        return ativo;
	    }

	    public void setAtivo(boolean ativo) {
	        this.ativo = ativo;
	    }


	    public abstract String exibirDetalhes();


	    public abstract boolean validar() throws ValidacaoException;

	    @Override
	    public String toString() {
	        return id + ": " + descricao;
	    }
}