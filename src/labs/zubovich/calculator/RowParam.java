package labs.zubovich.calculator;

import labs.zubovich.calculator.util.DevelopmentTools;
import labs.zubovich.calculator.util.DifficultyUpperCoefficientEnum;
import labs.zubovich.calculator.util.DificultyCategoryEnum;
import labs.zubovich.calculator.util.Novelty;
import labs.zubovich.calculator.util.StandartModuleUsageEnum;
import labs.zubovich.ui.RowViewType;
import labs.zubovich.ui.TypeUtils;

import java.util.List;

/**
 * Enum for UI fields configuration
 * The order of declaring affects the order of the fields on UI.
 *
 * See values format on constructor description
 * */
public enum RowParam {
	AVG_SALARY (
			"Стоимость человека-дня",
			Double.class,
			new RowViewType.NumberView(0, Double.MAX_VALUE)
	),
//	TEAM_SIZE(
//			"Человек в комманде",
//			Integer.class,
//			new RowViewType.NumberView(1, 1000000),
//			1
//	),
	LOC(
			"Количество строк кода на выбранном языке написания алгоритма",
			Integer.class,
			new RowViewType.NumberView(0, Integer.MAX_VALUE),
			100
	),
	PROGRAM_LANGUAGE(
			"Язык написания алгоритма",
			DevelopmentTools.class,
			new RowViewType.SelectType<>(DevelopmentTools.class)
	),
	STANDART_USAGES_K(
			"Коэфиценты, учтывающие степень использования стандартных модулей",
			StandartModuleUsageEnum.class,
			new RowViewType.SelectType<>(StandartModuleUsageEnum.class)
	),
	DIFICULTY(
			"Категория сложности ПО",
			DificultyCategoryEnum.class,
			new RowViewType.SelectType<>(DificultyCategoryEnum.class)
	),
	DIFFICULTY_UPPER_COEF(
			"Коэффициенты повышения сложности ПО",
			List.class,
			new RowViewType.MultiSelectType<>(DifficultyUpperCoefficientEnum.class)
	),
	HAS_NEW_PC(
			"На основе нового типа ПК",
			Boolean.class,
			new RowViewType.BooleanView()
	),
	HAS_NEW_OS(
			"В среде новой ОС",
			Boolean.class,
			new RowViewType.BooleanView()
	),
	NOVELTY_COEFFICIENT(
			"Коэфициент новизны",
			Novelty.class,
			new RowViewType.SelectType<>(Novelty.class)
	);

	private String title;
	private Class<?> type;
	private RowViewType editorType;
	private Object defaultValue;

	/**
	 * @param title      - description string (shown in the first column of table)
	 * @param type       - JavaClass of value which will be put into the result Map
	 * @param editorType - Instance of  {@link RowViewType} which describe how to show and edit value
	 *  RowViewType contains Default view types for :
	 *     Boolean                              - {@link RowViewType.BooleanView}
	 *     Text(String)                         - {@link RowViewType.TextView}
	 *     Number                               - {@link RowViewType.NumberView}  (can support Double or Integer value)
	 *     Select(Dropdown) from Array or Enum  - {@link RowViewType.SelectType}
	 *     Multy Select from Array or Enum      - {@link RowViewType.MultiSelectType}
	 * */
	RowParam(String title, Class<?> type, RowViewType editorType) {
		this(title, type, editorType, TypeUtils.getDefaultByClass(type));
	}

	/**
	 * @param title         - description string (shown in the first column of table)
	 * @param type          - JavaClass of value which will be put into the result Map
	 * @param editorType    - Instance of  {@link RowViewType} which describe how to show and edit value
	 *  RowViewType contains Default view types for :
	 *     Boolean                              - {@link RowViewType.BooleanView}
	 *     Text(String)                         - {@link RowViewType.TextView}
	 *     Number                               - {@link RowViewType.NumberView}  (can support Double or Integer value)
	 *     Select(Dropdown) from Array or Enum  - {@link RowViewType.SelectType}
	 *     Multy Select from Array or Enum      - {@link RowViewType.MultiSelectType}
	 *  @param defaultValue - default field value
	 * */
	RowParam(String title, Class<?> type, RowViewType editorType, Object defaultValue) {
		this.title = title;
		this.type = type;
		this.editorType = editorType;
		this.defaultValue = defaultValue;
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

	public Object getDefaultValue() {
		return defaultValue;
	}
}

