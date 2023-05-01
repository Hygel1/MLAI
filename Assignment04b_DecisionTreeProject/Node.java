import java.util.ArrayList;
public class Node {
    private String attribute="";
    private int attributeIndex=-1;
    private ArrayList<Node> branches=new ArrayList<>(); //brach nodes
    private ArrayList<String> values=new ArrayList<>(); //possible returned values
    private String output="";
    //Constructors
    public Node(){}
    public Node(String attr, int index){ //builds a node with connections
        attribute=attr;
        attributeIndex=index;
    }
    public Node(String out){
        output=out;
    }
    public void addBranch(Node n, String v){
        branches.add(n);
        values.add(v);
    }
    public String getName(){
        return attribute;
    }
    public String classify(Record instance){
        if(branches.size()<1) return output;
        String value=instance.getAttributeAt(attribute);
        int branchIndex=values.indexOf(value);
        return branches.get(branchIndex).classify(instance);
    }
}