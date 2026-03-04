package UI.UIButtons;

import UI.Tools.Deleter;
import UI.Workspace;

import javax.swing.*;
import java.awt.*;

public class DeletionButton extends ButtonTemplate {

    public DeletionButton(Workspace workspace){
        this.setWorkspace(workspace);
        this.initButton();
    }

    @Override
    public void buttonLeftClicked() {
        getWorkspace().setCurrentTool(new Deleter(getWorkspace()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int[] x = {
                22, 32, 68, 78
        };

        int[] y = {
                32, 87, 87, 32
        };

        g2.setColor(Color.BLACK);
        g2.fillRoundRect(20,21,60,10,10,10);
        g2.fillOval(45,15,10,10);
        g2.fillPolygon(x, y, x.length);
    }
}
