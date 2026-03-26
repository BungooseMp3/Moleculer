package UI.LeftBar;

import UI.Other.GridTemplate;
import UI.UIButtons.*;
import UI.UIButtons.Halogens.HalogenMenu;
import UI.UIButtons.NumButtons.CarbonChainButton;
import UI.UIButtons.NumButtons.SelectionButtonWrapper;
import UI.Other.Workspace;

import java.awt.*;

public class Toolbar extends GridTemplate {
    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    private Workspace workspace;

    public Toolbar (Workspace workspace, int radius){
        this.initGrid(6,2, new Dimension(200,520),radius,new Color(190,10,10),workspace);

        add(new BondButton(1,workspace));
        this.add(new BondButton(2, workspace));
        this.add(new BondButton(3, workspace));
        this.add(new SelectionButtonWrapper(new CarbonChainButton(workspace)));
        this.add(new ElementButton("O",0.75,70,workspace));
        this.add(new ElementButton("N",0.75,70,workspace));
        this.add(new ElementButton("C",0.75,70,workspace));
        this.add(new CarboxylButton(workspace));
        this.add(new CarbonylButton(workspace));
        this.add(new HalogenMenu(workspace));
        this.add(new SelectorButton(workspace));
        this.add(new DeletionButton(workspace));
    }
}
