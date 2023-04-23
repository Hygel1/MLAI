import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
public class DataSet {
    //reads and parses dataset into readable format
    ArrayList<Record> data;
    ArrayList<String> attrs;
    int classIndex;
    public DataSet(){
        data=new ArrayList<>();
        attrs=new ArrayList<>();
    }
    public DataSet(String path, int classIndex){
        this.classIndex=classIndex;
        data=new ArrayList<>();
        attrs=new ArrayList<>();
        try{
            File f=new File(path);
            Scanner s=new Scanner(f);
            for(String string:s.nextLine().split(",")) attrs.add(string.trim());
            while(s.hasNext()){
                ArrayList<String> str=new ArrayList<String>();
                for(String string:s.nextLine().split(",")) str.add(string.trim());
                data.add(new Record(str,str.get(classIndex)));
            }
            s.close();
        } catch(Exception e){e.printStackTrace();}
    }
    public Record getDataAtIndex(int i){
        return data.get(i);
    }
    public void addRecord(Record r){
        data.add(r);
    }
    public DataSet removeAttributesAtIndex(int n){
        data.remove(n);
        return this;
    }
    public String toString(){
        String rtn="";
        for(String s:attrs) rtn+=s+", ";
        if(rtn.length()>2) rtn=rtn.substring(0,rtn.length()-2)+"\n"; //should always be called, if statement is for protection
        for(Record d:data) rtn+=d+"\n";
        return rtn;
    }
}
