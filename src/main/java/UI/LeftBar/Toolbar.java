package UI.LeftBar;

import UI.GridTemplate;
import UI.UIButtons.BondButton;
import UI.UIButtons.ElementButton;
import UI.UIButtons.Halogens.HalogenButton;
import UI.UIButtons.Halogens.HalogenMenu;
import UI.Workspace;

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
        this.initGrid(8,2, new Dimension(200,800),radius,new Color(190,10,10),workspace);

        this.add(new BondButton(1));
        this.add(new BondButton(2));
        this.add(new BondButton(3));
        this.add(new ElementButton("C",0.75,70,workspace));
        this.add(new HalogenMenu(workspace));
        this.add(new ElementButton("H",0.75,70,workspace));
        this.add(new ElementButton("O",0.75,70,workspace));
        this.add(new ElementButton("N",0.75,70,workspace));

        for (int i = 0; i < 8; i++) {
            this.add(new ElementButton("C",0.75,70,workspace));
        }
    }
}
