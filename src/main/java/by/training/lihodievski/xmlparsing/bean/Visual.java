package by.training.lihodievski.xmlparsing.bean;

public class Visual {

    private String leafColor;
    private String stemColor;
    private int length;

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        Visual visual = (Visual) o;

        if (length != visual.length) return false;
        if (!leafColor.equals (visual.leafColor)) return false;
        return stemColor.equals (visual.stemColor);
    }

    @Override
    public int hashCode() {
        int result = leafColor.hashCode ();
        result = 31 * result + stemColor.hashCode ();
        result = 31 * result + length;
        return result;
    }
}
