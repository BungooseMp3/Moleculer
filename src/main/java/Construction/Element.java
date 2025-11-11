package Construction;

import UI.MoleculeComponent;
import UI.Workspace;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.Objects;

public class Element extends MoleculeComponent {
    static int molDistance = 50;
    private Bond[] bonds;
    private String symbol;
    private double ar;

    public void setAr(double ar) {
        this.ar = ar;
    }

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

    public Element(String symbol, Workspace workspace, int x, int y){
        bonds = new Bond[getBondNum(symbol)]; // sets bonds to the correct size based on element input
        this.symbol = symbol;
        ar = getArVal(symbol);
        for (int i=0;i<bonds.length;i++){ // iterates through bonds
            bonds[i] = new Bond(this, null, 1); // makes a new bond connecting the current element and a null element
        }

        this.setPos(x,y);
        setWidth(50);
        setHeight(50);
        this.initComponent(workspace);// initialises the component
    }

    public boolean hasFreeBonds(int num){
        for (Bond eachBond: getBonds()){
            if (eachBond.isEmpty()){
                num--;
            }
            if (num == 0){
                break;
            }
        }

        if(num == 0){
            return true;
        } else {
            return false;
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

    public void mouseClick(MouseEvent e){
        getWorkspace().getCurrentTool().onElementClick(e,this); // calls the relevant element clicked function of the current tool
    }

    @Override
    public void setPos(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    protected void paintComponent(Graphics g) { // handles the drawing of the element
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        long fontSize = Math.round(this.getHeight()/1.1);
        g2d.setFont(new Font("Arial",Font.PLAIN, (int) fontSize ));

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(this.getSymbol());
        int textAscent = fm.getAscent();
        int textDescent = fm.getDescent();

        int x = (this.getWidth() - textWidth) / 2;
        int y = (this.getHeight() - (textAscent + textDescent)) / 2 + textAscent;

        g2d.drawString(this.getSymbol(),x, y);
    }

    public void joinElements(Element targetNode) {
        Bond currentBond = targetNode.getFirstFreeBond(); //finds the first available bond
        currentBond.setConnectedElement(0,this); //sets the first node in the bond to the element this method is called on
        currentBond.setConnectedElement(1,targetNode);// sets the other node to the input target node
        this.getBonds()[targetNode.posOfBond(currentBond)].setConnectedElements(currentBond.getConnectedElements()); // updates the target node's bond list so they share a bond

    }

    private Bond getFirstFreeBond() {
        for (int i = 0; i < this.getBonds().length; i++) { // iterates through the chosen element's bond list to find the first bond with an empty element
            if(this.getBonds()[i].isEmpty()){
                return this.getBonds()[i];
            }
        }
        return null;
    }

    private int getFirstFreeBondPos() {
        for (int i = 0; i < this.getBonds().length; i++) { // iterates through the chosen element's bond list to find the first bond with an empty element
            if(this.getBonds()[i].isEmpty()){
                return i;
            }
        }
        return -1;
    }

    public int[] calcNextElementPos() {
        int bondPos = this.getFirstFreeBondPos();
        System.out.println(bondPos);
        return switch (bondPos) {
            case 0 -> new int[]{50, -50};
            case 1 -> new int[]{-50, -50};
            case 2 -> new int[]{0, 50};
            case 3 -> new int[]{0, -50};
            default -> null;
        };
    }

    public int posOfBond(Bond bond){
        for (int i = 0; i < this.getBonds().length; i++) {
            if (this.getBonds()[i]==bond){
                return i;
            }
        }
        return -1;
    }
}
