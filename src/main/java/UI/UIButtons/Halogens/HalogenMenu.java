package UI.UIButtons.Halogens;

import UI.RoundedPanel;
import UI.Workspace;

import java.awt.*;

public class HalogenMenu extends RoundedPanel {
    public HalogenButton mainButton;
    public HalogenMenu(Workspace workspace){
        this.setLayout(new CardLayout());
        mainButton = new HalogenButton(workspace,this);
        this.setPreferredSize(new Dimension(100,100));
        this.add(mainButton,"button");
        this.add(new HalogenGrid(workspace,this, mainButton), "grid");
        this.setBackground(new Color(190,10,10,255));
    }
}
