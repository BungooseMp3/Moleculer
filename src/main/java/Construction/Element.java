package Construction;

import java.util.Objects;

public class Element {
    private Bond[] bonds;
    private String symbol;
    private double ar;

    public Bond[] getBonds() {
        return bonds;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setBonds(Bond[] bonds) {
        this.bonds = bonds;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(float ar) {
        this.ar = ar;
    }

    public Element(String symbol){
        bonds = new Bond[getBondNum(symbol)]; // sets bonds to the correct size based on element input
        this.symbol = symbol;
        ar = getArVal(symbol);
        for (int i=0;i<bonds.length;i++){ // iterates through bonds
            bonds[i] = new Bond(this, null, 1); // makes a new bond connecting the current element and a null element
        }
    }

    private double getArVal(String symbol) {
        return ElementReference.atomicMass.get(symbol); //returns the atomic mass of input element
    }

    private int getBondNum(String symbol) {
        return ElementReference.electrons.get(symbol); //returns number of bonds of input element
    }


    public boolean isElement(String elementID) {
        return (Objects.equals(this.getSymbol(), elementID)); // returns true if element symbols are the same
    }
}
