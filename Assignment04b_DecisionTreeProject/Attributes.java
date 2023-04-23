import java.util.ArrayList;
public class Attributes{
    //defines a collection of attributes dedscribing a node
    ArrayList<String> fields;
    ArrayList<String> fieldNames;
    public Attributes(ArrayList<String> input){
        fields=new ArrayList<>(input);
    }
    public Attributes(Attributes a){
        fields=new ArrayList<String>(a.fields);
        fieldNames=new ArrayList<String>(a.fieldNames);
    }
    public ArrayList<String> getValues(){
        return fields;
    }
    public ArrayList<String> getCopyValues(){
        return new ArrayList<String>(fields);
    }
    public ArrayList<String> getFieldNames(){
        return new ArrayList<String>(fieldNames);
    }
    public void setFieldNames(ArrayList<String> s){
        fieldNames=new ArrayList<>(s);
    }
    public String getField(int i){
        return fields.get(i);
    }
    public String getValueAt(int index){
        return fields.get(index);
    }
    public String getValueAt(String s){
        if(fields.indexOf(s)==-1) return null;
        return fields.get(fields.indexOf(s));
    }
    /**
     * print comma separated versoin of all values in fields
     */
    public String toString(){
        String rtn="";
        for(String s:fields){
            rtn+=s+", ";
        }
        if(rtn.length()>2) return rtn.substring(0,rtn.length()-2);
        return rtn; //shouldn't ever be used
    }
    public void remove(int index) {
        fields.remove(index);
        if(fieldNames!=null) fieldNames.remove(index);
    }
    public void remove(String name) {
        int ind=fieldNames.indexOf(name);
        fieldNames.remove(ind);
        fields.remove(ind);
    }
}
