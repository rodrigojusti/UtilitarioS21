package relatorios;

import java.util.ArrayList;
import java.util.List;

public class Ano {
	
	private int ano;
	private List<Mes> meses = new ArrayList<Mes>(); //relatorios de setembro a agosto de cada ano
	
	public Ano() {
	}

	public Ano(int ano, List<Mes> meses) {
		this.ano = ano;
		this.meses = meses;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public List<Mes> getMeses() {
		return meses;
	}

	public void setMeses(List<Mes> meses) {
		this.meses = meses;
	}
	
	
	
}
