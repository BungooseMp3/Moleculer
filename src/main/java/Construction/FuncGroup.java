package Construction;

import java.util.ArrayList;

public class FuncGroup {
    private ArrayList<Element> attachedCarbons = new ArrayList<Element>();
    private ArrayList<Element> containedElements = new ArrayList<Element>();
    private String groupName;

    public ArrayList<Element> getAttachedCarbons() {
        return attachedCarbons;
    }

    public void setAttachedCarbons(ArrayList<Element> attachedCarbons) {
        this.attachedCarbons = attachedCarbons;
    }

    public ArrayList<Element> getContainedElements() {
        return containedElements;
    }

    public void setContainedElements(ArrayList<Element> containedElements) {
        this.containedElements = containedElements;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public FuncGroup(Element initialElement){
        addElement(initialElement);
    }

    private static void extracted(Element initialElement, ArrayList<Element> containedElementList) {
        containedElementList.add(initialElement);
    }

    public void addElement(Element element){
        if(element.isElement("C")){
            addAttachedCarbon(element);
        } else {
            addContainedElement(element);
        }
        element.repaint();
    }

    public void addAttachedCarbon(Element element) {
        attachedCarbons.add(element);
    }

    public void addContainedElement(Element element) {
        containedElements.add(element);
    }
}
