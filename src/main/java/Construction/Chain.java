package Construction;

import java.util.*;

public class Chain {

    private final Stack<Element> contents;
    public boolean isMainChain = true;
    public int subChainPos;
    private Map<String, ArrayList<Integer>> postGroupData;
    private ArrayList<Chain> subChainlist = new ArrayList<>();
    private String priorityGroupName;
    private Map<String, ArrayList<Integer>> chainData;
    private String name;

    public Chain(Stack<Element> contents, FuncGroup priorityGroup) {
        this.contents = contents;
        if (!Objects.isNull(priorityGroup) && priorityGroup.getAttachedCarbons().size() > 1 && !Objects.isNull(priorityGroup.getGroupName()) && !Objects.isNull(priorityGroup.getContainedElements()) && !priorityGroup.getContainedElements().isEmpty()) {
            isMainChain = false;
            for (Bond bond : contents.getLast().getBonds()) {
                for (Element element : bond.getConnectedElements()) {
                    if (!Objects.isNull(element) && element.isElement("O") && bond.getBondType() == 2) {
                        isMainChain = true;
                        break;
                    }
                }
                if (isMainChain) {
                    break;
                }
            }
        }
        if (isMainChain && !Objects.isNull(priorityGroup) && !Objects.isNull(priorityGroup.getGroupName())) {
            priorityGroupName = priorityGroup.getGroupName();
        } else {
            priorityGroupName = null;
        }

    }

    public Map<String, ArrayList<Integer>> getPostGroupData() {
        return postGroupData;
    }

    public void setPostGroupData(Map<String, ArrayList<Integer>> postGroupData) {
        this.postGroupData = postGroupData;
    }

    private int getSubChainPos() {
        return subChainPos;
    }

    public void setSubChainPos(int subChainPos) {
        this.subChainPos = subChainPos;
    }

    public ArrayList<Chain> getSubChainlist() {
        return subChainlist;
    }

    public void setSubChainlist(ArrayList<Chain> subChainlist) {
        this.subChainlist = subChainlist;
    }

    public String getPriorityGroupName() {
        return priorityGroupName;
    }

    public void setPriorityGroupName(String priorityGroupName) {
        this.priorityGroupName = priorityGroupName;
    }

    public Map<String, ArrayList<Integer>> getChainData() {
        return chainData;
    }

