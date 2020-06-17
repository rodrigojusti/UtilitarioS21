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

			// ID
			Cell cellId = row.createCell(cellnum++);
			cellId.setCellValue(publicador.getId());

			// Nome
			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(publicador.getNome());

			// Grupo - Todos grupo 1
			Cell cellGrupo = row.createCell(cellnum++);
			cellGrupo.setCellValue(1);

			// Pioneiro - sem tratamento
			Cell cellPio = row.createCell(cellnum++);
			cellPio.setCellValue(0);

			// Servo Minisnterial
			Cell cellSm = row.createCell(cellnum++);
			if (publicador.isServoMinisterial()) {
				cellSm.setCellValue(1);
			} else {
				cellSm.setCellValue(0);
			}

			// Anciao
			Cell cellAnciao = row.createCell(cellnum++);
			if (publicador.isAnciao()) {
				cellAnciao.setCellValue(1);
			} else {
				cellAnciao.setCellValue(0);
			}

			// Ungido - sem ungidos
			Cell cellUngido = row.createCell(cellnum++);
			cellUngido.setCellValue(0);

			// Dessassociado
			Cell cellDessassociado = row.createCell(cellnum++);
			cellDessassociado.setCellValue(0);

			// Desativado
			Cell cellDesativado = row.createCell(cellnum++);
			cellDesativado.setCellValue(0);

			// Data batismo
			Cell cellDtNascimento = row.createCell(cellnum++);
			cellDtNascimento.setCellValue(new Utils().converterDataParaTexto(publicador.getDataNascimento()));

			// Data batismo
			Cell cellDtBatismo = row.createCell(cellnum++);
			if (publicador.isBatizado()) {
				cellDtBatismo.setCellValue(new Utils().converterDataParaTexto(publicador.getDataBatismo()));
			}
			// Genero
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
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet planilhaPublicadores = workbook.createSheet("Relatorios");
		int rownum = 0;
		for (Publicador publicador : publicadores) {
			System.out.println("Iterando-> " + publicador.getNome());
			for (Relatorio relatorio : publicador.getRelatorios()) {
				System.out.println("Iterando relatorio-> " + relatorio.getAno() + "|" + relatorio.getMes() + "|" + relatorio.getHoras() + "|" + publicador.getNome());
				Row row = planilhaPublicadores.createRow(rownum++);
				int cellnum = 0;

				// Nome
				Cell cellNome = row.createCell(cellnum++);
				cellNome.setCellValue(publicador.getNome());

				// Ano
				Cell cellAno = row.createCell(cellnum++);
				cellAno.setCellValue(relatorio.getAno());

				// Mes - Retornar numero
				Cell cellMes = row.createCell(cellnum++);
				Utils util = new Utils();
				int mes = util.retornarNumeroMes(relatorio.getMes());
				cellMes.setCellValue(mes);

				// Vazios -> Livros / Brochuras (relatorios antigos)
				Cell cellVazio = row.createCell(cellnum++);
				cellVazio.setBlank();
				cellVazio.setBlank();

				// Publicacoes
				Cell cellPub = row.createCell(cellnum++);
				cellPub.setCellValue(relatorio.getPublicacoes());

				// Videos
				Cell cellVideo = row.createCell(cellnum++);
				cellVideo.setCellValue(relatorio.getVideos());

				// Horas (completas)
				Cell cellHora = row.createCell(cellnum++);
				int i = util.retornarHorasRelatorio(relatorio.getHoras());
				cellHora.setCellValue(i);

				// Minutos
				Cell cellMin = row.createCell(cellnum++);
				int j = util.retornarMinutosRelatorio(relatorio.getHoras());
				cellMin.setCellValue(j);

				// Revistas -> relatorios antigos
				cellVazio.setBlank();

				// Revisitas
				Cell cellRevisita = row.createCell(cellnum++);
				cellRevisita.setCellValue(relatorio.getRevisitas());

				// Estudo
				Cell cellEstudo = row.createCell(cellnum++);
				cellEstudo.setCellValue(relatorio.getEstudos());

				// Pioneiro?
				Cell cellPioneiro = row.createCell(cellnum++);
				if (relatorio.isPioneiroAuxiar()) {
					cellPioneiro.setCellValue(1);
				} else if (relatorio.isPioneiroRegular()) {
					cellPioneiro.setCellValue(2);
				} else {
					cellPioneiro.setCellValue(0);
				}

				// Estudo
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
