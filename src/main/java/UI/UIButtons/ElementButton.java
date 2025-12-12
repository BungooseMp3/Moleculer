package UI.UIButtons;

import UI.LeftBar.Toolbar;
import UI.Tools.ElementPlacer;
import UI.Workspace;
import java.awt.*;

public class ElementButton extends TextButton{

    public ElementButton(String text,double fontScale,double maxSize,Workspace workspace){
        this.setWorkspace(workspace);
        this.initTextButton(text,fontScale,maxSize,workspace);
        this.setMargin(new Insets(0, 0, 0, 0));
    }

    @Override
    public void buttonLeftClicked() {
        this.getWorkspace().setCurrentTool(new ElementPlacer(this.getText(), this.getWorkspace()));
    }


}
