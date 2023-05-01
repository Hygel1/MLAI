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
	public static double getB(double q) {
		//TODO: Implement this function
		// Values below are placeholders
		//equation above uses log base 2 (log2q= log base 2 of q)
		//Math.log() uses ln, which can be manipulated to give custom bases using lna/lnb where b is the desired base and a is the log being solved for
		if(q>=1||q<=0) return 0;
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
		        if(found>-1) 
		            n.set(found, n.get(found)+1);
		        if(dta.get(i).getClassification().equals("Yes")) 
		            numTrue.set(found, numTrue.get(found)+1);
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
	/**
	 * Returns the Information Gain from an Attribute at Index
	 * @param data a DataSet of Records
	 * @param attr a String with a field name
	 * @return
	 */
	public static double getGain(DataSet data, String attr) {
		double gain = 0;
		double remainder=getRemainder(data,attr);
		ArrayList<Record> recs=data.getData();
		int p=0;
	    for(int i=0;i<recs.size();i++){
	        if(recs.get(i).getClassification().equals("Yes")) p++;
	    }
	    double b=(double) p/data.size();
	    double bigB=getB(b);
	    gain+=bigB;
	    gain-=remainder;
		return gain;
	}
	
	/**
	 * Return A_Important object with Attribute and index with highest information gain
	 * @param data A DataSet
	 * @param attributes A list of attributes
	 * @return
	 */
	public static String getImportance(Attributes attributes, DataSet data) {
		ArrayList<String> atr=attributes.getCopyValues();
		String out = atr.get(0);
		double biggest=getGain(data,out);
		for(int i=0;i<atr.size();i++){
		    double temp=getGain(data,atr.get(i));
		    if(temp>biggest){
		        out=atr.get(i);
		        biggest=temp;
		    }
		}
		return out;
	}
	//exclude index q
	public static String getImportance(Attributes attributes, DataSet data, int q) {
		ArrayList<String> atr=attributes.getCopyValues();
		String out = atr.get(0);
		double biggest=getGain(data,out);
		for(int i=0;i<atr.size();i++){
		    double temp=getGain(data,atr.get(i));
		    if(q!=i&&temp>biggest){
		        out=atr.get(i);
		        biggest=temp;
		    }
		}
		return out;
	}
	/**
	 * Returns a String with the classification
	 * Votes based on highest number of Classifications
	 * @param data
	 * @return
	 */
	public static String getPluralityValue(DataSet data) {
		int y=0,n=0;
		for(Record r:data.getData()){
		    if(r.getClassification().equals("Yes")) y++;
		    else n++;
		}
		if(y>=n) return "Yes";
		return "No"; 
	}
	public static ArrayList<String> getPossibleReturn(DataSet data, String attr){
		ArrayList<String> rtn=new ArrayList<>();
		for(Record r:data.getData()){
			String hold=r.getAttributeAt(attr);
			if(rtn.indexOf(hold)==-1) rtn.add(hold);
		}
		return rtn;
	}
	/**
	 * returns true if a given answer to a question always returns a single answer
	 * @param d
	 * @param attr
	 * @param answer
	 * @return
	 */
	public boolean isAlways(DataSet d, String attr, String answer){
		ArrayList<Record> data=d.getData();
		String val=null;
		for(int i=0;i<data.size();i++){
			if(val==null&&data.get(i).getAttributeAt(attr).equals(answer)) val=data.get(0).getClassification(); //set initial value on first encounter with answer
			if(val!=null&&data.get(i).getAttributeAt(attr).equals(answer)&&!data.get(i).getClassification().equals(val)) return false; //if a part has the same answer but not the same class, return false
		}
		return true;
	}
	public String alwaysAnswer(DataSet d, String attr, String answer){
		for(Record r:d.getData()) if(r.getAttributeAt(attr).equals(answer)) return r.getClassification();
		return null;
	}
}