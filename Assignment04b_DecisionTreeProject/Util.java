/**
 * Implement the functions in this class to test your Util functions
 */ 
import java.util.ArrayList;
public class Util
{

	/**
	 * Returns the entropy of a Boolean random
	 * variable that is true with a probability q
	 * Be careful!  You can't get values for 1 and 0
	 * @param q double in range of (0, 1)
	 * @return -(qlog2q + (1-q)log2(1-q)) Entropy Value
	 * 
	 */
	public double getB(double q) {
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
	public double getRemainder(DataSet data, String attr) {
		ArrayList<attrHold> t=new ArrayList<>();
		for(Record r:data.getData()) {
		    String s=r.getAttributeAt(attr);
		    int num=indAttr(t,s);
		    boolean b=r.getClassification().equals("yes");
		    if(num>1){
		        if(b) t.get(num).addPos();
		        else t.get(num).addNeg();
		    }
		    else t.add(new attrHold(s,b));
		}
		double remainder = 0;
		for(int i=0;i<t.size();i++){
		    remainder+=(t.get(i).getTotal()/data.size())*getB(t.get(i).getPos()/data.size());
		}
		return remainder;
	}
	public int indAttr(ArrayList<attrHold> attrs, String name){
	    for(int i=0;i<attrs.size();i++) if(attrs.get(i).isEqual(name)) return i; //if the same name exists in the list return true
	    return -1; //if it doesn't exst return false
	}
    public class attrHold{ //holds name, number of occurences, and number of postive instances of an attribute
        String name; int num=0,pos=0;
        public attrHold(String name, boolean b){this.name=name;num++;if(b)pos++;}
        public void addNeg(){num++;}
        public void addPos(){num++;pos++;}
        public int getPos(){return pos;}
        public int getTotal(){return num;}
        public boolean isEqual(String n){return name.equals(n);}
    }
	/**
	 * Returns the Information Gain from an Attribute at Index
	 * @param data a DataSet of Records
	 * @param attr a String with a field name
	 * @return
	 */
	public double getGain(DataSet data, String attr) {
		double gain = 0;
		/* Loop through each item in data 
		   gain = gain + 
		
		*/
		return gain;
	}
	
	/**
	 * Return A_Important object with Attribute and index with highest information gain
	 * @param data A DataSet
	 * @param attributes A list of attributes
	 * @return
	 */
	public String getImportance(Attributes attributes, DataSet data) {
		String out = "";
		
		return out;
	}
		
	/**
	 * Returns a String with the classification
	 * Votes based on highest number of Classifications
	 * @param data
	 * @return
	 */
	public String getPluralityValue(DataSet data) {
		String out = "";
		
		return out;
		
	}

	
	

}
