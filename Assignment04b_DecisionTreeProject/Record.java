import java.util.ArrayList;
public class Record {
    //describes a Record to be evaluated
    Attributes attributes;
    String classification;
    public Record(Attributes a, String c){
        attributes=a;
        classification=c;
    }
    public Record(ArrayList<String> a, String c){
        classification=c;
        attributes=new Attributes(a);
    }
    public Attributes getAttributes(){
        return attributes;
    }
    public String getClassification(){
        return classification;
    }
    public String getAttributeAtIndex(int index){
        return attributes.getField(index);
    }
    public void removeAttributeAtIndex(int index){
        attributes.remove(index);
    }
    public void removeAttrbuteByName(String name){
        attributes.remove(name);
    }
    public String toString(){
        return attributes+" Class: "+classification;
    }
}
