package UI.UIButtons;

import UI.Tools.CarboxylPlacer;
import UI.Other.Workspace;

public class CarboxylButton extends TextButton{

    public CarboxylButton(Workspace workspace) {
        initTextButton("COOH",0.75,50, workspace);
    }

    @Override
    public void buttonLeftClicked() {
        getWorkspace().setCurrentTool(new CarboxylPlacer(getWorkspace()));
    }
}
