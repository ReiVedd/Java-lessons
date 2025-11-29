package br.com.model;

public class ContaCorrente extends Conta {
	
	private double limiteChequeEspecial;
	
	public ContaCorrente(int numero, String titular, double limiteChequeEspedcial) {
		super(numero, titular);
		this.limiteChequeEspecial = limiteChequeEspecial;	
	}
	
	@Override
	public boolean sacar(double valor) {
		if (valor > 0 && (this.saldo + this.limiteChequeEspecial) >= valor) {
			this.saldo -= valor;
			System.out.println("Saque de R$" + valor + " realizado (cheque especial disponível).");
			return true;
		} else {
			System.out.println("Erro: Limite do cheque especial insuficiente.");
            return false;
		}
	}
	
	@Override
	public void aplicarTaxa() {
		double taxa = 20.0;
		this.saldo -= taxa;
		System.out.println("Taxa de manutenção de R$" + taxa + " aplicada à conta corrente " + getNumero());
	}
}
