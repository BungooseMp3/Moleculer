package UI.UIButtons.NumButtons;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberSelector extends JSpinner {
    NumberSelectionButton button;
    CardLayout card;


    public SelectionButtonWrapper getSwitcher() {
        return switcher;
    }

    public void setSwitcher(SelectionButtonWrapper switcher) {
        this.switcher = switcher;
    }

    private SelectionButtonWrapper switcher;

    public NumberSelector(NumberSelectionButton button,SelectionButtonWrapper switcher){
        this.switcher = switcher;
        this.card = (CardLayout) switcher.getLayout();
        this.setModel(new SpinnerNumberModel(1,1,99,1));
        this.button = button;

        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int number = (int) NumberSelector.this.getValue();
                button.setCurrentNumber(number);
                button.updateText(((Integer)number).toString());
            }
        });

        NumberEditor editor = (NumberEditor) this.getEditor();
        JFormattedTextField textfield = editor.getTextField();
        NumberFormatter formatter = (NumberFormatter) textfield.getFormatter();
        formatter.setAllowsInvalid(false);
        textfield.setFont(new Font("Arial",Font.PLAIN,30));

        textfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    int number = (int) NumberSelector.this.getValue();
                    button.setCurrentNumber(number);
                    button.updateText(((Integer)number).toString());
                    card.show(switcher,"button");
                } else if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE)&&(textfield.getText().length() ==1 )) {
                    textfield.selectAll();
                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


    }
}
