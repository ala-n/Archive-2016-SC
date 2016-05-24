package labs.zubovich.calculator.util;

public enum Novelty {
    ABSOLUTELY_NEW("Принципиально новые ПО, не имеющие подобных аналогов", 1.58, 1.44, 1.10, 1.0),
    MIDDLE_NEW("ПО, являющиеся развитием определенного параметрического ряда ПО)", 1.0, 0.81, 0.72, 0.63),
    LOW_NEW("ПО, являющиеся развитием определенного параметрического ряда ПО)", 0.63, 0.63, 0.63, 0.63);

    String displayName;
    Double value;
    Double value2;
    Double value3;
    Double value4;

    Novelty(String displayName, Double value, Double value2, Double value3, Double value4) {
        this.displayName = displayName;
        this.value = value;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Double getValue() {
        return value;
    }

    public Double getValue2() {
        return value2;
    }

    public Double getValue3() {
        return value3;
    }

    public Double getValue4() {
        return value4;
    }

    public Double getCofficient(boolean hasNewPc, boolean hasNewOs){
        if (hasNewOs && hasNewPc) {
            return this.getValue();
        }
        if (!hasNewOs && hasNewPc) {
            return this.getValue2();
        }
        if (hasNewOs && !hasNewPc) {
            return this.getValue3();
        }
        if (!hasNewOs && !hasNewPc) {
            return this.getValue4();
        }
        return 0.;
    }
}
