package labs.zubovich.calculator.util;

import java.util.List;

/**
 * Created by Alexey on 24.05.2016.
 */
public enum DifficultyUpperCoefficientEnum {
	AdvansedEnv("Функционирование ПО в расширенной операционной среде (связь с другими ПО)", 0.08),
	InteractiveAccess("Интерактивный доступ", 0.06),
	DificultStructures("Обеспечение хранения, ведения и поиска данных в сложных структурах", 0.07),
	Two("2 характеристики", 0.12),
	Three("3 характеристики", 0.18),
	MoreThenThree("свыше 3 характеристик", 0.26);

	String displayValue;
	Double value;

	DifficultyUpperCoefficientEnum(String displayValue, Double value) {
		this.displayValue = displayValue;
		this.value = value;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return displayValue;
	}

	public static Double getValue(List<DifficultyUpperCoefficientEnum> list) {
		return list.stream().mapToDouble(v -> v.getValue()).sum();
	}
}
