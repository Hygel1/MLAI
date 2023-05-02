/**
 * This class is provided for you so you can test your Util functions
 * modify at your own risk!
 * @author C Michaud / www.nebomusic.net
 */ 
import java.util.ArrayList;
import java.util.List;

public class Record {
	
	private Attributes attributes;
	private String classification = "";
	private ArrayList<String> fieldNames = new ArrayList<String>();
	
	public Record(Attributes a, String c) {
		attributes = a;
		classification = c;
	}
	
	public Record(List<String> a, String c) {
		attributes = new Attributes(a);
		classification = c;
		
	}
	
	public Attributes getAttributes() {
		return attributes;
	}
	
	public String getClassification() {
		return classification;
	}
	
	public String getAttributeAtIndex(int i) {
		String output = attributes.getValues().get(i);
		return output;
	}
	
	public String getAttributeAt(String attr) {
		int index = fieldNames.indexOf(attr);
		String out = attributes.getValues().get(index);
		return out;
	}
	
	public void setFieldNames(ArrayList<String> names) {
		for (String s : names) {
			fieldNames.add(s);
		}
	}
	
	public String toString() {
		return attributes.toString() + ": " + classification;
	}

}