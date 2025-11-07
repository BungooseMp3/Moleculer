package UI;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        Workspace workspace = new Workspace();
        this.setSize(500,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new ToolBarContainer(this.getBounds(),workspace),BorderLayout.WEST);
        this.getContentPane().add(new BottomBar(),BorderLayout.SOUTH);
        this.getContentPane().add(workspace);
    }
}
