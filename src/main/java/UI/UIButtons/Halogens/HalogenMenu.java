package UI.UIButtons.Halogens;

import UI.RoundedPanel;
import UI.Workspace;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class HalogenMenu extends RoundedPanel {
    public HalogenButton mainButton;
    CardLayout card;
    public HalogenMenu(Workspace workspace){
        this.setLayout(new CardLayout());
        mainButton = new HalogenButton(workspace,this);
        this.setPreferredSize(new Dimension(100,100));
        this.add(mainButton,"button");
        this.add(new HalogenGrid(workspace,this, mainButton), "grid");
        this.setBackground(new Color(190,10,10,255));
        CardLayout card = (CardLayout) this.getLayout();
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!HalogenMenu.this.getBounds().contains(e.getPoint())) {
                    card.show(HalogenMenu.this,"button");
                }
            }
        });
    }
}
