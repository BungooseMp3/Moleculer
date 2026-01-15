package UI;

import Construction.Element;
import Construction.Molecule;
import UI.Tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Workspace extends JPanel {

    public ArrayList<Molecule> getMolecules() {
        return molecules;
    }

    public void setMolecules(ArrayList<Molecule> molecules) {
        this.molecules = molecules;
    }

    private ArrayList<Molecule> molecules = new ArrayList<Molecule>();

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    private Tool currentTool;

    public void addNewMolecule(String symbol, int x, int y){
        this.getMolecules().add(new Molecule(symbol,this, new Point(x-25,y-25)));//adds a new molecule with start element indicated by the symbol
    }

    public Workspace(){
        this.setLayout(null);
        this.setBackground(new Color(255,255,255));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Workspace.this.getCurrentTool().onEmptyClick(e);
                repaint();
            }
        });

    }

}
