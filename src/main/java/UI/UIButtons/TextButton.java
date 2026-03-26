package UI.UIButtons;

import UI.Other.Workspace;

import java.awt.*;

abstract public class TextButton extends ButtonTemplate{

    private double fontScale;

    public double getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(double maxSize) {
        this.maxSize = maxSize;
    }

    public double getFontScale() {
        return fontScale;
    }

    public void setFontScale(double fontScale) {
        this.fontScale = fontScale;
    }

    private double maxSize;

    public void initTextButton(String text,double fontScale,double maxSize,Workspace workspace){
        this.setWorkspace(workspace);
        this.setFontScale(fontScale);
        this.setMaxSize(maxSize);
        this.setButtonSize(new Dimension(100,100));
        this.initButton();
        this.initText(text);
    }

    public void initText(String text){
        int fontSize = 80;
        this.setFont(new Font("Arial", Font.PLAIN,fontSize)); //sets an initial font with arbitrary size
        FontMetrics metrics = getFontMetrics(this.getFont());
        int textWidth = metrics.charsWidth(text.toCharArray(),0,text.length()); // determines the actual width of the text on the button
        fontSize = (int) Math.min(this.getPreferredSize().width*fontScale*((double) fontSize/(double) textWidth ), maxSize); //calculates new width based on container size
        this.setFont(new Font("Arial", Font.PLAIN, fontSize));
        this.setText(text);
    }

}
