package UI.LeftBar;

import UI.Workspace;

import javax.swing.*;
import java.awt.*;

public class ToolbarSwitcher extends JPanel {
    public ToolbarSwitcher(Workspace workspace){
        this.setPreferredSize(new Dimension(220,1000));
        this.setLayout(new CardLayout());
        this.add(new ToolbarOuterWrapper(workspace,this),"visible");
        this.add(new ButtonWrapper(this),"hidden");
        this.repaint();
    }
}
