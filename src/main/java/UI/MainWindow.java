package UI;

import UI.BottomBar.BottomBarOuterWrapper;
import UI.BottomBar.GreyBar;
import UI.LeftBar.LeftPanel;
import UI.UIButtons.NameButton;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        Workspace workspace = new Workspace(new GreyBar());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(new LeftPanel(workspace),BorderLayout.WEST);
        this.getContentPane().add(new BottomBarOuterWrapper(workspace.getBottomBar()),BorderLayout.SOUTH);
        this.getContentPane().add(workspace);

        /*GraphicsDevice gd = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();

        gd.setFullScreenWindow(this); - Virus */

        this.setVisible(true);
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);


    }
}
