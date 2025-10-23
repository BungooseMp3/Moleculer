package Construction;

import UI.MoleculeComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.Objects;

public class Element extends MoleculeComponent {
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

    public Element(String symbol){
        bonds = new Bond[getBondNum(symbol)]; // sets bonds to the correct size based on element input
        this.symbol = symbol;
        ar = getArVal(symbol);
        for (int i=0;i<bonds.length;i++){ // iterates through bonds
            bonds[i] = new Bond(this, null, 1); // makes a new bond connecting the current element and a null element
        }

        this.setVisible(true);
        this.initComponent();
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
        System.out.println(this.getSymbol() + " clicked");
    }

    @Override
    protected void paintComponent(Graphics g) {
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
        g2d.drawRect(0,0,this.getWidth(),this.getHeight());

    }

    protected void paintFunction(Graphics2D g2d){
        g2d.drawRect(0,0,this.getWidth(),this.getHeight());
    }
}
