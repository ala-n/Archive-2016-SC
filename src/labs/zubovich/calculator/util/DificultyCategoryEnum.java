package labs.zubovich.calculator.util;

/**
 * Created by Alexey on 24.05.2016.
 */
public enum DificultyCategoryEnum {
	FIRST("1-я", 1),
	SECOND("2-я", 2),
	THIRD("3-я", 3);

	String displayValuse;
	Integer value;

	DificultyCategoryEnum(String displayValuse, Integer value) {
		this.displayValuse = displayValuse;
		this.value = value;
	}

	public String getDisplayValuse() {
		return displayValuse;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return displayValuse;
	}
}
