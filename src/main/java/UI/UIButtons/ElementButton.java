package UI.UIButtons;

import Construction.Element;
import UI.Toolbar;
import UI.Tools.ElementPlacer;

public class ElementButton extends TextButton{

    public ElementButton(String text,double fontScale,double maxSize){
        this.initTextButton(text,fontScale,maxSize);
    }

    @Override
    public void buttonPressed() {
        Toolbar toolbar = (Toolbar) this.getParent();
        toolbar.getWorkspace().setCurrentTool(new ElementPlacer(this.getText(), toolbar.getWorkspace()));
    }
}
