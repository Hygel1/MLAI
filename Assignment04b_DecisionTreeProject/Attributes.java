import java.util.ArrayList;
import java.util.List;

/**
 * This class is provided for you so you can concentrate on testing the Util.java file
 * Modify at your own Risk!
 * @author C Michaud / www.nebomusic.net
 */ 
public class Attributes {
	
	private ArrayList<String> fields = new ArrayList<String>();
	
	public Attributes (List<String> input) {
		for (String s : input) {
			String n = new String(s);
			fields.add(n);
		}
	}
	
	public Attributes(Attributes a) {
		for (String s : a.getValues()) {
			fields.add(s);
		}
	}
	public int size(){
		return fields.size();
	}
	public ArrayList<String> getValues() {
		return fields;
	}
	public int indexOf(String s){
		return fields.indexOf(s);
	}
	public ArrayList<String> getCopyValues() {
		ArrayList<String> output = new ArrayList<String>();
		for (String s : fields) {
			String n = new String(s);
			output.add(n);
		}
		return output;
	}
	
	public String toString() {
		String output = "";
		
		for (String s : fields) {
			output += s + ",";
		}
		
		return output;
	}
	
	public void removeAttributeAtIndex(int i) {
		fields.remove(i);
	}
	public Attributes removeAndCopy(String s){
		Attributes rtn=new Attributes(this);
		rtn.removeAttributeByName(s);
		return rtn;
	}
	public void removeAttributeByName(String name) {
		fields.remove(name);
	}
}