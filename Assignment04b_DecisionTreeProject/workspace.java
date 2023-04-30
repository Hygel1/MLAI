import java.util.ArrayList;
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

        String path="Assignment04b_DecisionTreeProject\\iris_data\\iris.csv";
        DataSet data=new DataSet(path, 4);

        Record test=data.getDataAtIndex(0);
        System.out.println(test.getAttributes());

        String classification=root.classify(test);
        System.out.println("Class: "+classification+"\n\n\n\n\n");
        //System.out.println(getRemainder(data,"Pat"));

    }
    
    /**
	 * Returns the entropy of a Boolean random
	 * variable that is true with a probability q
	 * Be careful!  You can't get values for 1 and 0
	 * @param q double in range of (0, 1)
	 * @return -(qlog2q + (1-q)log2(1-q)) Entropy Value
	 */
	public static double getB(double q) {
		//TODO: Implement this function
		// Values below are placeholders
		//equation above uses log base 2 (log2q= log base 2 of q)
		//Math.log() uses ln, which can be manipulated to give custom bases using lna/lnb where b is the desired base and a is the log being solved for
		return -1*(q*(Math.log(q)/Math.log(2))+(1-q)*(Math.log(1-q)/Math.log(2)));
	}
    /**
	 * Gets the Remainder value of DataSet data at a specific attribute
	 * @param data a DataSet of Records
	 * @param attr a String with a field name
	 * @return Remainder Value of DataSet data at index of Attribute
	 */
    public static double getRemainder(DataSet data, String attr) {
		ArrayList<Record> dta=data.getData(); //all data points
		ArrayList<String> answers=new ArrayList<>();
		ArrayList<Integer> numTrue=new ArrayList<>();
		ArrayList<Integer> n=new ArrayList<>();
		double remainder=0;
		for(int i=0;i<dta.size();i++) {
		    String attrLocal=dta.get(i).getAttributeAt(attr);
		    if(!answers.contains(attrLocal)){
		        answers.add(attrLocal);
		        n.add(1);
		        if(dta.get(i).getClassification().equals("Yes"))
		            numTrue.add(1);
		        else numTrue.add(0);
		    }
		    else{
		        int found=answers.indexOf(attrLocal);
		        if(found>-1) n.set(found, n.get(found)+1);
		        if(dta.get(i).getClassification().equals("Yes")) numTrue.set(found, numTrue.get(found)+1);
		        
		    }
		}
		int d=answers.size();
		int pn=data.size();
		for(int j=0;j<d;j++){
		    int pk=numTrue.get(j);
		    int nk=n.get(j)-numTrue.get(j);
            double q=(double)pk/(pk+nk);
            double b=getB(q);
            double beforeB=(double)(pk+nk)/pn;
            double out=(double) beforeB*b;
            remainder+=out;
		}
		return remainder;
	}

}