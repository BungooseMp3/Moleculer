package UI.UIButtons;

import UI.Tools.Selector;
import UI.Other.Workspace;

import java.awt.*;

public class SelectorButton extends ButtonTemplate {
    public SelectorButton(Workspace workspace) {
        this.setWorkspace(workspace);
        this.initButton();
    }

    @Override
    public void buttonLeftClicked() {
        getWorkspace().setCurrentTool(new Selector(getWorkspace()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int[] x = {23, 82, 60, 72, 60, 48, 32 , 23};
        int[] y = {10, 60, 65, 85, 92, 72, 87, 10};

        g2.setColor(Color.BLACK);
        g2.fillPolygon(x, y, x.length);
    }
}
