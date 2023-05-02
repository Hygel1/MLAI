import java.util.ArrayList;
public class DecisionTree{
    public ArrayList<String> fieldNames;
    Attributes fN;
    int classIndex;
    Node root;
    /**
     * Makes a new Tree with data to start learning from
     * @param path path of file to use for data collection
     * @param classIndex index of classification in csv file
     */
    public DecisionTree(String path, int classIndex){
        root=decisionTreeLearning(path, classIndex);
        this.classIndex=classIndex;
    }
    /**
     * builds a tree of nodes (one node with branches) to make decision based on
     * @param path path to data to use in learning
     * @param classIndex index of classification data
     * @return root node with branches
     */
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
            if(Util.isAlways(data, mI, pR.get(i)))
                node.addBranch(new Node(Util.alwaysAnswer(data, mI, pR.get(i))), pR.get(i)); //if there is only ever one result when this answer is given, return that answer
            else{
                DataSet dT=new DataSet(data);
                for(int n=0;n<dT.size();n++){ //removes all instances where the answer doesn't match up, surveys only matching data
                    if(!dT.getDataAtIndex(n).getAttributeAt(mI).equals(pR.get(i))){dT.removePoint(n);n--;};
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
    /**
     *moves through build node tree with given data to find solution 
     * @param set set (line) of data being tested
     * @return
     */
    private String makeDecision(Record set){
        return root.classify(set);
    }
    /**
     * tests a set of data to determine tree accuracy
     * @param path path of test data
     * @return
     */
    public String testTree(String path){
        DataSet data=new DataSet(path, classIndex); //parse data from file
        int right=0,wrong=0; //correct/incorrect classification counter
        for(Record r:data.getData()){ //for each collection of data in the file...
            if(makeDecision(r).equals(r.getClassification())) right++; //if the classification is correct, add to correct counter
            else wrong++; //if the classification is wrong, add to incorrect counter
        }
        return "Accuracy: "+(double)right/wrong+"%"; //calculate accuracy and return
    }
}