package UI;

import UI.UIButton.TextButton;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    public Toolbar (){
        this.setLayout(new GridLayout(8,2));
        this.setPreferredSize(new Dimension(100,800));
        for (int i = 0; i < 16; i++) {
            this.add(new TextButton("Cl"));
        }
        this.setVisible(true);
    }
}
