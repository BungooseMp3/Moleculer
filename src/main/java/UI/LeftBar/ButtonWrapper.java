package UI.LeftBar;

import UI.Workspace;

import javax.swing.*;
import java.awt.*;

public class ButtonWrapper extends JPanel {
    public ButtonWrapper(ToolbarSwitcher toolbarSwitcher){
        this.setLayout(new BorderLayout());
        this.add(new PopoutButtonPanel(toolbarSwitcher,false),BorderLayout.WEST);
        this.add(new EmptyPanel(),BorderLayout.CENTER);
    }
}
