package UI.Tools;

import Construction.Bond;
import Construction.Element;
import UI.Other.Workspace;

import java.awt.event.MouseEvent;

public class Selector extends Tool {

    public Selector(Workspace workspace) {
        this.setWorkspace(workspace);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {
        getWorkspace().getBottomBar().setCurrentMol(element.getMolecule());
        updateBar();
    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        getWorkspace().getBottomBar().setCurrentMol(null);
        updateBar();
    }

    @Override
    public void onBondClick(MouseEvent e, Bond bond) {
        getWorkspace().getBottomBar().setCurrentMol(bond.getMolecule());
        updateBar();
    }

    public void updateBar(){
        getWorkspace().getBottomBar().repaint();
        getWorkspace().getBottomBar().getNameButton().repaint();
    }
}