    public void setChainData(Map<String, ArrayList<Integer>> chainData) {
        this.chainData = chainData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stack<Element> getContents() {
        return contents;
    }

    public void nameChain() {
        compileGroups();
        correctOrder();
        constructFront(sortedGroups());
        constructend();
    }

    private void correctOrder() {
        HashMap<String, ArrayList<Integer>> allGroups = new HashMap<>();
        allGroups.putAll(postGroupData);
        allGroups.putAll(chainData);

        if (!allGroups.isEmpty()) {
            reverseChain(getNextHighestGroup(allGroups), allGroups);
        }
    }

    private String getLongestSubChain() {
        int chainlength = 0;
        for (Chain chain : getSubChainlist()) {
            if (chain.getContents().size() > chainlength) {
                chainlength = chain.getContents().size();
            }
        }
        ArrayList<Chain> tempList = new ArrayList<Chain>(subChainlist);
        for (Chain chain : tempList) {
            if (chain.getContents().size() == chainlength) {
                subChainlist.remove(chain);
            }
        }
        return ElementReference.chainLengths.get(chainlength) + "yl";
    }

    private void reverseChain(String key, Map<String, ArrayList<Integer>> allGroups) {
        ArrayList<Integer> groupPos = allGroups.get(key);
        if (!Objects.isNull(groupPos)) {
            if (contents.size() - groupPos.getFirst() + 1 == groupPos.getLast()) {
                String nextGroup = getNextHighestGroup(allGroups);
                if (!Objects.isNull(nextGroup)) {
                    reverseChain(nextGroup, allGroups);
                }
            } else {
                for (int pos : groupPos) {
                    if (this.contents.size() - pos < pos) {
                        Collections.reverse(getContents());
                        for (String currentKey : allGroups.keySet()) {
                            for (int i = 0; i < allGroups.get(currentKey).size(); i++) {
                                allGroups.get(currentKey).set(i, contents.size() - pos + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    private String getNextHighestGroup(Map<String, ArrayList<Integer>> allGroups) {
        String priorityKey = null;
        for (String currentKey : allGroups.keySet()) {
            if (ElementReference.namePriorities.containsKey(currentKey) && (Objects.isNull(priorityKey) || ElementReference.namePriorities.get(currentKey) < ElementReference.namePriorities.get(priorityKey))) {
                priorityKey = currentKey;
            }
        }
        if (!Objects.isNull(priorityKey)) {
            allGroups.remove(priorityKey);
            return priorityKey;
        } else {
            return getLongestSubChain();
        }
    }


    public void constructend() {
        StringBuilder end = new StringBuilder();
        end.append(ElementReference.chainLengths.get(this.getContents().size()));
        updateCarbonOnlyGroups(end);
        updatePriorityGroups(end);

        if (!isMainChain) {
            end.append("yl");
        } else {
            if (end.charAt(end.length() - 2) == 'n') {
                end.setCharAt(end.length() - 1, 'e');
            }
        }

        if (!Objects.isNull(name)) {
            name += end.toString();
        } else {
            name = end.toString();
        }

    }

    private void updatePriorityGroups(StringBuilder end) {
        if (!Objects.isNull(priorityGroupName) && !chainData.containsKey(priorityGroupName) && postGroupData.containsKey(ElementReference.suffixes.get(priorityGroupName))) {
            addNumbers(ElementReference.suffixes.get(priorityGroupName), end, postGroupData);
            end.append(ElementReference.suffixes.get(priorityGroupName));
        }
    }

    private void updateCarbonOnlyGroups(StringBuilder end) {
        if (!Objects.isNull(postGroupData) && postGroupData.containsKey("ene") || postGroupData.containsKey("yne")) {
            if (postGroupData.containsKey("ene")) {
                addNumbers("ene", end, this.postGroupData);
                end.append("en-");
                postGroupData.remove("ene");
            }

            if (postGroupData.containsKey("yne")) {
                if (postGroupData.containsKey("ene")) {
                    end.delete(end.length() - 2, end.length() - 1);
                }
                addNumbers("yne", end, this.postGroupData);
                end.append("yn-");
                postGroupData.remove("yne");
            }
        } else if (isMainChain) {
            end.append("an-");
        }
    }

    private void addNumbers(String key, StringBuilder namePart, Map<String, ArrayList<Integer>> data) {
        if (data.containsKey(key)) {
            if (!namePart.isEmpty() && namePart.charAt(namePart.length() - 1) != '-') {
                namePart.append("-");
            }
            for (int i = 0; i < data.get(key).size(); i++) {
                namePart.append(data.get(key).get(i));
                if (i != data.get(key).size() - 1) {
                    namePart.append(",");
                }
            }
            namePart.append("-");
            if (ElementReference.multiPrefixes.containsKey(data.get(key).size())) {
                namePart.append(ElementReference.multiPrefixes.get(data.get(key).size()));
            }
        }
    }

    private void constructFront(String[] strings) {
        if (!Objects.isNull(strings)) {
            StringBuilder frontName = new StringBuilder();
            for (String string : strings) {
                addNumbers(string, frontName, this.chainData);
                frontName.append(string);

            }
            this.name = frontName.toString();
        }

    }

    private String[] sortedGroups() {
        if (!Objects.isNull(chainData)) {
            String[] groupnames = this.getChainData().keySet().toArray(new String[0]);
            Arrays.sort(groupnames);
            return groupnames;
        }
        return null;
    }

    private void compileGroups() {
        chainData = new HashMap<>();
        postGroupData = new HashMap<>();
        for (Element element : this.contents) {
            if (!Objects.isNull(element.getGroups())) {
                for (FuncGroup group : element.getGroups()) {
                    if (!Objects.isNull(group.getGroupName())) {
                        if (ElementReference.prefixes.containsKey(group.getGroupName()) && !Objects.equals(priorityGroupName, group.getGroupName())) {
                            updateChainData(ElementReference.prefixes.get(group.getGroupName()), getContents().indexOf(element) + 1, getChainData());
                        } else if (ElementReference.suffixes.containsKey(group.getGroupName())) {
                            updateChainData(ElementReference.suffixes.get(group.getGroupName()), getContents().indexOf(element) + 1, getPostGroupData());
                        }
                    }
                }
            }
            ArrayList<Element> checkedNodes = new ArrayList<>();
            while (!Objects.isNull(element.getFirstAdjCarbon(checkedNodes, this.getContents()))) {
                Element carbon = element.getFirstAdjCarbon(checkedNodes, this.getContents());
                this.compileSubChain(carbon, element);
                checkedNodes.add(carbon);
            }
        }

        for (Chain subChain : this.getSubChainlist()) {
            updateChainData(ElementReference.chainLengths.get(subChain.getContents().size()) + "yl", subChain.getSubChainPos(), getChainData());
        }
    }

    private void compileSubChain(Element startNode, Element chainCarbon) {
        ArrayList<Element> checkedNodes = new ArrayList<>();
        checkedNodes.add(chainCarbon);
        Chain subChain = new Chain((Molecule.depthFirstSearchCarbonOnly(startNode, checkedNodes, new Stack<>(), new Stack<>())), null);
        subChain.setSubChainPos(this.getContents().indexOf(chainCarbon) + 1);
        this.getSubChainlist().add(subChain);
    }

    private void updateChainData(String groupName, int pos, Map<String, ArrayList<Integer>> data) {
        if (!data.containsKey(groupName)) {
            data.put(groupName, new ArrayList<>());
        }

        if (data.get(groupName).isEmpty() || !((groupName.equals("ene") || groupName.equals("yne")) && data.get(groupName).getLast() == pos - 1)) {
            data.get(groupName).add(pos);
        }
    }
}
