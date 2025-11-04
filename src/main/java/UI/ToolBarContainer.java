package UI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ToolBarContainer extends JPanel {

    public ToolBarContainer(Rectangle outerDimensions){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(200,1000));
        this.add(Box.createVerticalGlue());
        this.add(new Toolbar());
        this.add(Box.createVerticalGlue());
        this.setVisible(true);
    }
}
