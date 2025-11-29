package br.com.errors;

public class ItemNaoEncontradoException extends Exception {
    
    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ItemNaoEncontradoException(int id) {
        super("Item com ID " + id + " não foi encontrado");
    }
}