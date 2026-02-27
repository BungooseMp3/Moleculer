package Construction;

import java.util.*;

public class Chain {

    private int getSubChainPos() {
        return subChainPos;
    }

    public void setSubChainPos(int subChainPos) {
        this.subChainPos = subChainPos;
    }

    public int subChainPos;

    public ArrayList<Chain> getSubChainlist() {
        return subChainlist;
    }

    public void setSubChainlist(ArrayList<Chain> subChainlist) {
        this.subChainlist = subChainlist;
    }

    private ArrayList<Chain>subChainlist =  new ArrayList<>();

    public FuncGroup getPriorityGroup() {
        return priorityGroup;
    }

    public void setPriorityGroup(FuncGroup priorityGroup) {
        this.priorityGroup = priorityGroup;
    }

    private FuncGroup priorityGroup;
    private Map<String, ArrayList<Integer>> chainData;

    public Chain(FuncGroup priorityGroup) {
        this.priorityGroup = priorityGroup;
    }
    private String name;

    public Stack<Element> getContents() {
        return contents;
    }

    public void setContents(Stack<Element> contents, FuncGroup priorityGroup) {
        this.contents = contents;
        this.priorityGroup = priorityGroup;
    }

    private Stack<Element> contents;

    public Chain(Stack<Element> contents) {
        this.contents = contents;
    }

    public void nameChain(){
        compileGroups();
    }

    private void compileGroups() {
        chainData = new HashMap<>();
        for(Element element : this.contents){
            if (!Objects.isNull(element.getGroups())) {
                for(FuncGroup group : element.getGroups()) {
                    if(group!=this.getPriorityGroup()){
                        if(!Objects.isNull(group.getGroupName())) {
                            if(ElementReference.prefixes.containsKey(group.getGroupName())){
                                updateChainData(ElementReference.prefixes.get(group.getGroupName()), getContents().indexOf(element)+1);
                            } else{
                                updateChainData(ElementReference.suffixes.get(group.getGroupName()), getContents().indexOf(element)+1);
                            }
                        }
                    }
                }
            }

            ArrayList<Element> checkedNodes = new ArrayList<>();
            while(!Objects.isNull(element.getFirstAdjCarbon(checkedNodes,this.getContents()))){
                Element carbon = element.getFirstAdjCarbon(checkedNodes,this.getContents());
                this.compileSubChain(carbon,element);
                checkedNodes.add(carbon);
            }

        }

        for(Chain subChain : this.getSubChainlist()){
            updateChainData(ElementReference.chainLengths.get(subChain.getContents().size())+"yl",subChain.getSubChainPos());
        }
    }

    private void compileSubChain(Element startNode, Element chainCarbon) {
        ArrayList<Element> checkedNodes = new ArrayList<>();
        checkedNodes.add(chainCarbon);
        Chain subChain = new Chain((Molecule.depthFirstSearchCarbonOnly(startNode,checkedNodes,new Stack<>(),new Stack<>())));
        subChain.setSubChainPos(this.getContents().indexOf(chainCarbon)+1);
        this.getSubChainlist().add(subChain);
    }

    private void updateChainData(String groupName,int pos){
        if(!chainData.containsKey(groupName)){
            chainData.put(groupName, new ArrayList<>());
        }
        chainData.get(groupName).add(pos);
    }
}
