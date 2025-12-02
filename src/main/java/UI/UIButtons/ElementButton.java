package UI.UIButtons;

import UI.LeftBar.Toolbar;
import UI.Tools.ElementPlacer;

import javax.swing.*;
import java.awt.*;

public class ElementButton extends TextButton{

    public ElementButton(String text,double fontScale,double maxSize){
        this.initTextButton(text,fontScale,maxSize);
        this.setMargin(new Insets(0, 0, 0, 0));
    }

    @Override
    public void buttonPressed() {
        Toolbar toolbar = (Toolbar) this.getParent();
        toolbar.getWorkspace().setCurrentTool(new ElementPlacer(this.getText(), toolbar.getWorkspace()));
    }


}
