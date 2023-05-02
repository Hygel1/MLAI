import java.util.ArrayList;
public class Record{
    String name; //classification
    ArrayList<Double> values=new ArrayList<>(); //list of values given for training
    public Record(String[] arr){
        name=arr[0];
        for(int i=1;i<arr.length;i++){
            values.add(Double.parseDouble(arr[i]));
        }
    }
    public String getClassification(){
        return name;
    }
}