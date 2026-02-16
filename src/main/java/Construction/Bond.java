package Construction;

import UI.MoleculeComponent;
import UI.Workspace;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Bond extends MoleculeComponent {
    public static int bondThickness = 5;
    public static int bondLength;
    public static int bondGap = 10;
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

    public Bond(Element startElement, Element endElement, int bondType, Workspace workspace, Molecule molecule){
        this.bondType = bondType;
        this.connectedElements = new Element[2];
        this.connectedElements[0] = startElement;
        this.connectedElements[1] = endElement;
        this.setVisible(false);
    }

    public Rectangle calcBounds() {
        Point p1 = this.getConnectedElements()[0].getCenter();
        Point p2 = this.getConnectedElements()[1].getCenter();

        int x = Math.min(p1.x, p2.x)-bondGap/2*bondType;
        int y = Math.min(p1.y, p2.y)-bondGap/2*bondType;
        int w = Math.abs(p2.x - p1.x)+bondGap*bondType;
        int h = Math.abs(p2.y - p1.y)+bondGap*bondType;

        return new Rectangle(x, y, w, h);
    }

    private Point[] calcPoints(Point origin, Point point1, Point point2, int bondNum) {

        int bondx1 = Math.abs(origin.x-point1.x);
        int bondx2 = Math.abs(origin.x-point2.x);
        int bondy1 = Math.abs(origin.y-point1.y);
        int bondy2 = Math.abs(origin.y-point2.y);

        return new Point[]{new Point(bondx1, bondy1), new Point(bondx2, bondy2)};
    }

    /**
     *returns true if bond contains any null elements
     */
    public Boolean isEmpty(){
        for (Element each: getConnectedElements()){
            if (Objects.isNull(each)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseClick(MouseEvent e) {
        getWorkspace().getCurrentTool().onBondClick(e,this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(bondThickness));
        Point[] points = null;
        for (int i = 0; i < bondType; i++) {
            points = calcPoints(this.getLocation(), this.getConnectedElements()[0].getCenter(), this.getConnectedElements()[1].getCenter(), i);
            g.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
        }
        if (bondType!=1){

            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(Integer.toString(bondType));
            int textAscent = fm.getAscent();
            int textDescent = fm.getDescent();

            int x = (this.getWidth() - textWidth) / 2;
            int y = (this.getHeight() - (textAscent + textDescent)) / 2 + textAscent;

            g2.setColor(Color.red);
            g2.drawString(Integer.toString(bondType), x, y);
        }
    }

    public boolean hasCarbon(){
        for (Element each: getConnectedElements()){
            if (!Objects.isNull(each)){
                if(each.isElement("C")){
                    return true;
                }
            }
        }
        return false;
    }
}
