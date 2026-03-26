package UI.UIButtons;

import UI.Tools.BondPlacer;
import UI.Other.Workspace;

import java.awt.*;

public class BondButton extends ButtonTemplate {
    public int getBondType() {
        return bondType;
    }

    public void setBondType(int bondType) {
        this.bondType = bondType;
    }

    private int bondType;

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int startX;
    public int endX;

    public BondButton(int bondType, Workspace workspace){
        this.bondType = bondType;
        this.calcButtonGraphic();
        this.setWorkspace(workspace);
        this.initButton();
    }

    private void calcButtonGraphic() {
        switch (this.getBondType()){
            case 1:
                this.setStartX(this.getButtonSize().width-33);
                this.setEndX(33);
                break;
            case 2:
                this.setStartX(this.getButtonSize().width-39);
                this.setEndX(27);
                break;
            case 3:
                this.setStartX(this.getButtonSize().width-45);
                this.setEndX(21);
                break;
        }
    }

    @Override
    public void buttonLeftClicked() {
        getWorkspace().setCurrentTool(new BondPlacer(getWorkspace(),this.getBondType()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setStroke(new BasicStroke(4));

        for (int i = 0; i < this.getBondType(); i++) {
            g2.drawLine(this.getStartX()+(i*12),20,this.getEndX()+(i*12),getHeight()-20);
        }

    }
}
