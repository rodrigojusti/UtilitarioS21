package arquivos;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import publicadores.Publicador;
import publicadores.Relatorio;

public class PDFManipulador {

	private static PDDocument arquivoPdf;

	public void carregar(File arquivoS21) {
		try {
			arquivoPdf = PDDocument.load(arquivoS21);
		} catch (IOException e) {
			System.out.println("Erro ao abrir cartão -> ");
			e.printStackTrace();
		}
	}

	private void preencher(String chave, String valor) {
		PDDocumentCatalog catalogo = arquivoPdf.getDocumentCatalog();
		PDAcroForm acroForm = catalogo.getAcroForm();
		PDField campo = acroForm.getField(chave);
		try {
			if (campo != null) {
				campo.setValue(valor);
			}
		} catch (IOException e) {
			System.out.println("Erro ao preencher campo -> ");
			e.printStackTrace();
		}
	}
	
	public void preencherAssociandoCampos(Publicador publicador) {
		// Objeto mapa para associar dado do XML com o campo do PDF
		// Após isso percorrerá o mapa passando os dados para preencher o cartão
		
		// Objeto tem duas strings para atender criterios do metodo preencher
		Map<String, String> mapa = new HashMap<String, String>();

		//Nome
		mapa.put("Name", publicador.getNome());
		
		// Data de nascimento (se existir)
		if (publicador.isTemDataNascimento()) {
			mapa.put("Date of birth", new Utils().converterDataParaTexto(publicador.getDataNascimento()));
		}
		
		// Data de batismo
		if (publicador.isBatizado()) {
			mapa.put("Date immersed", new Utils().converterDataParaTexto(publicador.getDataBatismo()));
		}
		
		// Dados relatorio
		

		
		
		// Vai percorrer a lista passando os dados para o manipulador, pra ir setando as informações necessárias
		for (Map.Entry<String, String> entrada : mapa.entrySet()) {
			preencher(entrada.getKey(), entrada.getValue());
		}

	}

	public void salvar(String cartaoPronto) {
		try {
			arquivoPdf.save("E:\\" + cartaoPronto + ".pdf");
			arquivoPdf.close();
		} catch (IOException e) {
			System.out.println("Erro ao salvar cartão -> ");
			e.printStackTrace();
		}
	}

}
