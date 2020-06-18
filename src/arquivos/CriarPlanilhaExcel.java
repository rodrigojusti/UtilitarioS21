package arquivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import enums.MesEnum;
import publicadores.Publicador;
import publicadores.Relatorio;

public class CriarPlanilhaExcel {

	public void inserirPublicadores(List<Publicador> publicadores, File salvar) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet planilhaPublicadores = workbook.createSheet("Publicadores");
		int rownum = 0;
		for (Publicador publicador : publicadores) {
			Row row = planilhaPublicadores.createRow(rownum++);
			int cellnum = 0;

			// ID - A
			Cell cellId = row.createCell(cellnum++);
			cellId.setCellValue(publicador.getId());

			// Nome - B
			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(publicador.getNome());

			// Vazio -> ultimo nome - C
			Cell cellVazioC = row.createCell(cellnum++);
			cellVazioC.setBlank();
			
			
			// Grupo (Todos grupo 1) - D
			Cell cellGrupo = row.createCell(cellnum++);
			cellGrupo.setCellValue(1);

			// Pioneiro (sem tratamento) - E
			Cell cellPio = row.createCell(cellnum++);
			cellPio.setCellValue(0);

			// Servo Minisnterial - F
			Cell cellSm = row.createCell(cellnum++);
			if (publicador.isServoMinisterial()) {
				cellSm.setCellValue(1);
			} else {
				cellSm.setCellValue(0);
			}

			// Anciao - G
			Cell cellAnciao = row.createCell(cellnum++);
			if (publicador.isAnciao()) {
				cellAnciao.setCellValue(1);
			} else {
				cellAnciao.setCellValue(0);
			}

			// Ungido (sem ungidos) - H
			Cell cellUngido = row.createCell(cellnum++);
			cellUngido.setCellValue(0);

			// Dessassociado (sem dessassociados) - I
			Cell cellDessassociado = row.createCell(cellnum++);
			cellDessassociado.setCellValue(0);

			// Desativado (sem desativados) - J
			Cell cellDesativado = row.createCell(cellnum++);
			cellDesativado.setCellValue(0);

			// Data nascimento - K
			Cell cellDtNascimento = row.createCell(cellnum++);
			cellDtNascimento.setCellValue(new Utils().converterDataParaTexto(publicador.getDataNascimento()));

			// Data batismo - L
			Cell cellDtBatismo = row.createCell(cellnum++);
			if (publicador.isBatizado()) {
				cellDtBatismo.setCellValue(new Utils().converterDataParaTexto(publicador.getDataBatismo()));
			}
			
			// Genero - M
			Cell cellGenero = row.createCell(cellnum++);
			if (publicador.isGeneroMasculino()) {
				cellGenero.setCellValue(1);
			}
			if (publicador.isGeneroFeminino()) {
				cellGenero.setCellValue(0);
			}

		}

		try {
			FileOutputStream saida = new FileOutputStream(salvar);
			workbook.write(saida);
			saida.close();
			workbook.close();
			System.out.println("Arquivo gerado com sucesso!");
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro na edicao!");
			e.printStackTrace();
		}
	}

	public void gerarPlanilhaRelatorios(List<Publicador> publicadores, File arquivoXls) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet planilhaPublicadores = workbook.createSheet("Relatorios");
		int rownum = 0;
		for (Publicador publicador : publicadores) {
			System.out.println("Iterando-> " + publicador.getNome());
			for (Relatorio relatorio : publicador.getRelatorios()) {
				System.out.println("Iterando relatorio-> " + relatorio.getAno() + "|" + relatorio.getMes() + "|" + relatorio.getHoras() + "|" + publicador.getNome());
				Row row = planilhaPublicadores.createRow(rownum++);
				int cellnum = 0;

				// Nome - A
				Cell cellNome = row.createCell(cellnum++);
				cellNome.setCellValue(publicador.getNome());

				// Ano - B
				Cell cellAno = row.createCell(cellnum++);
				cellAno.setCellValue(relatorio.getAno());

				// Mes (1=jan | 12=dez) - C
				Cell cellMes = row.createCell(cellnum++);
				Utils util = new Utils();
				int mes = util.retornarNumeroMes(relatorio.getMes());
				cellMes.setCellValue(mes);

				// Vazios -> Livros / Brochuras (relatorios antigos) - D e E
				Cell cellVazioD = row.createCell(cellnum++);
				cellVazioD.setBlank();
				Cell cellVazioE = row.createCell(cellnum++);
				cellVazioE.setBlank();

				// Publicacoes - F
				Cell cellPub = row.createCell(cellnum++);
				cellPub.setCellValue(relatorio.getPublicacoes());

				// Videos - G
				Cell cellVideo = row.createCell(cellnum++);
				cellVideo.setCellValue(relatorio.getVideos());

				// Horas (completas) - H
				Cell cellHora = row.createCell(cellnum++);
				int i = util.retornarHorasRelatorio(relatorio.getHoras());
				cellHora.setCellValue(i);

				// Minutos - I
				Cell cellMin = row.createCell(cellnum++);
				int j = util.retornarMinutosRelatorio(relatorio.getHoras());
				cellMin.setCellValue(j);

				// Vazio -> Revistas (relatorios antigos) - J
				Cell cellVazioJ = row.createCell(cellnum++);
				cellVazioJ.setBlank();

				// Revisitas - K
				Cell cellRevisita = row.createCell(cellnum++);
				cellRevisita.setCellValue(relatorio.getRevisitas());

				// Estudo - L
				Cell cellEstudo = row.createCell(cellnum++);
				cellEstudo.setCellValue(relatorio.getEstudos());

				// Pioneiro? - M
				Cell cellPioneiro = row.createCell(cellnum++);
				if (relatorio.isPioneiroAuxiar()) {
					cellPioneiro.setCellValue(1);
				} else if (relatorio.isPioneiroRegular()) {
					cellPioneiro.setCellValue(2);
				} else {
					cellPioneiro.setCellValue(0);
				}

				// Observacao - N
				Cell cellObs = row.createCell(cellnum++);
				cellObs.setCellValue(relatorio.getObservacao());

			}
		}
		try {
			FileOutputStream saida = new FileOutputStream(arquivoXls);
			workbook.write(saida);
			saida.close();
			workbook.close();
			System.out.println("Arquivo gerado com sucesso!");
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro na edicao!");
			e.printStackTrace();
		}
	}

}
