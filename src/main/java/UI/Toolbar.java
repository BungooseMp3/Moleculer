package UI;

import UI.UIButtons.ElementButton;
import UI.UIButtons.TextButton;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    private Workspace workspace;

    public Toolbar (Workspace workspace){
        this.setWorkspace(workspace);
        this.setLayout(new GridLayout(8,2));
        this.setPreferredSize(new Dimension(100,800));
        for (int i = 0; i < 8; i++) {
            this.add(new ElementButton("C",0.75,70));
            this.add(new ElementButton("O",0.75,70));
        }

        this.setVisible(true);
    }
}
