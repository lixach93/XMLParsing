package by.training.lihodievski.xmlparsing.parser;

import by.training.lihodievski.xmlparsing.bean.Flower;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowerParser {

    Set<Flower> flowers;

    AbstractFlowerParser() {
        this.flowers = new HashSet<> ();
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    abstract public void buildSetFlowers(InputStream stream);
}
