package UI.Other;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class GridTemplate extends RoundedPanel {
    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    private Workspace workspace;

    public void initGrid(int rows, int columns, Dimension dimensions, int radius, Color color, Workspace workspace){
        this.setRadius(radius);
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(0,0,0,3));
        this.setWorkspace(workspace);
        this.setLayout(new GridLayout(rows,columns,0,0));
        this.setPreferredSize(dimensions);
        this.setBackground(color);
    }

}
