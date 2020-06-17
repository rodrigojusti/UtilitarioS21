package enums;

public enum MesEnum {
	JAN("Jan"),
	FEV("Feb"),
	MAR("Mar"),
	ABR("Apr"),
	MAI("May"),
	JUN("Jun"),
	JUL("Jul"),
	AGO("Aug"),
	SET("Sep"),
	OUT("Oct"),
	NOV("Nov"),
	DEZ("Dec");
	
	public String valorMes;

	MesEnum(String string) {
		valorMes = string;
	}
	
	
}
