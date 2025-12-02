package UI.UIButtons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class ButtonTemplate extends JButton {

    private Color buttonColor;
    private Dimension buttonSize = new Dimension(100,100);
    private Border buttonBorder;

    public Border getButtonBorder() {
        return buttonBorder;
    }

    public void setButtonBorder(Border buttonBorder) {
        this.buttonBorder = buttonBorder;
    }

    public Dimension getButtonSize() {
        return buttonSize;
    }

    public void setButtonSize(Dimension buttonSize) {
        this.buttonSize = buttonSize;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    public void initButton(){
        this.setPreferredSize(this.getButtonSize());
        this.setButtonColor(this.getButtonColor());
        this.setButtonBorder(this.getButtonBorder());
        this.setVisible(true);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonTemplate.this.buttonPressed();
            }
        });
    }

    abstract public void buttonPressed();

}
