package UI.UIButtons.NumButtons;

import UI.RoundedPanel;
import UI.Workspace;

import javax.swing.*;
import java.awt.*;

public class SelectionButtonWrapper extends RoundedPanel {
    public SelectionButtonWrapper(NumberSelectionButton mainbutton){
        this.setRadius(10);
        this.setLayout(new CardLayout());
        NumberSelector selector = new NumberSelector(mainbutton, this);
        mainbutton.setSelector(selector);
        mainbutton.setSwitcher(this);
        mainbutton.setCard((CardLayout) mainbutton.getSwitcher().getLayout());
        this.add(mainbutton,"button");
        this.add(selector,"popout");
        this.setBackground(new Color(190,10,10));


    }
}
