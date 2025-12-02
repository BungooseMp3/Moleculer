package UI.UIButtons;

import UI.GridTemplate;
import UI.Tools.ElementPlacer;
import UI.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HalogenButton extends TextButton {
    String text = "X";

    public HalogenMenu getMenu() {
        return menu;
    }

    public void setMenu(HalogenMenu menu) {
        this.menu = menu;
    }

    private HalogenMenu menu;

    public HalogenButton(Workspace workspace){
        menu = new HalogenMenu(workspace,this);
        this.initTextButton(text,0.75,70,workspace);
    }

    @Override
    public void buttonPressed() {
        this.getWorkspace().setCurrentTool(new ElementPlacer(this.getText(), this.getWorkspace()));
        menu.show(this,0,menu.getHeight());
    }

    public class HalogenMenu extends JPopupMenu {
        public HalogenMenu(Workspace workspace,HalogenButton outerButton){
            this.add(new HalogenGrid(workspace,this, outerButton));
        }
    }

    public class HalogenGrid extends GridTemplate{
        public HalogenMenu getMenu() {
            return menu;
        }

        public void setMenu(HalogenMenu menu) {
            this.menu = menu;
        }

        private HalogenMenu menu;

        public HalogenGrid(Workspace workspace, HalogenMenu menu,HalogenButton outerButton){
            this.menu = menu;

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    HalogenGrid.this.getMenu().setVisible(false);
                }
            });

            this.initGrid(2,2,new Dimension(100,100),10,new Color(190,10,10,255),workspace);
            this.add(new HalogenGridButton("F", 0.75,20,workspace,outerButton));
            this.add(new HalogenGridButton("Cl", 0.75,20,workspace,outerButton));
            this.add(new HalogenGridButton("Br", 0.75,20,workspace,outerButton));
            this.add(new HalogenGridButton("I", 0.75,20,workspace,outerButton));
            this.setOpaque(true);
        }
    }

    public class HalogenGridButton extends TextButton{

        HalogenButton outerButton;

        public HalogenGridButton(String text,double fontScale,double maxSize,Workspace workspace,HalogenButton outerButton){
            this.initTextButton(text,fontScale,maxSize,workspace);
            this.outerButton = outerButton;
        }

        @Override
        public void buttonPressed() {
            this.getWorkspace().setCurrentTool(new ElementPlacer(this.getText(), this.getWorkspace()));
            outerButton.setText(this.getText());
        }
    }
}
