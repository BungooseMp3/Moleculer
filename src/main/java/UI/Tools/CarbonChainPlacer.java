package UI.Tools;

import Construction.Bond;
import Construction.Element;
import UI.Other.Workspace;

import java.awt.event.MouseEvent;

public class CarbonChainPlacer extends Tool {
    public int chainLength;

    public CarbonChainPlacer(Workspace workspace, int currentLength) {
        this.chainLength = currentLength;
        this.setWorkspace(workspace);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {
        placeCarbonChain(element,chainLength);
    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        this.getWorkspace().addNewMolecule("C",e.getX(),e.getY());
        placeCarbonChain(this.getWorkspace().getMolecules().getLast().getLastElement(),chainLength-1);
    }

    @Override
    public void onBondClick(MouseEvent e, Bond bond) {

    }

    public void placeCarbonChain(Element startElement,int length) {
        for (int i = 0; i < length; i++) {
            startElement.getMolecule().addNewNode(startElement,"C",startElement.calcNextPos(),1);
            startElement = startElement.getMolecule().getLastElement();
        }
        this.getWorkspace().repaint();
    }
}
