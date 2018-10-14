package modelo;

public class Convenio {

	private String descricao;
	private double taxaDesconto;
	
	
	
	public Convenio(String descricao, double taxaDesconto) {
		super();
		this.descricao = descricao;
		this.taxaDesconto = taxaDesconto;
	}



	public String getDescricao() {
		return descricao;
	}



	public double getTaxaDesconto() {
		return taxaDesconto;
	}



	@Override
	public String toString() {
		return "Convenio [descricao=" + descricao + ", taxaDesconto=" + taxaDesconto + "]";
	}
	
	
	
	
}
