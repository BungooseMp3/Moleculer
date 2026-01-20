package UI.LeftBar;

import UI.UIButtons.PopoutButton;

import javax.swing.*;
import java.awt.*;

public class PopoutButtonPanel extends JPanel {
    public PopoutButtonPanel(ToolbarSwitcher toolbar, Boolean visible){
        Dimension s = new Dimension(20,150);
        this.setPreferredSize(s);
        this.setMaximumSize(s);
        this.setMinimumSize(s);
        this.setPreferredSize(s);
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(new PopoutButton(toolbar, visible));
        this.add(Box.createVerticalGlue());

    }
}
