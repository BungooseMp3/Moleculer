package UI.UIButtons.NumButtons;

import UI.LeftBar.Toolbar;
import UI.Tools.BondPlacer;
import UI.Tools.CarbonChainPlacer;
import UI.Workspace;

import java.awt.*;


public class CarbonChainButton extends NumberSelectionButton{

    public CarbonChainButton(Workspace workspace){
        this.currentNumber = 1;
        this.setWorkspace(workspace);
        this.initNumberButton("<html>C<sub><font size='5'>n</font></sub></html>",16,70,workspace);
    }



    @Override
    void buttonRightClicked() {

    }

    @Override
    public void buttonLeftClicked() {
        getWorkspace().setCurrentTool(new CarbonChainPlacer(getWorkspace(),this.currentNumber));
    }

    @Override
    void updateText(String text) {
        this.setText("<html>C<sub><font size='5'>"+text+"</font></sub></html>");
    }
}
