package UI.Tools;

import Construction.Element;
import UI.Workspace;

import java.awt.event.MouseEvent;

public abstract class Tool {

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    private Workspace workspace;

    abstract public void onElementClick(MouseEvent e, Element element);
    abstract public void onEmptyClick(MouseEvent e);
    abstract public void onBondClick(MouseEvent e);
}
