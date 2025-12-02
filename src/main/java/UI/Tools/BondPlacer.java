package UI.Tools;

import Construction.Element;
import UI.Workspace;

import java.awt.event.MouseEvent;

public class BondPlacer extends Tool {
    public int getCurrentBondType() {
        return currentBondType;
    }

    public void setCurrentBondType(int currentBondType) {
        this.currentBondType = currentBondType;
    }

    private int currentBondType;

    public BondPlacer(Workspace workspace, int bondtype){
        this.setCurrentBondType(bondtype);
        this.setWorkspace(workspace);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {

    }

    @Override
    public void onEmptyClick(MouseEvent e) {

    }

    @Override
    public void onBondClick(MouseEvent e) {

    }
}
