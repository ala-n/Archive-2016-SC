package labs.zubovich.calculator.util;


public enum Weights {
    TZ_STAGE("Стадия разработки ТЗ", 0.12, 0.10, 0.08),
    EP_STAGE("Стадия анализа требований)", 0.21, 0.20, 0.19),
    TP_STAGE("Стадия проектирования", 0.32, 0.30, 0.28),
    RP_STAGE("Стадия разработки", 0.26, 0.30, 0.34),
    VN_STAGE("Стадия ввода в действие", 0.09, 0.10, 0.11);

    String displayName;
    Double absolutelyNewValue;
    Double middleNewValue;
    Double lowNewValue;

    Weights(String displayName, Double absolutelyNewValue, Double middleNewValue, Double lowNewValue) {
        this.displayName = displayName;
        this.absolutelyNewValue = absolutelyNewValue;
        this.middleNewValue = middleNewValue;
        this.lowNewValue = lowNewValue;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Double getAbsolultelyNewValue() {
        return absolutelyNewValue;
    }

    public Double getMiddleNewValue() {
        return middleNewValue;
    }

    public Double getLowNewValue() {
        return lowNewValue;
    }

}
