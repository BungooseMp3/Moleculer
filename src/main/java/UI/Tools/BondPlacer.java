package UI.Tools;

import Construction.Bond;
import Construction.Element;
import UI.Other.Workspace;

import java.awt.event.MouseEvent;

public class BondPlacer extends Tool {
    public int getCurrentBondType() {
        return currentBondType;
    }

    public void setCurrentBondType(int currentBondType) {
        this.currentBondType = currentBondType;
    }

    private int currentBondType;
    private Element[] selectedElements = new Element[2];

    public BondPlacer(Workspace workspace, int bondtype){
        this.setCurrentBondType(bondtype);
        this.setWorkspace(workspace);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {
        if(element.hasFreeBonds(currentBondType)){
            if(!element.equals(selectedElements[0])) {
                for (int i = 0; i < 2; i++) {
                    if (selectedElements[i] == null) {
                        selectedElements[i] = element;
                        break;
                    }
                }

                if (selectedElements[1] != null) {
                    selectedElements[0].makeBond(selectedElements[1], currentBondType);
                    selectedElements[0] = null;
                    selectedElements[1] = null;
                }
            }

        }

    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        selectedElements[0] = null;
        selectedElements[1] = null;
    }

    @Override
    public void onBondClick(MouseEvent e, Bond bond) {
        if(bond.getBondType()<currentBondType){
            bond.getConnectedElements()[0].makeBond(bond.getConnectedElements()[1], currentBondType-bond.getBondType());
        }
    }
}
