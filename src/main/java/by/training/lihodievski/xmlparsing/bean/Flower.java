package by.training.lihodievski.xmlparsing.bean;

import java.time.LocalDate;

public class Flower {

    private String id;
    private Soil soil;
    private String name;
    private String origin;
    private Visual visual;
    private LocalDate dateLanding;
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

    public LocalDate getDateLanding() {
        return dateLanding;
    }

    public void setDateLanding(LocalDate dateLanding) {
        this.dateLanding = dateLanding;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        Flower flower = (Flower) o;

        if (!id.equals (flower.id)) return false;
        if (soil != flower.soil) return false;
        if (!name.equals (flower.name)) return false;
        if (!origin.equals (flower.origin)) return false;
        if (!visual.equals (flower.visual)) return false;
        if (!dateLanding.equals (flower.dateLanding)) return false;
        if (!growingTip.equals (flower.growingTip)) return false;
        return multiplying == flower.multiplying;
    }


    @Override
    public int hashCode() {
        int result = id.hashCode ();
        result = 31 * result + soil.hashCode ();
        result = 31 * result + name.hashCode ();
        result = 31 * result + origin.hashCode ();
        result = 31 * result + visual.hashCode ();
        result = 31 * result + dateLanding.hashCode ();
        result = 31 * result + growingTip.hashCode ();
        result = 31 * result + multiplying.hashCode ();
        return result;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id='" + id + '\'' +
                ", soil=" + soil +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", visual=" + visual +
                ", dateLanding=" + dateLanding +
                ", growingTip=" + growingTip +
                ", multiplying=" + multiplying +
                '}';
    }
}
