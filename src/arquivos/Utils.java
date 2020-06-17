package arquivos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import enums.MesEnum;

public class Utils {

	public Date converterData(String dataComTracos) throws ParseException {
		Date formatador = new SimpleDateFormat("yyyy-MM-dd").parse(dataComTracos);
		return formatador;
	}

	public String converterDataParaTexto(Date data) {
		String dataEmTexto = new SimpleDateFormat("dd/MM/yyyy").format(data);
		return dataEmTexto;
	}

	public int retornarHorasRelatorio(double horas) {
		if (horas >= 0.0) {
			return (int) Math.floor(horas);
		}
		return 0;

	}

	public int retornarMinutosRelatorio(double horas) {
		if (horas >= 0.0) {
			return (int) Math.floor((horas % 1) * 100);
		}
		return 0;
	}

	public int retornarNumeroMes(MesEnum mes) {
		switch (mes) {
		case JAN:
			return 1;
		case FEV:
			return 2;
		case MAR:
			return 3;
		case ABR:
			return 4;
		case MAI:
			return 5;
		case JUN:
			return 6;
		case JUL:
			return 7;
		case AGO:
			return 8;
		case SET:
			return 9;
		case OUT:
			return 10;
		case NOV:
			return 11;
		case DEZ:
			return 12;

		default:
			return 0;
		}
	}

}
