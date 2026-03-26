package UI.UIButtons.Halogens;

import UI.Tools.ElementPlacer;
import UI.UIButtons.TextButton;
import UI.Other.Workspace;

import java.awt.*;

public class HalogenGridButton extends TextButton {

    HalogenButton outerButton;
    HalogenMenu menu;
    CardLayout card;

    public HalogenGridButton(String text, double fontScale, double maxSize, Workspace workspace, HalogenButton outerButton, HalogenMenu menu){
        this.menu = menu;
        this.initTextButton(text,fontScale,maxSize,workspace);
        this.outerButton = outerButton;
        card = (CardLayout) menu.getLayout();

    }

    @Override
    public void buttonLeftClicked() {
        this.getWorkspace().setCurrentTool(new ElementPlacer(this.getText(), this.getWorkspace()));
        outerButton.setText(this.getText());
        card.show(menu,"button");
        this.repaint();
    }
}
