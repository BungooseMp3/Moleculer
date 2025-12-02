package UI.LeftBar;

import UI.Workspace;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    public LeftPanel(Workspace workspace){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(220,1000));
        this.add(new ToolbarSwitcher(workspace));
        this.setVisible(true);
    }
}
