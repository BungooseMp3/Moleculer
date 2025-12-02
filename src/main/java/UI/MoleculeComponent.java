package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class MoleculeComponent extends JComponent {

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    private Workspace workspace;

    public void initComponent(Workspace workspace, Rectangle bounds){
        setWorkspace(workspace);
        this.setBounds(bounds);
        workspace.add(this);
        this.setVisible(true);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MoleculeComponent.this.mouseClick(e);
            }
        });
    }

    public abstract void mouseClick(MouseEvent e);

}
