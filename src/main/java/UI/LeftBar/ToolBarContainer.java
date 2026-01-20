package UI.LeftBar;

import UI.Workspace;

import javax.swing.*;
import java.awt.*;

public class ToolBarContainer extends JPanel {

    public ToolBarContainer(Workspace workspace){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(200,1000));
        this.add(Box.createVerticalGlue());
        this.add(new Toolbar(workspace,10));
        this.add(Box.createVerticalGlue());
        this.setVisible(true);
        this.setBackground(Color.white);
    }
}
