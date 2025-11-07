package UI.Tools;
import Construction.Element;
import Construction.Molecule;
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
        Molecule currentMol = findMolWith(element);
        currentMol.addNewNode(element,this.getElementSymbol());
        getWorkspace().repaint();
    }

    private Molecule findMolWith(Element element) {
        for (int i = 0; i < getWorkspace().getMolecules().size() ; i++) {
            if(getWorkspace().getMolecules().contains(element)){
                return getWorkspace().getMolecules().get(i);
            }
        }
        return null;
    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        getWorkspace().addNewMolecule(getElementSymbol(),e);
    }

    @Override
    public void onBondClick(MouseEvent e) {

    }
}
