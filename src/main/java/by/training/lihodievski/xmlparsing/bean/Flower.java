package by.training.lihodievski.xmlparsing.bean;

public class Flower {

    private String id;
    private Soil soil;
    private String name;
    private String origin;
    private Visual visual;
    private GrowingTip growingTip;
    private Multiplying multiplying;

     Flower() {
        this.visual =  new Visual ();
        this.growingTip = new GrowingTip ();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Visual getVisual() {
        return visual;
    }

    public void setVisual(Visual visual) {
        this.visual = visual;
    }

    public GrowingTip getGrowingTip() {
        return growingTip;
    }

    public void setGrowingTip(GrowingTip growingTip) {
        this.growingTip = growingTip;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }
}
