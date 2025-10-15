package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShapeButton extends JComponent {
    private final Shape shape;
    private boolean clicked = false;

    public ShapeButton(Shape shape) {
        this.shape = shape;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (shape.contains(e.getPoint())) {
                    clicked = !clicked; // toggle color each click
                    System.out.println("Shape clicked!");
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(clicked ? Color.RED : Color.GRAY);
        g2.fill(shape);
        g2.setColor(Color.BLACK);
        g2.draw(shape);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        Rectangle bounds = shape.getBounds();
        return new Dimension(bounds.width + bounds.x, bounds.height + bounds.y);
    }
}