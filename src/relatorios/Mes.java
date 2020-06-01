package relatorios;

public class Mes {
	private int publicacoes;
	private int videos;
	private double horas;
	private int revisitas;
	private int estudos;
	private String observacao;
	
	public Mes() {
	}

	public Mes(int publicacoes, int videos, double horas, int revisitas, int estudos) {
		this.publicacoes = publicacoes;
		this.videos = videos;
		this.horas = horas;
		this.revisitas = revisitas;
		this.estudos = estudos;
		this.observacao = observacao;
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

	@Override
	public String toString() {
		return "Mes [publicacoes=" + publicacoes + ", videos=" + videos + ", horas=" + horas + ", revisitas="
				+ revisitas + ", estudos=" + estudos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + estudos;
		long temp;
		temp = Double.doubleToLongBits(horas);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Mes other = (Mes) obj;
		if (estudos != other.estudos)
			return false;
		if (Double.doubleToLongBits(horas) != Double.doubleToLongBits(other.horas))
			return false;
		if (publicacoes != other.publicacoes)
			return false;
		if (revisitas != other.revisitas)
			return false;
		if (videos != other.videos)
			return false;
		return true;
	}
	
	
	
}
