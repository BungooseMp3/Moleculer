package Construction;

import java.util.Objects;

public class Bond {
    public static int bondLength;
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

    public void setConnectedElement(int i, Element element){
        this.getConnectedElements()[i] = element;
    }

    public Bond(Element startElement, Element endElement, int bondType){
        this.bondType = bondType;
        this.connectedElements = new Element[2];
        this.connectedElements[0] = startElement;
        this.connectedElements[1] = endElement;
    }

    /**
     *returns true if bond is empty (contains null elements)
     */
    public Boolean isEmpty(){
        for (Element each: getConnectedElements()){
            if (Objects.isNull(each)){
                return true;
            }
        }
        return false;
    }
}
