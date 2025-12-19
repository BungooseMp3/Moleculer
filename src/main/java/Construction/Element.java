package Construction;

import UI.MoleculeComponent;
import UI.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Element extends MoleculeComponent {
    static int elementWidth = 50;
    static int elementHeight = 50;
    private Bond[] bonds;
    private String symbol;
    private double ar;
    private int orientation;
    private Point center;

    public Element(String symbol, Workspace workspace, Point pos, int orientation, Molecule molecule) {
        bonds = new Bond[getBondNum(symbol)]; // sets bonds to the correct size based on element input
        this.symbol = symbol;
        this.orientation = orientation;
        ar = getArVal(symbol);
        for (int i=0;i<bonds.length;i++){ // iterates through bonds
            bonds[i] = new Bond(this, null, 1); // makes a new bond connecting the current element and a null element
        }
        this.initComponent(workspace, new Rectangle(pos.x,pos.y, elementWidth,elementHeight),molecule);// initialises the component

        center = this.getLocation();
        center.x+=elementWidth/2;
        center.y+=elementHeight/2;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Bond[] getBonds() {
        return bonds;
    }

    public void setBonds(Bond[] bonds) {
        this.bonds = bonds;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    public void setAr(float ar) {
        this.ar = ar;
    }

    /**
     *
     * @param num the number of bonds to check for
     * @return returns true if there are the specified or larger than the specified number of free bonds
     */
    public boolean hasFreeBonds(int num){
        for (Bond eachBond: getBonds()){
            if (eachBond.isEmpty()){
                num--;
            }
            if (num == 0){
                break;
            }
        }

        return num == 0;
    }


    /**
     *
     * @param symbol the symbol of the element to be checked
     * @return returns the mass of the specified element
     */
    private double getArVal(String symbol) {
        return ElementReference.atomicMass.get(symbol);
    }

    /**
     *
     * @param symbol the symbol of the element to be checked
     * @return returns the number of bonds the specified element can form
     */
    private int getBondNum(String symbol) {
        return ElementReference.electrons.get(symbol); //returns number of bonds of input element
    }

    /**
     *
     * @param symbol the symbol being looked for
     * @return returns true if the symbol of <code>this</code> is the same as the input, false otherwise
     */
    public boolean isElement(String symbol) {
        return (Objects.equals(this.getSymbol(), symbol)); // returns true if element symbols are the same
    }

    public void mouseClick(MouseEvent e){
        getWorkspace().getCurrentTool().onElementClick(e,this); // calls the relevant element clicked function of the current tool
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

    /**
     * edits a bond of each element to contain both <code>this</code> and the <code>targetNode</code>
     * @param targetNode the node that <code>this</code> will be joined to
     */
    public void joinElements(Element targetNode) {
        Bond currentBond = this.getFirstFreeBond(); //finds the first available bond
        currentBond.setConnectedElement(0,this); //sets the first node in the bond to the element this method is called on
        currentBond.setConnectedElement(1,targetNode);// sets the other node to the input target node
        targetNode.getFirstFreeBond().setConnectedElements(currentBond.getConnectedElements()); // updates the target node's bond list so they share a bond

    }

    /**
     *
     * @return returns the first empty bond
     */
    public Bond getFirstFreeBond() {
        for (int i = 0; i < this.getBonds().length; i++) { // iterates through the chosen element's bond list to find the first bond with an empty element
            if(this.getBonds()[i].isEmpty()){
                return this.getBonds()[i];
            }
        }
        return null;
    }

    /**
     * Calculates the next element position by using trig, then checks if a component is at that spot.
     * If so, it increases the angle by 120 until a free spot is found.
     * If no spaces are free, returns null
     * @return returns the <code>Point</code> where the next element in the chain must be placed
     */
    public Point calcNextPos(){
        int angle = 120;
        Point elementPos = this.getLocation();
        do{ Point currentpos = new Point(elementPos.x +  this.orientation*((int) (100*Math.sin(Math.toRadians(angle)))),elementPos.y + this.orientation*((int) (100*Math.cos(Math.toRadians(angle)))));
            Component c = SwingUtilities.getDeepestComponentAt(this.getWorkspace(), currentpos.x,currentpos.y);
            if(c == this.getWorkspace()|| Objects.isNull(c)){
                return currentpos;
            } else{
                angle+=120;
            }
        } while(angle<720);
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

}
