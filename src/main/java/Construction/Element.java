package Construction;

import UI.MoleculeComponent;
import UI.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class Element extends MoleculeComponent {
    public static int elementWidth = 60;
    public static int elementHeight = 60;
    private Bond[] bonds;
    private String symbol;
    private double ar;
    private int orientation;
    private Point center;

    public int getChainPos() {
        return chainPos;
    }

    public void setChainPos(int chainPos) {
        this.chainPos = chainPos;
    }

    public int chainPos;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color color = new Color(Color.BLACK.getRGB());

    public ArrayList<FuncGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<FuncGroup> groups) {
        this.groups = groups;
    }

    private ArrayList<FuncGroup> groups;

    public Element(String symbol, Workspace workspace, Point pos, int orientation, Molecule molecule) {
        bonds = new Bond[getBondNum(symbol)]; // sets bonds to the correct size based on element input
        this.symbol = symbol;
        this.orientation = orientation;
        ar = getArVal(symbol);
        this.initComponent(workspace, new Rectangle(pos.x, pos.y, elementWidth, elementHeight), molecule);// initialises the component
        center = this.getLocation();
        center.x += elementWidth / 2;
        center.y += elementHeight / 2;

        for (int i = 0; i < bonds.length; i++) { // iterates through bonds
            bonds[i] = new Bond(this, null, 1, workspace, getMolecule()); // makes a new bond connecting the current element and a null element
        }
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
    public boolean hasFreeBonds(int num) {
        for (Bond eachBond : getBonds()) {
            if (eachBond.isEmpty()) {
                num--;
            }
            if (num == 0) {
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

    public void mouseClick(MouseEvent e) {
        getWorkspace().getCurrentTool().onElementClick(e, this); // calls the relevant element clicked function of the current tool
    }

    @Override
    protected void paintComponent(Graphics g) { // handles the drawing of the element
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



        if (getSymbol().equals("C")) {

            g2d.setColor(color);
            g2d.fillOval(elementWidth / 2 - 5, elementHeight / 2 - 5, 10, 10);

        } else {

            String text = calcSymbol(getSymbol());

            g2d.setColor(new Color(255, 255, 255));
            g2d.fillRoundRect(0, 0, (int) (elementWidth * 0.95), (int) (elementHeight * 0.95), 30, 50);
            g2d.setColor(color);
            long fontSize = Math.round(this.getHeight() / 1.55);
            g2d.setFont(new Font("Arial", Font.PLAIN, (int) fontSize));

            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textAscent = fm.getAscent();
            int textDescent = fm.getDescent();

            int x = (this.getWidth() - textWidth) / 2;
            int y = (this.getHeight() - (textAscent + textDescent)) / 2 + textAscent;

            g2d.drawString(text, x, y);

            if (text == "NH" && this.hasFreeBonds(2)) {
                g2d.setFont(new Font("Arial", Font.PLAIN, (int) (fontSize * 0.4)));
                g2d.drawString("2", (int) (elementWidth * 0.85), (int) (y + elementWidth * 0.2));
            }
        }

    }

    public void makeBond(Element element2, int bondType) {
        if (this.hasFreeBonds(bondType) && element2.hasFreeBonds(bondType)) {
            groupUpdate(new Element[]{this, element2});
            for (int i = 0; i < bondType; i++) {
                this.joinElements(element2);
            }
            this.updateBonds(element2);
            getWorkspace().repaint();
        }
    }

    /**
     * edits a bond of each element to contain both <code>this</code> and the <code>targetNode</code>
     *
     * @param targetNode the node that <code>this</code> will be joined to
     */

    public void joinElements(Element targetNode) {

        Bond currentBond = this.getFirstFreeBond();//finds the first available bond
        currentBond.setBondType(1);
        currentBond.setConnectedElement(0, this); //sets the first node in the bond to the element this method is called on
        currentBond.setConnectedElement(1, targetNode);// sets the other node to the input target node
        targetNode.getBonds()[targetNode.getFirstFreeBondPos()] = currentBond; // updates the target node's bond list so they share a bond

        if (targetNode.getMolecule() != this.getMolecule()) {
            this.getMolecule().joinMolecules(targetNode.getMolecule());
        }

        if (!currentBond.isVisible()) {
            currentBond.initComponent(getWorkspace(), currentBond.calcBounds(), getMolecule());
        }

        currentBond.getConnectedElements()[0].repaint();
        currentBond.getConnectedElements()[1].repaint();

    }

    private int getFirstFreeBondPos() {
        for (int i = 0; i < this.getBonds().length; i++) { // iterates through the chosen element's bond list to find the first bond with an empty element
            if (this.getBonds()[i].isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @return returns the first empty bond
     */
    public Bond getFirstFreeBond() {
        for (int i = 0; i < this.getBonds().length; i++) { // iterates through the chosen element's bond list to find the first bond with an empty element
            if (this.getBonds()[i].isEmpty()) {
                return this.getBonds()[i];
            }
        }
        return null;
    }

    /**
     * Calculates the next element position by using trig, then checks if a component is at that spot.
     * If so, it increases the angle by 120 until a free spot is found.
     * If no spaces are free, returns null
     *
     * @return returns the <code>Point</code> where the next element in the chain must be placed
     */
    public Point calcNextPos() {
        int angle = 120;
        Point elementPos = this.getLocation();
        do {
            Point currentpos = new Point(elementPos.x + this.orientation * ((int) (100 * Math.sin(Math.toRadians(angle)))), elementPos.y + this.orientation * ((int) (100 * Math.cos(Math.toRadians(angle)))));
            Component c = SwingUtilities.getDeepestComponentAt(this.getWorkspace(), currentpos.x, currentpos.y);
            if (c == this.getWorkspace() || Objects.isNull(c)) {
                return currentpos;
            } else {
                angle += 120;
            }
        } while (angle < 720);
        return null;
    }

    public String calcSymbol(String symbol) {

        if (symbol.equals("O") && this.hasFreeBonds(1)) {
            symbol = "OH";
        } else if (symbol.equals("N") && this.hasFreeBonds(1)) {
            symbol = "NH";
        }

        return symbol;
    }

    public void updateBonds(Element element2) {
        ArrayList<Bond> bonds = new ArrayList<Bond>();

        for (int i = 0; i < this.getBonds().length; i++) {
            if (Arrays.asList(this.getBonds()[i].getConnectedElements()).contains(this) && Arrays.asList(this.getBonds()[i].getConnectedElements()).contains(element2)) {
                bonds.add(this.getBonds()[i]);
            }
        }

        if (bonds.size() > 1) {
            for (Bond bond : bonds) {
                bond.setBondType(bonds.size());
                this.getMolecule().setMr(this.getMolecule().getMr());
                bond.repaint();
            }
        }

    }

    public static void groupUpdate(Element[] elements) {
        /*if(this.isElement("C")){
            if(!newElement.isElement("C")){
                FuncGroup newGroup = new FuncGroup(newElement);
                newGroup.addAttachedCarbon(this);
                this.getMolecule().addFuncGroup(newGroup);
            }
        } else {
            FuncGroup currentGroup = this.getMolecule().findGroupWith(this);
            if(newElement.isElement("C")){
                currentGroup.addAttachedCarbon(newElement);
            } else {
                currentGroup.addContainedElement(this);
            }
        }*/

        if (!(elements[0].isElement("C") && elements[1].isElement("C"))) {
            for (Element currentElement : elements) {
                FuncGroup group = currentElement.getMolecule().findGroupWith(currentElement);
                if (group == null) {
                    group = new FuncGroup(currentElement);
                    currentElement.getMolecule().addFuncGroup(group);
                }
            }
            elements[0].addGroups(elements[1]);
        }

    }

    private void addGroups(Element element) {
        FuncGroup group1 = this.getMolecule().findGroupWith(this);
        FuncGroup group2 = element.getMolecule().findGroupWith(element);
        if (!group1.equals(group2)) {
            group1.getContainedElements().addAll(group2.getContainedElements());
            group1.getAttachedCarbons().addAll(group2.getAttachedCarbons());

            element.getMolecule().getGroupList().remove(group2);
            group2 = null;
        }
    }

    public int getOccupiedBondNum() {
        int num = 0;
        for (int i = 0; i < this.getBonds().length; i++) {
            if (this.getBonds()[i].getBondType() == 2) {
                num--;
            }
            if (!this.getBonds()[i].isEmpty()) {
                num++;
            }
        }
        return num;
    }


    public void changeToGroup(String name, FuncGroup funcGroup) {
        FuncGroup newGroup = new FuncGroup(this);
        newGroup.setAttachedCarbons(funcGroup.getAttachedCarbons());
        newGroup.setGroupName(name);
        funcGroup.getContainedElements().remove(this);
        if (funcGroup.getContainedElements().isEmpty()) {
            funcGroup.getMolecule().getGroupList().remove(funcGroup);
            for(Element element : funcGroup.getAttachedCarbons()) {
                element.getGroups().remove(funcGroup);
            }
            funcGroup = null;
        }
    }

    public void checkOxygens(FuncGroup funcGroup, Bond[] bondlist) {
        int carbonCount = 0;

        for (Bond bond : bondlist) {
            if (bond.hasCarbon()) {
                if (bond.getBondType() == 2) {
                    this.changeToGroup("carbonyl", funcGroup);
                } else {
                    carbonCount++;
                }
            }
        }

        if (carbonCount == 1 && this.hasFreeBonds(1)) {
            this.changeToGroup("hydroxy", funcGroup);
        } else if (carbonCount == 2) {
            this.changeToGroup("ester link", funcGroup);
        }
    }

    public void checkNitrogens(FuncGroup funcGroup, Bond[] bondlist) {
        for (Bond bond : bondlist) {
            if (bond.hasCarbon()) {
                if (bond.getBondType() == 3) {
                    this.changeToGroup("nitrile", funcGroup);
                    break;
                } else if (bond.getBondType() == 1) {
                    this.changeToGroup("amine", funcGroup);
                    break;
                }
            }
        }
    }

    public void checkHalogens(FuncGroup funcGroup) {
        if (this.getBonds()[0].hasCarbon()) {
            this.changeToGroup("halogen", funcGroup);
        }
    }

    public void checkAlkenes(){
        if(this.isElement("C")){
            for(Bond bond : this.getBonds()){
                if(bond.getBondType()>1){
                    Element element1 = bond.getConnectedElements()[0];
                    Element element2 = bond.getConnectedElements()[1];
                    if ((Objects.equals(element1.getSymbol(), "C") && Objects.equals(element2.getSymbol(), "C"))&&element1.isNotAlkeneWith(element2)){
                        FuncGroup group = new FuncGroup(element1);
                        group.addElement(element2);
                        if(bond.getBondType()==2){
                            group.setGroupName("alkene");
                        } else if (bond.getBondType()==3){
                            group.setGroupName("alkyne");
                        }
                        element1.addGroup(group);
                    }

                }
            }
        }
    }


    public void addGroup(FuncGroup funcGroup) {
        this.getGroups().add(funcGroup);
        if(!this.getMolecule().getGroupList().contains(funcGroup)) {
            this.getMolecule().getGroupList().add(funcGroup);
        }
    }

    public void updateGroups() {
        if(this.getGroups().size()>1){
            FuncGroup group1 = null;
            FuncGroup group2 = null;

            for(FuncGroup funcGroup : this.getGroups()) {
                if(funcGroup.getGroupName()!=null){
                    if(funcGroup.getGroupName().equals("carbonyl")){
                        group1 = funcGroup;
                    } else if(funcGroup.getGroupName().equals("hydroxy")||funcGroup.getGroupName().equals("amine")||funcGroup.getGroupName().equals("halogen")||funcGroup.getGroupName().equals("ester link")){
                        group2 = funcGroup;
                    }
                }
            }

            if(group1!=null&&group2!=null){
                group1.combineWith(group2);
            }
        }

    }


    public void removeGroup(FuncGroup group2) {
        this.getGroups().remove(group2);
    }

    public Bond[] makeCleanBondList() {
        ArrayList<Bond> bondlist = new ArrayList<Bond>();
        boolean isIn = false;
        for(Bond bond1 : this.getBonds()){
            for(Bond bond2 :bondlist){
                if(bond1.getConnectedElements()[0]==bond2.getConnectedElements()[0]&&bond1.getConnectedElements()[1]==bond2.getConnectedElements()[1]){
                    isIn = true;
                }
            }
            if(!isIn){
                bondlist.add(bond1);
            }

        }
        return bondlist.toArray(new Bond[bondlist.size()]);
    }

    public boolean isNotAlkeneWith(Element element) {
        if(this.getGroups()!=null){
            for(FuncGroup funcGroup : this.getGroups()) {
                if(funcGroup.getAttachedCarbons().contains(element)) {
                    return false;
                }
            }
        }

        return true;
    }

    public Element getFirstAdjCarbon(ArrayList<Element> checkedNodes, Stack<Element> stack) {
        for(Bond bond : this.getBonds()){
            for(Element element : bond.getConnectedElements()){
                if(!Objects.isNull(element)&&element.isElement("C")&&element!=this&&!checkedNodes.contains(element)&&!stack.contains(element)){
                    return element;
                }
            }
        }
        return null;
    }
}





