package UI.UIButton;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextButton extends ButtonTemplate{

    public TextButton(String text){
        this.initText(text);
        this.initButton();
    }

    public void initText(String text){
        int fontSize = 80;
        this.setFont(new Font("Arial", Font.PLAIN,fontSize)); //sets an initial font
        FontMetrics metrics = getFontMetrics(this.getFont());
        int textWidth = metrics.charsWidth(text.toCharArray(),0,text.length()); // determines the actual width of the text on the button
        fontSize = this.getWidth()*(fontSize/textWidth); //calculates new width based on container size
        this.setFont(new Font("Arial", Font.PLAIN, fontSize));
        this.setText(text);
    }

}
