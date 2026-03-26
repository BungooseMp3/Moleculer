package UI.Tools;

import Construction.Bond;
import Construction.Element;
import Construction.Molecule;
import UI.Other.Workspace;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Deleter extends Tool{

    public Deleter(Workspace workspace) {
        this.setWorkspace(workspace);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {
        Workspace workspace = getWorkspace();
        Molecule molecule = element.getMolecule();
        workspace.getMolecules().remove(molecule);
        for(Element element1:molecule.getElementList()){
            for(Bond bond:element1.getBonds()){
                workspace.remove(bond);
            }
            workspace.remove(element1);
        }
        molecule = null;
        workspace.repaint();
    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        getWorkspace().setMolecules(new ArrayList<>());
        for(Component component:getWorkspace().getComponents()){
            getWorkspace().remove(component);
        }
    }

    @Override
    public void onBondClick(MouseEvent e, Bond bond) {

    }
}
