package UI.LeftBar;

import UI.RoundedPanel;
import UI.UIButtons.BondButton;
import UI.UIButtons.ElementButton;
import UI.Workspace;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Toolbar extends RoundedPanel {
    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    private Workspace workspace;

    public Toolbar (Workspace workspace, int radius){
        this.setRadius(radius);
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(0,0,0,3));
        this.setWorkspace(workspace);
        this.setLayout(new GridLayout(8,2,0,0));
        this.setPreferredSize(new Dimension(200,800));
        this.setBackground(new Color(190,10,10));

        this.add(new BondButton(1));
        this.add(new BondButton(2));
        this.add(new BondButton(3));
        this.add(new ElementButton("C",0.75,70));
        this.add(new ElementButton("C",0.75,70));
        this.add(new ElementButton("H",0.75,70));
        this.add(new ElementButton("O",0.75,70));
        this.add(new ElementButton("N",0.75,70));

        for (int i = 0; i < 8; i++) {
            this.add(new ElementButton("C",0.75,70));
        }

        this.setVisible(true);
    }
}
