package modelo;

import java.time.LocalDateTime;

public class Consulta {
	
	private LocalDateTime horario;
	private double valor;
	private Paciente paciente;
	private Medico medico;
	private Secretario secretario;
	
	
	
	public Consulta(LocalDateTime horario, double valor, Paciente paciente, Medico medico, Secretario secretario) {
		super();
		this.horario = horario;
		this.valor = valor;
		this.paciente = paciente;
		this.medico = medico;
		this.secretario = secretario;
	}



	public LocalDateTime getHorario() {
		return horario;
	}



	public double getValor() {
		return valor;
	}



	public Paciente getPaciente() {
		return paciente;
	}



	public Medico getMedico() {
		return medico;
	}



	public Secretario getSecretario() {
		return secretario;
	}
	
	
	
	
	
	
	
	
}
