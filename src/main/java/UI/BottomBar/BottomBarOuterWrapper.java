package UI.BottomBar;

import javax.swing.*;
import java.awt.*;

public class BottomBarOuterWrapper extends JPanel{
    public BottomBarOuterWrapper(GreyBar greyBar) {
        this.setPreferredSize(new Dimension(0,74));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(new GreyBarWrapper(greyBar));
        this.setBackground(Color.white);
        this.add(new NameButtonPanel(greyBar));
    }
}
