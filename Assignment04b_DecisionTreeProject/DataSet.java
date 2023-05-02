/**
 * This class is provided for you so you can test your Util functions
 * modify at your own risk!
 */ 
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author C Michaud / www.nebomusic.net
 * Models a Dataset with ArrayList of Records
 *
 */

public class DataSet {
	
	private ArrayList<Record> data = new ArrayList<Record>();
	private ArrayList<String> attrs = new ArrayList<String>();
	
	private ArrayList<Integer> selectedAttributeIndexes = new ArrayList<Integer>(); // Note sure if will use
	
	public DataSet() {
		
	}
	
	/**
	 *  Creates and Returns a DataSet object from a file
	 * @param path String pointing to csv file
	 * @param classIndex Index 
	 */
	public DataSet(String path, int classIndex) {
		try {
			File file = new File(path);
			Scanner input = new Scanner(file);
			
			String [] header = input.nextLine().split(",");
			
			for (int i = 0; i < header.length; i++) {
				if (i != classIndex) {
					attrs.add(header[i]);
				}
			}
						
			while (input.hasNext()) {
				String [] line = input.nextLine().split(",");
				ArrayList<String> a = new ArrayList<String>();
				String c = "";
				for (int i = 0; i < line.length; i++) {
					if (i == classIndex) {
						c = line[i];
					}
					else a.add(line[i]);
				}
				Attributes atts = new Attributes(a);
				Record r = new Record(atts, c);
				r.setFieldNames(attrs);
				data.add(r);
			}
			
			input.close();
			
		}
		
		catch(Exception e) {
    		e.printStackTrace();
    	}
	}
	public DataSet(DataSet d){
		for(Record r:d.data) data.add(r);
		for(String s:d.attrs) attrs.add(s);
	}
	public void addRecord(Record r) {
		data.add(r);
	}
	
	public ArrayList<Record> getCopyData(){
		return new ArrayList<>(data);
	}
	public DataSet removeAtributeByName(String name) {
		DataSet output = new DataSet();
		ArrayList<String> fieldNamesMod=new ArrayList<>(attrs);
		fieldNamesMod.remove(name);
		for (Record r : data) {
			String c =r.getClassification();
			ArrayList<String> attr = r.getAttributes().getCopyValues();
			attr.remove(name);
			Record nr = new Record(attr, c);
			nr.setFieldNames(fieldNamesMod);
			output.addRecord(nr);
		}

		
		return output;
	}
	public void removePoint(int i){
		data.remove(i);
	}
	public ArrayList<Record> getData() {
		return data;
	}
	
	public Record getDataAtIndex(int i) {
		return data.get(i);
	}
	
	public Record getDataAtName(String name) {
		int index = attrs.indexOf(name);
		return data.get(index);
	}
	
	public ArrayList<String> getAttributeList() {
		return attrs;
	}
	
	public String toString() {
		String output = "";
		for (String s : attrs) {
			output += s + ",";
		}
		output += "classification," + "\n";
		
		for (Record r : data) {
			output += r.getAttributes().toString();
			output += r.getClassification() + "\n";
		}
		
		return output;
	}
	
	public int size() {
		return data.size();
	}
	
	public ArrayList<Integer> getUsedIndexes() {
		return selectedAttributeIndexes;
	}
	
	public void addUsedIndex(int index) {
		selectedAttributeIndexes.add(index);
	}
	
	public void setAttributes(ArrayList<String> attributes) {
		for (String s : attributes) {
			attrs.add(s);
		}
	}

}