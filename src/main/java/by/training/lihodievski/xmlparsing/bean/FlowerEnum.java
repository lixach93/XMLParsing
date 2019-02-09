package by.training.lihodievski.xmlparsing.bean;

public enum FlowerEnum {

    FLOWERS("flowers"),
    MONOCOTS_FLOWER("monocots-flower"),
    DICOTYLEDONS_FLOWER("dicotyledons-flower"),
    ID("id"),
    SOIL("soil"),
    NAME("name"),
    FIRSTMENTION("firstMention"),
    ORIGIN("origin"),
    MULTIPLYING("multiplying"),
    LEAF_COLOR("leaf-color"),
    STEM_COLOR("stem-color"),
    LENGTH("length"),
    TEMPERATURE("temperature"),
    LIGHTING("lighting"),
    WATERING("watering"),
    VISUAL("visual"),
    GROWING_TIPS("growing-tips");

    private String field;

    FlowerEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
