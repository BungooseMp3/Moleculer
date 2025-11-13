package Construction;

import UI.MoleculeComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;

public class Bond extends MoleculeComponent {
    public static int bondHeight = 50;
    public static int bondWidth = 50;
    private int bondType;
    private Element[] connectedElements;
    public int[] bondPos;

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

        this.bondPos = calcAngle(bondIndex);
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
        g2d.drawLine(bondPos[0],bondPos[1],bondPos[2],bondPos[3]);
    }

    @Override
    public void mouseClick(MouseEvent e) {
        System.out.println("Hello");
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
            case 0 -> new int[]{0,bondHeight,bondWidth,0};
            case 1 -> new int[]{0,0,bondWidth,bondHeight};
            case 2 -> new int[]{bondWidth/2,bondHeight,bondWidth/2,0};
            case 3 -> new int[]{bondWidth/2,0,bondWidth/2,bondHeight};
            default -> null;
        };
    }
}
