package Construction;

import UI.Other.MoleculeName;
import UI.Other.Workspace;

import java.awt.*;
import java.util.*;

public class Molecule {

    private Workspace workspace;
    private ArrayList<Element> elementList;
    private double mr;
    private String name = "";
    private String empiricalForm;
    private String molecularForm;
    private ArrayList<Chain> priorityChainList;
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
        startElement.setLocation(pos);
        elementList.add(startElement);
        mr = startElement.getAr()+ElementReference.electrons.get(startElement.getSymbol());

        if (!startElement.isElement("C")&&!startElement.isElement(null)){
            groupList.add(new FuncGroup(startElement));
        }

        this.setWorkspace(workspace);
    }

    public void addNewNode(Element selectedNode, String selectedElementID, Point pos,int bondtype){
        if(selectedNode.hasFreeBonds(bondtype)){
            this.addElement(new Element(selectedElementID,getWorkspace(),pos,selectedNode.getOrientation()*(-1),this));
            Element newElement = this.getLastElement();
            newElement.makeBond(selectedNode,bondtype);
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
        this.getGroupList().addAll(molecule.getGroupList());
        this.setMr(this.getMr()+molecule.getMr()-2);
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

    public void updateMass(String symbol,int bondtype){
        this.setMr(this.getMr()+ ElementReference.atomicMass.get(symbol)+ElementReference.electrons.get(symbol)-2*bondtype);
    }

    public void nameMolecule() {
        this.setName("");
        this.consolidateGroups();
        this.setPriorityGroup(this.findPriorityGroup());
        this.findPriorityChains();

        for(Chain stack : this.priorityChainList){
            stack.nameChain();

            if(!stack.isMainChain){
               this.setName(stack.getName()+" "+this.getName());
            } else {
                this.setName(this.getName()+" "+stack.getName());
            }

        }

        if(!this.getName().isEmpty()){
            workspace.add(new MoleculeName(this));
            workspace.repaint();
        }

    }

    private FuncGroup findPriorityGroup() {
        if(this.getGroupList().isEmpty()){
            return null;
        } else{
            FuncGroup currentGroup = this.getGroupList().getFirst();
            for (FuncGroup group : this.getGroupList()) {
                if(!Objects.isNull(group.getGroupName())){
                    if(Objects.isNull(currentGroup.getGroupName())){
                        currentGroup = group;
                    } else if(ElementReference.groupPriorities.get(group.getGroupName())<ElementReference.groupPriorities.get(currentGroup.getGroupName())){
                        currentGroup = group;
                    }

                }

            }
            return currentGroup;
        }

    }

    public void consolidateGroups(){

        for (Element element : elementList) {
            element.checkAlkenes();
        }

        FuncGroup[] list = this.getGroupList().toArray(new FuncGroup[groupList.size()]);
        for(FuncGroup group : list){
            group.checkElements();
        }

        for(Element element : elementList){
            if(element.getGroups()!=null){
                element.updateGroups();
            }
        }

        for (FuncGroup group : groupList) {
            if(Objects.equals(group.getGroupName(), "carbonyl")){
                group.classifyCarbonyl();
            }
        }

        this.cleanGroups();
    }

    public void findPriorityChains(){
        ArrayList<Chain> chainlist = new ArrayList<>();
        if(Objects.isNull(this.getPriorityGroup())){
            for(Element element: getElementList()){
                if(element.isElement("C")){
                    Stack<Element> chain = depthFirstSearchCarbonOnly(element,new ArrayList<>(),new Stack<>(),new Stack<>());
                    chainlist.add(new Chain(depthFirstSearchCarbonOnly(chain.getLast(),new ArrayList<>(),new Stack<>(),new Stack<>()),this.priorityGroup));
                    break;
                }
            }
        } else if (this.getPriorityGroup().getGroupName().equals("alkene")||this.getPriorityGroup().getGroupName().equals("alkyne")){
            Stack<Element> chain = depthFirstSearch(getPriorityGroup().getAttachedCarbons().getFirst(),new ArrayList<>(),new Stack<>(),new Stack<>(),50,50);
            chainlist.add(new Chain(depthFirstSearch(chain.getLast(),new ArrayList<>(),new Stack<>(),new Stack<>(),50,50),this.priorityGroup));

        } else {
            for (Element mainCarbon : getPriorityGroup().getAttachedCarbons()) {
                Stack<Element> chain = depthFirstSearch(mainCarbon, new ArrayList<>(), new Stack<>(), new Stack<>(), 50, 50);
                chainlist.add(new Chain(depthFirstSearch(chain.getLast(), new ArrayList<>(), new Stack<>(), new Stack<>(), 50, 50),this.priorityGroup));
            }
        }
        for(Chain chain : chainlist){
            Collections.reverse(chain.getContents());
        }
        this.priorityChainList = chainlist;
    }

    public static Stack<Element> depthFirstSearch(Element startNode, ArrayList<Element> checkedNodes, Stack<Element> currentChain ,Stack<Element> priorityChain,int priorityChainWeight, int currentChainWeight){
        currentChain.push(startNode);
        while(checkedNodes.isEmpty()||!currentChain.isEmpty()){
            Element node = currentChain.getLast();
            if(!Objects.isNull(node.getGroups())&&currentChain.size()>1) {
                for (FuncGroup group : node.getGroups()) {
                    if ((group.getGroupName() != null) && (ElementReference.groupPriorities.get(group.getGroupName()) < currentChainWeight)) {
                        currentChainWeight = ElementReference.groupPriorities.get(group.getGroupName());
                    }
                    if (currentChainWeight == priorityChainWeight) {
                        if(currentChain.size()>priorityChain.size()){
                            priorityChain.clear();
                            priorityChain.addAll(currentChain);
                        }
                    } else if(currentChainWeight < priorityChainWeight){
                        priorityChain.clear();
                        priorityChain.addAll(currentChain);
                        priorityChainWeight = currentChainWeight;
                    }
                }
            }

            Element nextnode = node.getFirstAdjCarbon(checkedNodes,currentChain);

            if(!Objects.isNull(nextnode)){
                currentChain.push(nextnode);
            } else {
                if(priorityChain.isEmpty()||currentChain.contains(priorityChain.getLast())){
                    priorityChain.clear();
                    priorityChain.addAll(currentChain);
                }
                checkedNodes.add(currentChain.pop());
            }

        }
        return priorityChain;
    }

    public static Stack<Element> depthFirstSearchCarbonOnly(Element startCarbon,ArrayList<Element> checkedNodes, Stack<Element> currentChain ,Stack<Element> priorityChain){
        currentChain.push(startCarbon);
        while(checkedNodes.isEmpty()||!currentChain.isEmpty()){
            Element node = currentChain.getLast();
            Element nextNode = node.getFirstAdjCarbon(checkedNodes,currentChain);
            if(!Objects.isNull(nextNode)){
                currentChain.push(nextNode);
            } else{
                if(currentChain.size()>priorityChain.size()){
                    priorityChain.clear();
                    priorityChain.addAll(currentChain);
                }
                checkedNodes.add(currentChain.pop());
            }
        }
        return priorityChain;
    }

    public void cleanGroups(){
        ArrayList<Element> list = new ArrayList<>(this.getElementList());
        for(Element element : list){
            if(element.getGroups()!=null){
                ArrayList<FuncGroup> dupedGroups =  new ArrayList<>();
                ArrayList<FuncGroup> list2 = new ArrayList<>(element.getGroups());
                for (FuncGroup group : list2){
                    if(group.getGroupName() == null && group.getContainedElements().isEmpty()){
                        element.removeGroup(group);
                        group = null;
                    }
                    if(!dupedGroups.contains(group)){
                        dupedGroups.add(group);
                    } else {
                        element.removeGroup(group);
                        group = null;
                    }
                }
            }
        }
    }
}
