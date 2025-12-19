package UI.Tools;

import Construction.Element;
import UI.UIButtons.TextButton;
import UI.Workspace;

import java.awt.event.MouseEvent;

public class CarbonChainPlacer extends Tool {
    public int chainLength;

    public CarbonChainPlacer(Workspace workspace, int currentLength) {
        this.chainLength = currentLength;
        this.setWorkspace(workspace);
    }

    @Override
    public void onElementClick(MouseEvent e, Element element) {
        placeCarbonChain(element);
    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        this.getWorkspace().addNewMolecule("C",e.getX(),e.getY());
        placeCarbonChain(this.getWorkspace().getMolecules().getLast().getLastElement());
    }

    @Override
    public void onBondClick(MouseEvent e) {

    }

    public void placeCarbonChain(Element startElement) {
        for (int i = 0; i < chainLength; i++) {
            startElement.getMolecule().addNewNode(startElement,"C",startElement.calcNextPos());
            startElement = startElement.getMolecule().getLastElement();
        }
        this.getWorkspace().repaint();
    }
}
