package publicadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import enums.Esperanca;
import enums.Genero;
import enums.PrivilegioDianteira;
import enums.PrivilegioPregacaoEnum;

public class Publicador {
	
	private int id;
	private String nome;
	private Date dataNascimento;
	private Date dataBatismo;
	private Genero genero; //masculino ou feminino
	private Esperanca esperança; //ungido ou outra ovelha
	private PrivilegioDianteira dianteira; //anciao ou sm
	private PrivilegioPregacaoEnum pioneiro; //pioneiro
	
	private List<Relatorio> relatorios = new ArrayList<Relatorio>(); 
	
	public Publicador() {
	}

	public Publicador(int id, String nome, Date dataNascimento, Date dataBatismo, Genero genero, Esperanca esperança,
			PrivilegioDianteira dianteira, PrivilegioPregacaoEnum pioneiro, List<Relatorio> relatorios) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataBatismo = dataBatismo;
		this.genero = genero;
		this.esperança = esperança;
		this.dianteira = dianteira;
		this.pioneiro = pioneiro;
		this.relatorios = relatorios;
	}

	public boolean isAnciao() {
		if (this.dianteira == PrivilegioDianteira.ANCIAO) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isServoMinisterial() {
		if (this.dianteira == PrivilegioDianteira.SERVO_MINISTERIAL) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isBatizado() {
		if (this.dataBatismo != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isTemDataNascimento() {
		if (this.dataNascimento != null) {
			return true;
		} else {
			return false;
		}	
	}
	
	public boolean isGeneroMasculino() {
		if (this.genero == Genero.MASCULINO) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isGeneroFeminino() {
		if (this.genero == Genero.FEMININO) {
			return true;
		} else {
			return false;
		}
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

	public PrivilegioPregacaoEnum getPioneiro() {
		return pioneiro;
	}

	public void setPioneiro(PrivilegioPregacaoEnum pioneiro) {
		this.pioneiro = pioneiro;
	}

	public List<Relatorio> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}
}
