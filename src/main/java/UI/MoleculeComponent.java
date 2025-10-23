package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

public abstract class MoleculeComponent extends JComponent {

    public void initComponent(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MoleculeComponent.this.mouseClick(e);
            }
        });
    }

    public abstract void mouseClick(MouseEvent e);


}
