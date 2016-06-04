package labs.zubovich.calculator;

import labs.zubovich.calculator.util.DevelopmentTools;
import labs.zubovich.calculator.util.DifficultyUpperCoefficientEnum;
import labs.zubovich.calculator.util.DificultyCategoryEnum;
import labs.zubovich.calculator.util.Novelty;
import labs.zubovich.calculator.util.StandartModuleUsageEnum;
import labs.zubovich.ui.RowViewType;

import java.util.List;

public enum RowParam {
	LOC(
			"Количество строк кода на выбранном языке написания алгоритма",
			Integer.class,
			new RowViewType.NumberView(0, Integer.MAX_VALUE)
	),
	PROGRAM_LANGUAGE(
			"Язык написания алгоритма",
			DevelopmentTools.class,
			new RowViewType.SelectType<>(DevelopmentTools.values())
	),
	STANDART_USAGES_K(
			"Коэфиценты, учтывающие степень использования стандартных модулей",
			StandartModuleUsageEnum.class,
			new RowViewType.SelectType<>(StandartModuleUsageEnum.values())
	),
	DIFICULTY(
			"Категория сложности ПО",
			DificultyCategoryEnum.class,
			new RowViewType.SelectType<>(DificultyCategoryEnum.values())
	),
	DIFFICULTY_UPPER_COEF(
			"КОЭФФИЦИЕНТЫ ПОВЫШЕНИЯ СЛОЖНОСТИ ПО",
			List.class,
			new RowViewType.MultySelectType<>(DifficultyUpperCoefficientEnum.values())
	),
	NOVELTY_COEFFICIENT(
			"Коэфициент новизны",
			Novelty.class,
			new RowViewType.SelectType<>(Novelty.values())
	);

	private String title;
	private Class<?> type;
	private RowViewType editorType;

	RowParam(String title, Class<?> type, RowViewType editorType) {
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

	public RowViewType getViewType() {
		return editorType;
	}
}

