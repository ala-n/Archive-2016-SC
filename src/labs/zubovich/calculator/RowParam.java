package labs.zubovich.calculator;

import labs.zubovich.calculator.util.DevelopmentTools;
import labs.zubovich.calculator.util.Novelty;
import labs.zubovich.calculator.util.StandartModuleUsageEnum;
import labs.zubovich.ui.RowEditorType;

public enum RowParam {
	LOC(
			"Количество строк кода на выбранном языке написания алгоритма",
			Integer.class,
			new RowEditorType.NumberEditor()
	),
	PROGRAM_LANGUAGE(
			"Язык написания алгоритма",
			DevelopmentTools.class,
			new RowEditorType.SelectType<>(DevelopmentTools.values())
	),
	STANDART_USAGES_K(
			"Коэфиценты, учтывающие степень использования стандартных модулей",
			StandartModuleUsageEnum.class,
			new RowEditorType.SelectType<>(StandartModuleUsageEnum.values())
	),
	NOVELTY_COEFFICIENT(
			"Коэфициент новизны",
			Novelty.class,
			new RowEditorType.SelectType<>(Novelty.values())
	);

//	CoefNew("Коэфициент новизны", Double.class, new RowEditorType.NumberEditor()),
//	Test("Test", Boolean.class, new RowEditorType.TextEditor());

	private String title;
	private Class<?> type;
	private RowEditorType editorType;

	RowParam(String title, Class<?> type) {
		this.title = title;
		this.type = type;
		this.editorType = null;
	}

	RowParam(String title, Class<?> type, RowEditorType editorType) {
		this.title = title;
		this.type = type;
		this.editorType = editorType;
	}

	public String getTitle() {
		return title;
	}

	public Class<?> getType() {
		return type;
	}

	public RowEditorType getEditorType() {
		return editorType;
	}
}

