package UI.UIButtons;

import UI.BottomBar.GreyBar;

import java.awt.*;

public class NameButton extends TextButton{
    String state;
    GreyBar greyBar;

    public NameButton(GreyBar greyBar){
        this.greyBar = greyBar;
        this.setPreferredSize(new Dimension(300,70));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.state = "Select a molecule";
        this.initButton();

    }

    @Override
    public void buttonLeftClicked() {
        if(state.equals("Output Name")){
            greyBar.getCurrentMol().nameMolecule();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.lightGray);
        g2.fillRect(0,50,this.getWidth(),this.getHeight());

        g2.setColor(Color.black);
        g2.drawLine(0,50,this.getWidth(),50);

        g2.setColor(new Color(190,10,10));
        g2.fillRoundRect(1, 1, getWidth()-2, getHeight()-1, 70, 70);
        g2.setColor(Color.black);
        g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-1, 70, 70);

        g2.setColor(Color.white);
        g2.setFont(new Font("Arial",Font.PLAIN,30));

        if(greyBar.getCurrentMol()==null){
            this.state = "Select a molecule";
        } else {
            this.state = "Output Name";
        }

        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(state,(getWidth()-fm.stringWidth(state))/2+1,(getHeight()+fm.getAscent())/2-1);

    }
}
