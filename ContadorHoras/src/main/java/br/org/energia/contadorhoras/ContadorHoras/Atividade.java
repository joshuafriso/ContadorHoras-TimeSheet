package br.org.energia.contadorhoras.ContadorHoras;


public class Atividade {
	
	String descricao;
	Long hora;
	String data;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getHora() {
		return hora;
	}
	public void setHora(Long totalEmMinutos) {
		this.hora = totalEmMinutos;
	}

}
