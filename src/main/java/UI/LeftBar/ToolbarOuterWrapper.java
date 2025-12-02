package UI.LeftBar;

import UI.Workspace;

import javax.swing.*;
import java.awt.*;

public class ToolbarOuterWrapper extends JPanel {
    public ToolbarOuterWrapper(Workspace workspace, ToolbarSwitcher toolbar){
        this.setPreferredSize(new Dimension(220,1000));
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH; //Lets components fill the whole panel
        constraints.gridy = 0;// allows vertical expansion
        constraints.weighty = 1;

        constraints.gridx = 0;// sets the components position in the grid
        constraints.weightx = 0.8;// assigns half the width to the component
        this.add(new ToolBarContainer(workspace),constraints);

        constraints.gridx = 1;// sets the components position in the grid
        constraints.weightx = 0.2;
        this.add(new PopoutButtonPanel(toolbar,true));
    }
}
