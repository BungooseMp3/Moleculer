package UI.UIButtons.Halogens;

import UI.GridTemplate;
import UI.RoundedPanel;
import UI.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class HalogenGrid extends GridTemplate {
    public HalogenMenu getMenu() {
        return menu;
    }

    public void setMenu(HalogenMenu menu) {
        this.menu = menu;
    }

    private HalogenMenu menu;
    CardLayout card;

    public HalogenGrid(Workspace workspace, HalogenMenu menu, HalogenButton outerButton){
        this.menu = menu;
        card = (CardLayout) menu.getLayout();

        this.initGrid(2,2,new Dimension(100,100),10,new Color(190,10,10,255),workspace);
        this.add(new HalogenGridButton("F", 0.75,20,workspace,outerButton,menu));
        this.add(new HalogenGridButton("Cl", 0.75,20,workspace,outerButton,menu));
        this.add(new HalogenGridButton("Br", 0.75,20,workspace,outerButton,menu));
        this.add(new HalogenGridButton("I", 0.75,20,workspace,outerButton,menu));
    }
}