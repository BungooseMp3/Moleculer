package UI.Other;

import Construction.Molecule;
import UI.BottomBar.GreyBar;
import UI.Tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Workspace extends JPanel {

    public MainWindow window;

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

    public GreyBar getBottomBar() {
        return greyBar;
    }

    public void setBottomBar(GreyBar greyBar) {
        this.greyBar = greyBar;
    }

    public GreyBar getGreyBar() {
        return greyBar;
    }

    public void setGreyBar(GreyBar greyBar) {
        this.greyBar = greyBar;
    }

    private GreyBar greyBar;

    public void addNewMolecule(String symbol, int x, int y){
        this.getMolecules().add(new Molecule(symbol,this, new Point(x-30,y)));//adds a new molecule with start element indicated by the symbol
    }

    public Workspace(GreyBar greyBar,MainWindow mainWindow) {
        this.window = mainWindow;
        this.setLayout(null);
        this.setBackground(new Color(255,255,255));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Workspace.this.getCurrentTool().onEmptyClick(e);
                repaint();
            }
        });

        this.greyBar = greyBar;
    }

}
