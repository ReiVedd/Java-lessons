package br.com.errors;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException(String mensagem) {
       super(mensagem);
   }

   public DAOException(String mensagem, Throwable causa) {
       super(mensagem, causa);
   }
}
