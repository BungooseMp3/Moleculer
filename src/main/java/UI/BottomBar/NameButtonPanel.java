package UI.BottomBar;

import UI.UIButtons.NameButton;

import javax.swing.*;
import java.awt.*;

public class NameButtonPanel extends JPanel {
    public NameButtonPanel(GreyBar greyBar) {
        greyBar.setNameButton(this);
        this.setPreferredSize(new Dimension(120,74));
        this.setLayout(new BorderLayout());
        this.add(new NameButton(greyBar),BorderLayout.CENTER);
        this.setBackground(Color.white);

    }
}
