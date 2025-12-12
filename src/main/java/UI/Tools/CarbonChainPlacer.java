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

    }

    @Override
    public void onEmptyClick(MouseEvent e) {
        System.out.println("Yay");
    }

    @Override
    public void onBondClick(MouseEvent e) {

    }
}
