package modelo;

import java.time.LocalDateTime;

public class Consulta {
	
	private LocalDateTime dataHorario;
	private Paciente paciente;
	private Medico medico;
	private Secretario secretario;
	private boolean confirmado;
	private Especialidade especialidade;
	
	
	
	public Consulta(LocalDateTime dataHorario, Paciente paciente, boolean confirmado, Especialidade especialidade) {
		this.dataHorario = dataHorario;
		this.paciente = paciente;
		this.confirmado = confirmado;
		this.especialidade = especialidade;
	}



	public LocalDateTime getdataHorario() {
		return dataHorario;
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



	public boolean isConfirmado() {
		return confirmado;
	}



	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}



	public void setMedico(Medico medico) {
		this.medico = medico;
	}



	public void setSecretario(Secretario secretario) {
		this.secretario = secretario;
	}



	public Especialidade getEspecialidade() {
		return especialidade;
	}



	@Override
	public String toString() {
		return "Consulta [dataHorario=" + dataHorario.toString() + ", paciente=" 
				+ (paciente != null ? paciente.getNome():"Indisponivel") + ", medico="
				+ (medico != null ? medico.getNome():"Indisponivel") + ", secretario=" 
				+ (secretario != null ? secretario.getNome():"Indisponivel") + ", confirmado=" +
				confirmado + ", especialidade="	+ especialidade + "]";
	}
	
		
	
}
