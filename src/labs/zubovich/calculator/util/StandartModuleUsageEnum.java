package labs.zubovich.calculator.util;

/**
 * Created by Alexey on 24.05.2016.
 */
public enum StandartModuleUsageEnum {
	VeryHigh("От 60% и выше", 0.55),
	High("От 40% до 60%", 0.65),
	Middle("От 20% до 40%", 0.77),
	Low("До 20%", 0.9),
	NoUsage("Не используются стандартные модули", 1.0);

	String displayName;
	Double K;

	StandartModuleUsageEnum(String displayName, Double k) {
		this.displayName = displayName;
		K = k;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Double getK() {
		return K;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
