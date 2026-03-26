package UI.Other;

import Construction.Molecule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class MoleculeComponent extends JComponent {
    public Molecule getMolecule() {
        return molecule;
    }

    public void setMolecule(Molecule molecule) {
        this.molecule = molecule;
    }

    private Molecule molecule;

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    private Workspace workspace;

    public void initComponent(Workspace workspace, Rectangle bounds, Molecule molecule){
        setWorkspace(workspace);
        this.setMolecule(molecule);
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
