package publicadores;

import enums.MesEnum;
import enums.PrivilegioPregacaoEnum;

public class Relatorio {
	private int ano;
	private MesEnum mes;
	private int publicacoes;
	private int videos;
	private double horas;
	private int revisitas;
	private int estudos;
	private String observacao;
	private PrivilegioPregacaoEnum pioneiro;
	
	public Relatorio() {
	}

	public Relatorio(int ano, MesEnum mes, int publicacoes, int videos, double horas, int revisitas, int estudos, String observacao, PrivilegioPregacaoEnum pioneiro) {
		this.ano = ano;
		this.mes = mes;
		this.publicacoes = publicacoes;
		this.videos = videos;
		this.horas = horas;
		this.revisitas = revisitas;
		this.estudos = estudos;
		this.observacao = observacao;
		this.pioneiro = pioneiro;
	}
	
	public boolean isPioneiroRegular() {
		return this.pioneiro == PrivilegioPregacaoEnum.PIONEIRO_REGULAR;
	}
	
	public boolean isPioneiroAuxiar() {
		return this.pioneiro == PrivilegioPregacaoEnum.PIONEIRO_AUXILIAR;
	}

	public int getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(int publicacoes) {
		this.publicacoes = publicacoes;
	}

	public int getVideos() {
		return videos;
	}

	public void setVideos(int videos) {
		this.videos = videos;
	}

	public double getHoras() {
		return horas;
	}

	public void setHoras(double horas) {
		this.horas = horas;
	}

	public int getRevisitas() {
		return revisitas;
	}

	public void setRevisitas(int revisitas) {
		this.revisitas = revisitas;
	}

	public int getEstudos() {
		return estudos;
	}

	public void setEstudos(int estudos) {
		this.estudos = estudos;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public MesEnum getMes() {
		return mes;
	}

	public void setMes(MesEnum mes) {
		this.mes = mes;
	}
	
	

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	

	public PrivilegioPregacaoEnum getPioneiro() {
		return pioneiro;
	}

	public void setPioneiro(PrivilegioPregacaoEnum pioneiro) {
		this.pioneiro = pioneiro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + estudos;
		long temp;
		temp = Double.doubleToLongBits(horas);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((pioneiro == null) ? 0 : pioneiro.hashCode());
		result = prime * result + publicacoes;
		result = prime * result + revisitas;
		result = prime * result + videos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relatorio other = (Relatorio) obj;
		if (ano != other.ano)
			return false;
		if (estudos != other.estudos)
			return false;
		if (Double.doubleToLongBits(horas) != Double.doubleToLongBits(other.horas))
			return false;
		if (mes != other.mes)
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (pioneiro != other.pioneiro)
			return false;
		if (publicacoes != other.publicacoes)
			return false;
		if (revisitas != other.revisitas)
			return false;
		if (videos != other.videos)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Relatorio [ano=" + ano + ", mes=" + mes + ", publicacoes=" + publicacoes + ", videos=" + videos
				+ ", horas=" + horas + ", revisitas=" + revisitas + ", estudos=" + estudos + ", observacao="
				+ observacao + ", pioneiro=" + pioneiro + "]";
	}

	
}
