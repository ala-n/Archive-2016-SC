package labs.zubovich.calculator.util;

/**
 * Created by Alexey on 24.05.2016.
 */
public enum DevelopmentTools {
	HIGH_LEVEL("Процедурные языки высокого уровня (С++, Паскаль)", 1.0, 1.2, 1.3),
	FORE_GL("Языки 4GL (Visual Basic, Delphi)", 0.8, 0.95, 1.1),
	DB_FoxproType("Системы программирования на основе СУБД типа Foxpro", 0.45, 0.55, 0.65),
	DB_Oracle("Системы программирования на основе СУБД типа Огас1е, SQLServer", 0.4, 0.5, 0.6),
	OOTech("Объектно-ориентированные технологии (COM/DCOM, CORBA)", 0.55, 0.6, 0.7),
	BPWIN("Средства проектирования BPWIN/ERWINERX", 0.16, 0.19, 0.22),
	CASE_TOOLS("Прочие CASE-средства", 0.19, 0.22, 0.25);

	String displayName;
	Double IBM_PC_OR_WIN;
	Double LOCAL_NET;
	Double GLOBAL_NET;

	DevelopmentTools(String displayName, Double IBM_PC_OR_WIN, Double LOCAL_NET, Double GLOBAL_NET) {
		this.displayName = displayName;
		this.IBM_PC_OR_WIN = IBM_PC_OR_WIN;
		this.LOCAL_NET = LOCAL_NET;
		this.GLOBAL_NET = GLOBAL_NET;
	}

	@Override
	public String toString() {
		return displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Double getIBM_PC_OR_WIN() {
		return IBM_PC_OR_WIN;
	}

	public Double getLOCAL_NET() {
		return LOCAL_NET;
	}

	public Double getGLOBAL_NET() {
		return GLOBAL_NET;
	}
}
