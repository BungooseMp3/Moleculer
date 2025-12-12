package UI.UIButtons.NumButtons;

import UI.UIButtons.ButtonTemplate;
import UI.UIButtons.TextButton;
import UI.Workspace;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class NumberSelectionButton extends TextButton {
    public NumberSelector getSelector() {
        return selector;
    }

    public void setSelector(NumberSelector selector) {
        this.selector = selector;
    }

    private NumberSelector selector;

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public CardLayout getCard() {
        return card;
    }

    public void setCard(CardLayout card) {
        this.card = card;
    }

    private CardLayout card;

    public SelectionButtonWrapper getSwitcher() {
        return switcher;
    }

    public void setSwitcher(SelectionButtonWrapper switcher) {
        this.switcher = switcher;
    }

    private SelectionButtonWrapper switcher;

    public int currentNumber;

    public void initNumberButton(String text, double fontScale, double maxSize, Workspace workspace){
        this.initTextButton(text, fontScale, maxSize, workspace);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton() == MouseEvent.BUTTON2||e.isPopupTrigger()){
                    card.show(switcher,"popout");
                }
            }
        });
    }
    abstract void buttonRightClicked();

    abstract void updateText(String text);
}
