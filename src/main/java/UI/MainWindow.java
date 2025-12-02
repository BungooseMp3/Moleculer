package UI;

import UI.LeftBar.LeftPanel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        Workspace workspace = new Workspace();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new LeftPanel(workspace),BorderLayout.WEST);
        this.getContentPane().add(new BottomBar(),BorderLayout.SOUTH);
        this.getContentPane().add(workspace);
        this.pack();
    }
}
