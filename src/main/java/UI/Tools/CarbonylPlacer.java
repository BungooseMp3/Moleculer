package UI.Tools;

import Construction.Bond;
import Construction.Element;
import UI.Other.Workspace;

import java.awt.event.MouseEvent;

public class CarbonylPlacer extends Tool{
    public CarbonylPlacer(Workspace workspace){
        setWorkspace(workspace);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {
        addCarbonyl(element);
    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        getWorkspace().addNewMolecule("C",e.getX(),e.getY());
        addCarbonyl(getWorkspace().getMolecules().getLast().getLastElement());
    }

    @Override
    public void onBondClick(MouseEvent e, Bond bond) {

    }

    public void addCarbonyl(Element element){
        element.getMolecule().addNewNode(element,"O",element.calcNextPos(),2);
    }
}
