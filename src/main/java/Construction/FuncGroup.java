package Construction;

import java.util.ArrayList;
import java.util.Set;

public class FuncGroup {
    private ArrayList<Element> attachedCarbons = new ArrayList<Element>();
    private ArrayList<Element> containedElements = new ArrayList<Element>();

    public Molecule getMolecule() {
        return molecule;
    }

    public void setMolecule(Molecule molecule) {
        this.molecule = molecule;
    }

    private Molecule molecule;
    private String groupName;

    public ArrayList<Element> getAttachedCarbons() {
        return attachedCarbons;
    }

    public void setAttachedCarbons(ArrayList<Element> attachedCarbons) {
        this.attachedCarbons = attachedCarbons;
        for(Element element : attachedCarbons){
            element.addGroup(this);
        }
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
        this.molecule = initialElement.getMolecule();
    }

    private static void extracted(Element initialElement, ArrayList<Element> containedElementList) {
        containedElementList.add(initialElement);
    }

    public void addElement(Element element){
        if(element.isElement("C")){
            addAttachedCarbon(element);
            if(element.getGroups()==null){
                element.setGroups(new ArrayList<FuncGroup>());
            }
            element.getGroups().add(this);
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

    public void checkElements(){
        Element[] list = this.getContainedElements().toArray(new Element[containedElements.size()]);
        for(Element element : list){
            if(element.isElement("O")){
                element.checkOxygens(this,element.makeCleanBondList());
            }else if(element.isElement("N")){
                element.checkNitrogens(this,element.makeCleanBondList());
            } else if(element.isElement("Cl")||element.isElement("Br")||element.isElement("F")||element.isElement("I")){
                element.checkHalogens(this);
            }
        }
    }

    public void combineWith(FuncGroup group2) {
        this.setGroupName(ElementReference.groupCombos.get(Set.of(this.getGroupName(),group2.getGroupName())));
        this.getContainedElements().addAll(group2.getContainedElements());

        for(Element element : group2.getAttachedCarbons()){
            if(!element.getGroups().contains(this)){
                element.addGroup(this);
            }
            element.removeGroup(group2);
        }
        group2.getMolecule().getGroupList().remove(group2);
        group2 = null;
    }

    public void classifyCarbonyl(){
        if(this.getAttachedCarbons().getFirst().hasFreeBonds(1)){
            this.setGroupName("aldehyde");
        } else {
            this.setGroupName("ketone");
        }
    }
}
