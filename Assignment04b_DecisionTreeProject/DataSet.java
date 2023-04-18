import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
public class DataSet {
    //reads and parses dataset into readable format
    ArrayList<Node> data;
    ArrayList<String> attrs;
    public DataSet(){
        data=new ArrayList<>();
        attrs=new ArrayList<>();
    }
    public DataSet(String path, int classIndex){
        try{
            File f=new File(path);
            Scanner s=new Scanner(f);
            while(s.hasNext()){
                ArrayList<String> str=new ArrayList<String>();
                for(String string:s.nextLine().split(",")) str.add(string);
            }
        } catch(Exception e){}

    }
    public void addRecord(Node r){
        data.add(r);
    }
    public DataSet removeAttributeAtIndex(int n){
        ArrayList<String> a=new ArrayList<>();
        for(int i=0;i<attrs.size();i++){
            if(i!=n) a.add(attrs.get(i));
        }
    }
}
