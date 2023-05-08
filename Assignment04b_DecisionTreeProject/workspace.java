public class workspace{
    public static void main(String[] args){
        //String path="Assignment04b_DecisionTreeProject\\iris_data\\iris.csv";
        //DataSet d=new DataSet(path, 4);
        //System.out.println(d);
        testNode();
    }
	
    public static void testNode(){
        Node leaf1=new Node("No");
        Node leaf2=new Node("Yes");

        Node root=new Node("Pat", 4);
        Node node1=new Node("Hun", 3);

        root.addBranch(leaf1,"None");
        root.addBranch(leaf2, "Some");
        root.addBranch(node1, "Full");

        String path="Assignment04b_DecisionTreeProject\\data\\iris_data\\iris_train.csv";
        DataSet data=new DataSet(path, 10);

        Record test=data.getDataAtIndex(0);
        System.out.println(test.getAttributes());

        //String classification=root.classify(test);
        //System.out.println("Class: "+classification+"\n");
		System.out.println(Util.getImportance(new Attributes(data.getAttributeList()), data));
		DecisionTree dT=new DecisionTree(path,10); //make new tree with training data
        String path2="Assignment04b_DecisionTreeProject\\data\\iris_data\\iris_test.csv";
        System.out.println(dT.testTree(path2));
    }
}