import java.util.ArrayList;
public class DecisionTree{
    public ArrayList<String> fieldNames;
    Attributes fN;
    ArrayList<Node> nodes=new ArrayList<>();
    public Node decisionTreeLearning(String path,int classIndex){
        DataSet data=new DataSet(path, classIndex);
        fieldNames=data.getAttributeList();
        fN=new Attributes(fieldNames);
        String mostImportant=Util.getImportance(fN,data);
        Node node=new Node(mostImportant,1);
        setBranches(data,node,fN,mostImportant);







        return node;
    }
    /**
     * sets all branches for DecisionTree starting at given node
     * @param data dataset manipulated to match current standard
     * @param node parent node of which branches are being created
     * @param fieldNames list of all fieldNames
     * @param mI most important fieldName (question) to be checked
     */
    private void setBranches(DataSet data, Node node, Attributes fieldNames, String mI){
        ArrayList<String> pR=Util.getPossibleReturn(data, node.getName());
        if(fieldNames.size()<2){
            for(String p:pR){
                node.addBranch(new Node(Util.getPluralityValue(data),1),p);
            }
        }
        for(int i=0;i<pR.size();i++){ //runs for each possible answer to the given question
            DataSet dT=new DataSet(data);
            for(int n=0;n<dT.size();n++){ //removes all instances where the answer doesn't match up, surveys only matching data
                if(!dT.getDataAtIndex(n).getAttributeAt(mI).equals(pR.get(i))){b ;n--;};
            }
            Node hold=new Node(Util.getImportance(fieldNames, data, fieldNames.indexOf(node.getName())),1); //node to be added as a branch
            node.addBranch(hold, pR.get(i));
            Attributes a=fieldNames.removeAndCopy(mI); //remove last considered fieldName
            dT=dT.removeAtributeByName(mI); //remove last considered question
            String s=Util.getImportance(a, dT); //get next most important question to be surveyed (from modified set, not considering the last question)
            setBranches(dT,hold,a,s);
        }
    }
}