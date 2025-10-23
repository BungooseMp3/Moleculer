package UI;

import Construction.Element;
import Construction.Molecule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Workspace extends JFrame {

    public ArrayList<Element> elements = new ArrayList<Element>();
    Insets insets;

    public Workspace(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);

        this.getContentPane().addMouseListener(new MouseAdapter() {
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
