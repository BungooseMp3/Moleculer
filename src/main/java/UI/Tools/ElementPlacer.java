package UI.Tools;
import Construction.Bond;
import Construction.Element;
import UI.Workspace;

import java.awt.event.MouseEvent;

public class ElementPlacer extends Tool {

    public String getElementSymbol() {
        return elementSymbol;
    }

    public void setElementSymbol(String elementSymbol) {
        this.elementSymbol = elementSymbol;
    }

    private String elementSymbol;

    public ElementPlacer(String elementSymbol,Workspace workspace){
        this.setWorkspace(workspace);
        this.setElementSymbol(elementSymbol);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {
        if (element.hasFreeBonds(1)){
            element.getMolecule().addNewNode(element,this.elementSymbol,element.calcNextPos());
        } else {
            System.out.println("no free spaces");
        }
        element.getWorkspace().repaint();
    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        getWorkspace().addNewMolecule(getElementSymbol(),e.getX(),e.getY());
    }

    @Override
    public void onBondClick(MouseEvent e,  Bond bond) {

    }
}
