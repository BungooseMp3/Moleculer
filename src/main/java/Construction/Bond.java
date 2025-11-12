package Construction;

import UI.MoleculeComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Bond extends MoleculeComponent {
    private int bondType;
    private Element[] connectedElements;
    public int[] start;
    public int[] end;

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

    public Bond(Element startElement, Element endElement, int bondType, int bondIndex){
        this.bondType = bondType;
        this.connectedElements = new Element[2];
        this.connectedElements[0] = startElement;
        this.connectedElements[1] = endElement;

        int[] bondLocation = startElement.calcNextElementPos(bondIndex);
        bondLocation[0] /= 2;
        bondLocation[1] /= 2;
        this.setPos(startElement.getX()+bondLocation[0],startElement.getY()+bondLocation[1]);
    }

    public Boolean isEmpty(){
        for (Element each: getConnectedElements()){
            if (Objects.isNull(each)){
                return true;
            }
        }
        return false;
    }

    protected void paintComponent(Graphics g) { // handles the drawing of the element
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawRect(0,0,getWidth(),getHeight());

    }

    @Override
    public void mouseClick(MouseEvent e) {

    }

    @Override
    public void setPos(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setWidth(Element.elementSize);
        this.setHeight(Element.elementSize);
    }

    public int[] calcAngle(int bondIndex){
        return switch (bondIndex){
            default -> null;
        };
    }
}
