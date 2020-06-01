package publicadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constantes.Esperanca;
import constantes.Genero;
import constantes.PrivilegioDianteira;
import constantes.PrivilegioPregacao;
import relatorios.Ano;

public class Publicador {
	
	private int id;
	private String nome;
	private Date dataNascimento;
	private Date dataBatismo;
	
	private Genero genero; //masculino ou feminino
	private Esperanca esperança; //ungido ou outra ovelha
	private PrivilegioDianteira dianteira; //anciao ou sm
	private PrivilegioPregacao pioneiro; //pioneiro
	
	private List<Ano> cartao = new ArrayList<Ano>();
	
	public Publicador() {
	}

	public Publicador(int id, String nome, Date dataNascimento, Date dataBatismo, Genero genero, Esperanca esperança,
			PrivilegioDianteira dianteira, PrivilegioPregacao pioneiro, List<Ano> cartao) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataBatismo = dataBatismo;
		this.genero = genero;
		this.esperança = esperança;
		this.dianteira = dianteira;
		this.pioneiro = pioneiro;
		this.cartao = cartao;
	}

	public List<Ano> getCartao() {
		return cartao;
	}

	public void setCartao(List<Ano> cartao) {
		this.cartao = cartao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataBatismo() {
		return dataBatismo;
	}

	public void setDataBatismo(Date dataBatismo) {
		this.dataBatismo = dataBatismo;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Esperanca getEsperança() {
		return esperança;
	}

	public void setEsperança(Esperanca esperança) {
		this.esperança = esperança;
	}

	public PrivilegioDianteira getDianteira() {
		return dianteira;
	}

	public void setDianteira(PrivilegioDianteira dianteira) {
		this.dianteira = dianteira;
	}

	public PrivilegioPregacao getPioneiro() {
		return pioneiro;
	}

	public void setPioneiro(PrivilegioPregacao pioneiro) {
		this.pioneiro = pioneiro;
	}	
}
