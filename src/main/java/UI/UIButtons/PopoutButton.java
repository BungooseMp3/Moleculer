package UI.UIButtons;

import UI.LeftBar.ToolbarSwitcher;

import java.awt.*;

public class PopoutButton extends ButtonTemplate{
    ToolbarSwitcher sidebar;
    Boolean visible;
    CardLayout card;

    public PopoutButton(ToolbarSwitcher sidebar, Boolean visible){
        this.sidebar = sidebar;
        this.visible = visible;
        this.card = (CardLayout) sidebar.getLayout();
        Dimension s = new Dimension(20,80);
        this.setMaximumSize(s);
        this.setButtonSize(s);
        initButton();
    }

    @Override
    public void buttonLeftClicked() {
        if(visible){
            card.show(sidebar,"hidden");
        } else{
            card.show(sidebar,"visible");
        }
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(190,10,10));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.fillRect(0,0,getWidth()/2, getHeight());
        Polygon tri = new Polygon(new int[] {12,12,1},new int[]{30,50,40},3);
        g2.setColor(Color.white);
        g2.drawPolygon(tri);
        g.fillPolygon(tri);

    }
}
