package UI;

import Construction.Element;
import Construction.Molecule;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoleculeName extends MoleculeComponent {
    public MoleculeName(Molecule molecule) {
        this.initComponent(molecule.getWorkspace(),this.calcBounds(molecule),molecule);
    }

    private Rectangle calcBounds(Molecule molecule) {
        int x =-1;
        int y =-1;
        int w =-1;

        for(Element element:molecule.getElementList()){
            if(x==-1||element.getX()<x){
                x=element.getX();
            }
            if(y==-1||element.getY()>y){
                y=element.getY();
            }
            if(w==-1||element.getX()-x>w){
                w=element.getX()-x;
            }
        }
        return new Rectangle(x-Element.elementWidth/2,y+Element.elementHeight,w+2*Element.elementWidth,30);
    }

    @Override
    public void mouseClick(MouseEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial",Font.PLAIN,15));
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(getMolecule().getName(),(getWidth()-fm.stringWidth(getMolecule().getName()))/2+1,(getHeight()+fm.getAscent())/2-1);
    }
}
