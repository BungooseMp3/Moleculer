package UI.BottomBar;

import UI.LeftBar.EmptyPanel;

import javax.swing.*;
import java.awt.*;

public class GreyBarWrapper extends JPanel {
    public GreyBarWrapper(GreyBar greyBar){
        this.setLayout( new BorderLayout());
        this.add(greyBar,BorderLayout.SOUTH);
        this.add(new EmptyPanel(),BorderLayout.NORTH);
    }
}
