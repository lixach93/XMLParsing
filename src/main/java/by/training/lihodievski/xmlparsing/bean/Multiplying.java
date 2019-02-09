package by.training.lihodievski.xmlparsing.bean;

public enum Multiplying {

    LEAF("leaf"),
    CUTTING("cutting"),
    SEED("seed");

    private final String value;

    Multiplying(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Multiplying fromValue(String value) {
        for (Multiplying currentEnum: Multiplying.values()) {
            if (currentEnum.value.equals(value)) {
                return currentEnum;
            }
        }
        throw new IllegalArgumentException(value);
    }


}
