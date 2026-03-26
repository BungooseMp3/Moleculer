package UI.Tools;

import Construction.Bond;
import Construction.Element;
import UI.Other.Workspace;

import java.awt.event.MouseEvent;

public class CarboxylPlacer extends Tool{
    public CarboxylPlacer(Workspace workspace) {
        setWorkspace(workspace);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {
        element.getMolecule().addNewNode(element,"C",element.calcNextPos(),1);
        addCarboxyl(element.getMolecule().getLastElement());
    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        getWorkspace().addNewMolecule("C",e.getX(),e.getY());
        addCarboxyl(getWorkspace().getMolecules().getLast().getLastElement());
    }

    @Override
    public void onBondClick(MouseEvent e, Bond bond) {

    }

    public void addCarboxyl(Element carbon){
        carbon.getMolecule().addNewNode(carbon,"O",carbon.calcNextPos(),1);
        carbon.getMolecule().addNewNode(carbon,"O",carbon.calcNextPos(),2);
    }
}
