package executavel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import arquivos.CriarPlanilhaExcel;
import arquivos.PDFManipulador;
import arquivos.SAXManipulador;
import publicadores.Publicador;

public class Executavel {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		// Inicia objetos
		File arquivoXml = new File("E:\\Active.xml");
		File arquivoS21 = new File("E:\\S-21-T.pdf");
		File arquivoExcelPublicadores = new File("E:\\pub.xls");
		File arquivoExcelRelatorios = new File("E:\\relats.xls");
		SAXParserFactory fabrica = SAXParserFactory.newInstance();
		SAXParser parser = fabrica.newSAXParser();
		SAXManipulador leitorXml = new SAXManipulador();
		PDFManipulador impressor = new PDFManipulador();
		//Pega o arquivo e passa pro Parser do java
		parser.parse(arquivoXml, leitorXml);
		
		List<Publicador> publicadores = leitorXml.getPublicadores();
	
		// Por hora nao preencheremos PDF
//		for (Publicador publicador : publicadores) {
//			System.out.println("Processando cartão: -> " + publicador.getNome());
//			
//			//Teste - carregar arquivo e passar dados
//			impressor.carregar(arquivoS21);
//			
//			// Tentativa passar mais responsabilidade ao impressor
//			impressor.preencherAssociandoCampos(publicador);
//			impressor.salvar(publicador.getNome());
//			System.out.println("Cartão impresso.");
//			
//		}
		
		CriarPlanilhaExcel gerarPlanilha = new CriarPlanilhaExcel();
		gerarPlanilha.inserirPublicadores(publicadores, arquivoExcelPublicadores);
		gerarPlanilha.gerarPlanilhaRelatorios(publicadores, arquivoExcelRelatorios);
		System.out.println("CONCLUIDO!");
	}

}
