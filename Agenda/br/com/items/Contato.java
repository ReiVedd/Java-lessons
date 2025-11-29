package br.com.items;
import br.com.errors.ValidacaoException;

/**
 * Classe Contato - herda de ItemAgenda
 * Demonstra: Herança, Polimorfismo, Validação
 */
public class Contato extends ItemAgenda {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    // Construtores (Sobrecarga)
    public Contato() {
        super();
    }

    public Contato(String nome, String telefone) {
        super(nome);
        this.nome = nome;
        this.telefone = telefone;
    }

    public Contato(String nome, String telefone, String email) {
        this(nome, telefone);
        this.email = email;
    }

    public Contato(String nome, String telefone, String email, String endereco) {
        this(nome, telefone, email);
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    // Implementação do método abstrato
    @Override
    public String exibirDetalhes() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CONTATO ===\n");
        sb.append("ID: ").append(getId()).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Telefone: ").append(telefone).append("\n");
        if (email != null && !email.isEmpty()) {
            sb.append("Email: ").append(email).append("\n");
        }
        if (endereco != null && !endereco.isEmpty()) {
            sb.append("Endereço: ").append(endereco).append("\n");
        }
        sb.append("Status: ").append(isAtivo() ? "Ativo" : "Inativo");
        return sb.toString();
    }

    // Validação customizada
    @Override
    public boolean validar() throws ValidacaoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("Nome do contato é obrigatório");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new ValidacaoException("Telefone do contato é obrigatório");
        }
        if (email != null && !email.isEmpty() && !validarEmail(email)) {
            throw new ValidacaoException("Email inválido");
        }
        return true;
    }

    // Método auxiliar privado
    private boolean validarEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    @Override
    public String toString() {
        return getId() + ": " + nome + " - ";
    }
}