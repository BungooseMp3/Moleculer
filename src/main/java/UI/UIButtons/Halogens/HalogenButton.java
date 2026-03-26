package UI.UIButtons.Halogens;

import UI.Tools.ElementPlacer;
import UI.UIButtons.TextButton;
import UI.Other.Workspace;

import java.awt.*;

public class HalogenButton extends TextButton {
    String text = "X";
    public CardLayout card;

    public HalogenMenu getMenu() {
        return menu;
    }

    public void setMenu(HalogenMenu menu) {
        this.menu = menu;
    }

    private HalogenMenu menu;

    public HalogenButton(Workspace workspace,HalogenMenu menu){
        this.menu = menu;
        this.initTextButton(text,0.75,70,workspace);
        this.card = (CardLayout) menu.getLayout();

    }

    @Override
    public void buttonLeftClicked() {
        this.getWorkspace().setCurrentTool(new ElementPlacer(this.getText(), this.getWorkspace()));
        card.show(menu,"grid");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.fillPolygon(new Polygon(new int[]{this.getWidth(),this.getWidth(),(int)(this.getWidth()*0.7 )},new int[]{this.getHeight(),(int)(this.getHeight()*0.7),this.getHeight()},3));

    }
}
