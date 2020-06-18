package arquivos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import enums.Esperanca;
import enums.Genero;
import enums.MesEnum;
import enums.PrivilegioDianteira;
import enums.PrivilegioPregacaoEnum;
import publicadores.Publicador;
import publicadores.Relatorio;

public class SAXManipulador extends DefaultHandler {

	private List<Publicador> publicadores = null;
	List<Relatorio> relatorios = null;
	private Publicador publicador = null;
	private String elementValue;
	private boolean primeiroNomePreenchido = false;
	Relatorio relatorio = null;
	Integer ano = null;

	@Override
	public void startDocument() {
		publicadores = new ArrayList<Publicador>();
		relatorios = new ArrayList<Relatorio>();
		

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

		// Iniciar a partir de Pub, onde tem os dados
		if (qName.equalsIgnoreCase("Pub")) {
			publicador = new Publicador();

		}

		if (attributes.getLength() > 0 && !qName.equalsIgnoreCase("Agent")) {
			ano = Integer.valueOf(attributes.getValue("Year"));
			relatorio = new Relatorio();
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equalsIgnoreCase("Agent") || qName.equalsIgnoreCase("Date") || qName.equalsIgnoreCase("Count")
				|| qName.equalsIgnoreCase("Active")) {
			return;
		}

		if (qName.equalsIgnoreCase("Pub")) {
			publicadores.add(publicador);
			
			for (Relatorio relat : publicador.getRelatorios()) {
				System.out.println(relat.getMes() + "|" + relat.getHoras());
			}
		}

		// ID
		if (qName.equalsIgnoreCase("id")) {
			publicador.setId(Integer.valueOf(elementValue));

			// Esperanca - fixa por hora
			publicador.setEsperança(Esperanca.OUTRA_OVELHA);
		}

		// Nome
		if (qName.equalsIgnoreCase("longname")) {
			if (elementValue.equals(null)) {
				return;
			}
			publicador.setNome(elementValue);

		}

		// Se o longname nao vier, pegar o first name
		if (qName.equalsIgnoreCase("fname") && publicador.getNome() == null) {
			publicador.setNome(elementValue);
			primeiroNomePreenchido = true; // jeito que achei pra saber se tem primeiro nome
		}

		// Se o primeiro nome tiver sido preenchido
		if (qName.equalsIgnoreCase("lname") && primeiroNomePreenchido == true) {
			String primeiroNome = publicador.getNome();
			publicador.setNome(primeiroNome + " " + elementValue);
		}

		// Data de nascimento
		if (qName.equalsIgnoreCase("birdate")) {
			if (elementValue.equalsIgnoreCase(null)) {
				return;
			}

			Utils utiliario = new Utils();
			try {
				Date dataNascimento = utiliario.converterData(elementValue);
				publicador.setDataNascimento(dataNascimento);
			} catch (ParseException e) {
				System.out.println("Não foi possivel converter data!");
				e.printStackTrace();
			}
		}

		// Data de batismo
		if (qName.equalsIgnoreCase("bapdate")) {
			if (elementValue.contains("\n") || elementValue.equalsIgnoreCase(null)) {
				return;
			}
			Utils utiliario = new Utils();
			try {
				Date dataBatismo = utiliario.converterData(elementValue);
				publicador.setDataBatismo(dataBatismo);
			} catch (ParseException e) {
				System.out.println("Não foi possivel converter data!");
				e.printStackTrace();
			}
		}

		// Genero
		if (qName.equalsIgnoreCase("gender")) {
			if (elementValue.equalsIgnoreCase(null)) {
				return;
			}

			if (elementValue.equalsIgnoreCase("female")) {
				publicador.setGenero(Genero.FEMININO);
			} else if (elementValue.equalsIgnoreCase("male")) {
				publicador.setGenero(Genero.MASCULINO);
			}
		}

		// por enquanto nao trataremos Esperanca

		// por enquanto nao trataremos Pioneiros

		// Privilegio - Dianteira (Anciãos - SMs)
		if (qName.equalsIgnoreCase("svt")) {
			if (elementValue.equalsIgnoreCase("bapbro") || elementValue.equalsIgnoreCase(null)) {
				/*
				 * BapBro sao irmãos batizados com privilegios na congregação. É usado para
				 * relatórios TSWIN e gerenciamento dos irmãos.
				 */
				return;
			}

			if (elementValue.equalsIgnoreCase("elder")) {
				publicador.setDianteira(PrivilegioDianteira.ANCIAO);
			} else if (elementValue.equalsIgnoreCase("minsvt")) {
				publicador.setDianteira(PrivilegioDianteira.SERVO_MINISTERIAL);
			}

		}

		/*
		 * Pra nao colocar Jan, Fev, Mar, Abr, e repetir tudo varias vezes, fiz um enum
		 * e estou percorrendo como lista para ter menos codigo
		 */
		List<MesEnum> mes = Arrays.asList(MesEnum.values());
		for (MesEnum mesEnum : mes) {
			if (qName.equalsIgnoreCase(mesEnum.valorMes)) {
//		Mudanca de cenario -> geracao da planilha
//				if (isMudarAnoServico(qName)) {
//					// Muda ano serviço
//					relatorio.setAno(ano + 1);
//				} else {
//					// Nao muda ano serviço
					relatorio.setAno(ano);
//				}
				relatorio.setMes(mesEnum);
				adicionarObjetoPublicador();
			}
		}
		// Relatorio
		if (qName.equalsIgnoreCase("Plcmts")) {
			relatorio.setPublicacoes(Integer.valueOf(elementValue));
		}

		if (qName.equalsIgnoreCase("Videos")) {
			relatorio.setVideos(Integer.valueOf(elementValue));
		}

		if (qName.equalsIgnoreCase("Hours")) {
			String s = elementValue.substring(elementValue.length() - 1, elementValue.length());
			if (s.equalsIgnoreCase("½")) {
				s = elementValue.substring(0, elementValue.length() - 1);
				relatorio.setHoras(Double.valueOf(s) + 0.5);
			} else {
				relatorio.setHoras(Double.valueOf(elementValue));
			}
		}

		if (qName.equalsIgnoreCase("R.V.s")) {
			relatorio.setRevisitas(Integer.valueOf(elementValue));
		}

		if (qName.equalsIgnoreCase("BiSt.")) {
			relatorio.setEstudos(Integer.valueOf(elementValue));
		}

		if (qName.equalsIgnoreCase("Remark")) {
			relatorio.setObservacao(elementValue);
		}
		
		if (qName.equalsIgnoreCase("Pio")) {
			if (elementValue.equalsIgnoreCase("Reg")) {
				relatorio.setPioneiro(PrivilegioPregacaoEnum.PIONEIRO_REGULAR);
			}
			
			if (elementValue.equalsIgnoreCase("Aux")) {
				relatorio.setPioneiro(PrivilegioPregacaoEnum.PIONEIRO_AUXILIAR);
			}
			
		}

		// Terminou
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		elementValue = new String(ch, start, length);
	}

	// Metodos meus :)

	public void pegaValorNomeSobrenome(String valor) {

	}
	
	@SuppressWarnings("unused")
	private boolean isMudarAnoServico(String qName) {
		/*	Ano de serviço começa em setembro.
		 * 	Então, se estivessemos em setembro de 2020
		 * 	no ano de serviço seria setembro de 2021  
		 */	
		
		if (qName.equals(MesEnum.SET.valorMes) 
				|| qName.equals(MesEnum.OUT.valorMes) 
				|| qName.equals(MesEnum.NOV.valorMes) 
				|| qName.equals(MesEnum.DEZ.valorMes)) {
			return true;
		}
	return false;	
	}

	public void adicionarObjetoPublicador() {
		//cartao.add(relatorio);
		publicador.getRelatorios().add(relatorio);
	
	}
	
	

	public List<Publicador> getPublicadores() {
		return publicadores;
	}

}
