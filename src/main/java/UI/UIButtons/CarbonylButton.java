package UI.UIButtons;

import UI.Tools.CarbonylPlacer;
import UI.Other.Workspace;

public class CarbonylButton extends TextButton{
    public CarbonylButton(Workspace workspace) {
        this.initTextButton("=O",0.75,50,workspace);
    }
    @Override
    public void buttonLeftClicked() {
        getWorkspace().setCurrentTool(new CarbonylPlacer(getWorkspace()));
    }
}
