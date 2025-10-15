package Construction;

public class Bond {
    private int bondType;
    private Element[] connectedElements;

    public int getBondType() {
        return bondType;
    }

    public void setBondType(int bondType) {
        this.bondType = bondType;
    }

    public Element[] getConnectedElements() {
        return connectedElements;
    }

    public void setConnectedElements(Element[] connectedElements) {
        this.connectedElements = connectedElements;
    }

    public Bond(Element startElement, Element endElement, int bondType){
        this.bondType = bondType;
        this.connectedElements = new Element[2];
        this.connectedElements[0] = startElement;
        this.connectedElements[1] = endElement;
    }
}
