package UI.UIButtons;

import UI.Tools.Selector;
import UI.Workspace;

public class SelectorButton extends ButtonTemplate {
    public SelectorButton(Workspace workspace) {
        this.setWorkspace(workspace);
        this.initButton();
    }

    @Override
    public void buttonLeftClicked() {
        getWorkspace().setCurrentTool(new Selector(getWorkspace()));
    }
}
