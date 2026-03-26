package UI.LeftBar;

import UI.Other.Workspace;

import javax.swing.*;
import java.awt.*;

public class ToolbarOuterWrapper extends JPanel {
    public ToolbarOuterWrapper(Workspace workspace, ToolbarSwitcher toolbar){
        this.setPreferredSize(new Dimension(220,1000));
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.weighty = 1;

        constraints.gridx = 0;
        constraints.weightx = 0.8;
        this.add(new ToolBarContainer(workspace),constraints);

        constraints.gridx = 1;
        constraints.weightx = 0.2;
        this.add(new PopoutButtonPanel(toolbar,true));
    }
}
