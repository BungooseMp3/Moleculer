package Construction;

import java.util.ArrayList;

public class FuncGroup {
    private ArrayList<Element> attachedCarbons;
    private ArrayList<Element> containedElements;
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
        ArrayList<Element> attachedCarbonList = new ArrayList<Element>();
        ArrayList<Element> containedElementList = new ArrayList<Element>();
        containedElementList.add(initialElement);
    }
}
