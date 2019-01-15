package modelo;

import javax.persistence.*;

import dao.IDInterface;

@Entity
public class Convenio implements IDInterface {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	
	private String descricao;
	private double taxaDesconto;
	
	
	
	public Convenio(String descricao, double taxaDesconto) {
		super();
		this.descricao = descricao;
		this.taxaDesconto = taxaDesconto;
	}
	

	public int getId () {
		return this.id;
	}
	
	
	public String getDescricao() {
		return descricao;
	}


	public double getTaxaDesconto() {
		return taxaDesconto;
	}
	

	public void setId (int id) {
		this.id = id;
	}	


	@Override
	public String toString() {
		return "Convenio [descricao=" + descricao + ", taxaDesconto=" + taxaDesconto + "]";
	}
	
	
	
	
}
