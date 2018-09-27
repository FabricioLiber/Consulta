package modelo;

import java.util.ArrayList;

public class Especialidade {
	
	private String descricao;
	private double valorConsulta;
	private ArrayList<Medico> medicos;
	
	public Especialidade(String descricao, double valorConsulta) {
		super();
		this.descricao = descricao;
		this.valorConsulta = valorConsulta;
		this.medicos = new ArrayList<>();
	}


	public String getDescricao() {
		return descricao;
	}


	public double getValorConsulta() {
		return valorConsulta;
	}	
	
	
	
	public ArrayList<Medico> getMedicos() {
		return medicos;
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
