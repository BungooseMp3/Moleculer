package Construction;

import UI.Workspace;

import java.awt.*;
import java.util.ArrayList;

public class Molecule {

    private Workspace workspace;
    private ArrayList<Element> elementList;
    private double mr;
    private String name;
    private String empiricalForm;
    private String molecularForm;
    private int[] priorityChain;
    private FuncGroup priorityGroup;
    private ArrayList<FuncGroup> groupList;

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public ArrayList<Element> getElementList() {
        return elementList;
    }

    public void setElementList(ArrayList<Element> elementList) {
        this.elementList = elementList;
    }

    public double getMr() {
        return mr;
    }

    public void setMr(double mr) {
        this.mr = mr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpiricalForm() {
        return empiricalForm;
    }

    public void setEmpiricalForm(String empiricalForm) {
        this.empiricalForm = empiricalForm;
    }

    public String getMolecularForm() {
        return molecularForm;
    }

    public void setMolecularForm(String molecularForm) {
        this.molecularForm = molecularForm;
    }

    public int[] getPriorityChain() {
        return priorityChain;
    }

    public void setPriorityChain(int[] priorityChain) {
        this.priorityChain = priorityChain;
    }

    public FuncGroup getPriorityGroup() {
        return priorityGroup;
    }

    public void setPriorityGroup(FuncGroup priorityGroup) {
        this.priorityGroup = priorityGroup;
    }

    public ArrayList<FuncGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(ArrayList<FuncGroup> groupList) {
        this.groupList = groupList;
    }

    public Molecule(String startElementID, Workspace workspace, Point pos){

        groupList = new ArrayList<FuncGroup>();
        elementList = new ArrayList<Element>();
        Element startElement = new Element(startElementID, workspace, pos,1,this);
        startElement.setLocation(pos);//a new element is made using the specified ID
        elementList.add(startElement); // the new element is added to the molecule
        mr = startElement.getAr();

        if (!startElement.isElement("C")&&!startElement.isElement(null)){ // non carbon elements are added to a new funcGroup
            groupList.add(new FuncGroup(startElement));
        }

        this.setWorkspace(workspace);
    }

    public void addNewNode(Element selectedNode, String selectedElementID, Point pos){
        if(selectedNode.hasFreeBonds(1)){
            this.addElement(new Element(selectedElementID,getWorkspace(),pos,selectedNode.getOrientation()*(-1),this));
            Element newElement = this.getLastElement();
            newElement.makeBond(selectedNode,1);
        }
    }

    public void addElement(Element element) {
        this.getElementList().add(element);
    }

    public Element getLastElement() {
        return this.getElementList().getLast();
    }

    public void joinMolecules(Molecule molecule) {

        this.getElementList().addAll(molecule.getElementList());
        for (int i = 0; i < molecule.getElementList().size(); i++) {
            molecule.getElementList().get(i).setMolecule(this);
        }

        molecule.getWorkspace().getMolecules().remove(molecule);
        molecule = null;
    }

    public void addFuncGroup(FuncGroup newGroup) {
        this.getGroupList().add(newGroup);
    }

    public FuncGroup findGroupWith(Element element) {
        for (int i = 0; i<this.getGroupList().size(); i++) {
            if(this.getGroupList().get(i).getContainedElements().contains(element)||this.getGroupList().get(i).getAttachedCarbons().contains(element)){
                return getGroupList().get(i);
            }
        }
        return null;
    }
}
