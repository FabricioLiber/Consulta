package modelo;

import java.time.LocalDateTime;

import javax.persistence.*;

import daoDB4O.IDInterface;

@Entity
public class Consulta implements IDInterface {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataHorario;
	
	@ManyToOne
	private Paciente paciente;
	
	@ManyToOne
	private Medico medico;
	
	@ManyToOne
	private Secretario secretario;
	
	private boolean confirmado;
	
	@ManyToOne
	private Especialidade especialidade;

	public Consulta(LocalDateTime dataHorario, Paciente paciente, boolean confirmado, Especialidade especialidade) {
		this.dataHorario = dataHorario;
		this.paciente = paciente;
		this.confirmado = confirmado;
		this.especialidade = especialidade;
	}
	
	public Consulta() {
		
	}

	public int getId() {
		return this.id;
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

	public void setId(int id) {
		this.id = id;
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
				+ (paciente != null ? paciente.getNome() : "Indisponivel") + ", medico="
				+ (medico != null ? medico.getNome() : "Indisponivel") + ", secretario="
				+ (secretario != null ? secretario.getNome() : "Indisponivel") + ", confirmado=" + confirmado
				+ ", especialidade=" + especialidade + "]";
	}

}
