package UI.BottomBar;

import Construction.Molecule;
import UI.UIButtons.NameButton;

import javax.swing.*;
import java.awt.*;

public class GreyBar extends JPanel {
    public Molecule getCurrentMol() {
        return currentMol;
    }

    public void setCurrentMol(Molecule mol){
        this.currentMol = mol;
    }

    private Molecule currentMol;

    public NameButtonPanel getNameButton() {
        return nameButton;
    }

    public void setNameButton(NameButtonPanel nameButton) {
        this.nameButton = nameButton;
    }

    private NameButtonPanel nameButton;

    public GreyBar(){

        this.setPreferredSize(new Dimension(0,24));
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.black));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        String mr = "";
        String empForm = "";
        String molForm = "";

        if (currentMol != null) {
            mr = Double.toString(getCurrentMol().getMr());
            empForm = "";
            molForm = "";
        }

        String text1 = "Mr: "+mr;
        String text2 = "Empirical Formula: "+empForm;
        String text3 = "Molecular Formula: "+molForm;
        FontMetrics fm = g2d.getFontMetrics();
        int textAscent = fm.getAscent();

        g2d.drawString(text1,10,(this.getPreferredSize().height+textAscent)/2);
        g2d.drawString(text2,this.getSize().width/4,(this.getPreferredSize().height+textAscent)/2);
        g2d.drawString(text3,this.getSize().width/2,(this.getPreferredSize().height+textAscent)/2);



    }


}
