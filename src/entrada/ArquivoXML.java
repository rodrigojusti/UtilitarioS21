package entrada;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ArquivoXML {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		String caminho = "E:\\Active.xml";
		File arquivoXML = new File(caminho);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(arquivoXML);
		doc.getDocumentElement().normalize();
		
	}
	
	
}
