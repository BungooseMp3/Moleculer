package UI;

import Construction.Element;
import Construction.Molecule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Workspace extends JPanel {

    public ArrayList<Element> elements = new ArrayList<Element>();

    public Workspace(){
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    Element newEle = new Element("C");
                    Workspace.this.elements.add(newEle);
                    newEle.setBounds(e.getX(),e.getY(),50,50);
                    Workspace.this.add(newEle);

                } else {
                    Element newEle = new Element("O");
                    Workspace.this.elements.add(newEle);
                    newEle.setBounds(e.getX(),e.getY(),50,50);
                    Workspace.this.add(newEle);
                }
                for (Element ele: elements){
                    ele.repaint();
                }
            }
        });

    }
}
