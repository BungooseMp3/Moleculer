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
        menu.show(this,0,0);
    }

    public class HalogenMenu extends JPopupMenu {
        public HalogenMenu(Workspace workspace,HalogenButton outerButton){
            this.add(new HalogenGrid(workspace,this, outerButton));
            this.setBackground(new Color(190,10,10,255));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.fillPolygon(new Polygon(new int[]{this.getWidth(),this.getWidth(),(int)(this.getWidth()*0.7 )},new int[]{this.getHeight(),(int)(this.getHeight()*0.7),this.getHeight()},3));

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
            this.add(new HalogenGridButton("F", 0.75,20,workspace,outerButton,menu));
            this.add(new HalogenGridButton("Cl", 0.75,20,workspace,outerButton,menu));
            this.add(new HalogenGridButton("Br", 0.75,20,workspace,outerButton,menu));
            this.add(new HalogenGridButton("I", 0.75,20,workspace,outerButton,menu));
            this.setOpaque(true);
        }
    }

    public class HalogenGridButton extends TextButton{

        HalogenButton outerButton;
        HalogenMenu menu;

        public HalogenGridButton(String text,double fontScale,double maxSize,Workspace workspace,HalogenButton outerButton, HalogenMenu menu){
            this.menu = menu;
            this.initTextButton(text,fontScale,maxSize,workspace);
            this.outerButton = outerButton;
        }

        @Override
        public void buttonPressed() {
            this.getWorkspace().setCurrentTool(new ElementPlacer(this.getText(), this.getWorkspace()));
            outerButton.setText(this.getText());
            menu.setVisible(false);
            this.repaint();
        }
    }
}
