package UI.Tools;

import Construction.Bond;
import Construction.Element;
import Construction.Molecule;
import UI.Workspace;

import java.awt.event.MouseEvent;

public class Selector extends Tool {

    public Selector(Workspace workspace) {
        this.setWorkspace(workspace);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {
        getWorkspace().getBottomBar().setCurrentMol(element.getMolecule());
        getWorkspace().getBottomBar().repaint();
    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        getWorkspace().getBottomBar().setCurrentMol(null);
        getWorkspace().getBottomBar().repaint();
    }

    @Override
    public void onBondClick(MouseEvent e, Bond bond) {
        getWorkspace().getBottomBar().setCurrentMol(bond.getMolecule());
        getWorkspace().getBottomBar().repaint();
    }
}
