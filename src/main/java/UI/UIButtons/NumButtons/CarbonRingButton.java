package UI.UIButtons.NumButtons;

import UI.Other.Workspace;

import java.awt.*;

public class CarbonRingButton extends NumberSelectionButton{
    public CarbonRingButton(Workspace workspace){
        this.currentNumber = 1;
        this.setWorkspace(workspace);
        this.initNumberButton("n",16,20,workspace);
    }

    @Override
    void buttonRightClicked() {

    }

    @Override
    void updateText(String text) {
        this.setText(text);
    }

    @Override
    public void buttonLeftClicked() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawPolygon(new int[]{84, 67, 31, 14, 31, 67}, new int[]{50, 81, 81, 50, 19, 19},6);
    }
}
