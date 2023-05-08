import java.util.ArrayList;
public class KNNRecord {
    private ArrayList<Double> values; //index matched with fieldNames
    private ArrayList<String> fieldNames; 
    private String classification;
    public KNNRecord(String[] value){ //takes String[] and creates Record object with values and classification (first index is class)
        values=new ArrayList<>();
        classification=value[0]; //set classification value
        for(int i=1;i<value.length;i++){ //set all other double values
            values.add(Double.parseDouble(value[i]));
        }
    }
    /**
     * returns value at specified index
     * @param i
     * @return
     */
    public Double getValAt(int i){
        return values.get(i);
    }
    /**
     * returns value at specif fieldName
     * @param field
     * @return
     */
    public Double getValueAt(String field){
        return values.get(fieldNames.indexOf(field));
    }
    /**
     * returns the classification String
     * @return
     */
    public String getClassification(){
        return classification;
    }
    /**
     * returns copy of fieldNames arraylist
     * @return
     */
    public ArrayList<String> getFieldNames(){
        return new ArrayList<>(fieldNames);
    }
    public void setFieldNames(ArrayList<String> fN){
        fieldNames=new ArrayList<>(fN);
    }
    public ArrayList<Double> getValues(){
        return values;
    }
    /**
     * classification + values
     */
    public String toString(){
        return classification+" "+values;
    }
}
