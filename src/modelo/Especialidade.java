package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Id;

import daoDB4O.IDInterface;

@Entity
public class Especialidade implements IDInterface {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String descricao;
	private double valorConsulta;
	
	@ManyToMany
	private List<Medico> medicos;
	
	public Especialidade(String descricao, double valorConsulta) {
		this.descricao = descricao;
		this.valorConsulta = valorConsulta;
		this.medicos = new ArrayList<>();
	}
	

	public int getId () {
		return this.id;
	}


	public String getDescricao() {
		return descricao;
	}


	public double getValorConsulta() {
		return valorConsulta;
	}	
	
	
	
	public List<Medico> getMedicos() {
		return medicos;
	}
	

	public void setId (int id) {
		this.id = id;
	}

	
	public void add (Medico m) {
		medicos.add(m);
	}

	
	public void remove (Medico m) {
		medicos.remove(m);
	}


	@Override
	public String toString() {
		return "Especialidade [descricao=" + descricao + ", valorConsulta=" + valorConsulta + ", medicos=" + medicos.size()
				+ "]";
	}
	
	
	
}
