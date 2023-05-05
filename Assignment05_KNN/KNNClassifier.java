import java.util.ArrayList;
public class KNNClassifier {
    private ArrayList<Record> data;
    private VoteCounter vC;
    /**
     * builds a classifier with default (empty) data and voteCounter
     */
    public KNNClassifier(){
        data=new ArrayList<>();
        vC=new VoteCounter();
    }
    /**
     * builds a classifier with a given set of data and a fresh voteCounter
     * @param data
     */
    public KNNClassifier(ArrayList<Record> data){
        data=new ArrayList<>(data);
        vC=new VoteCounter();
    }
    /**
     * returns int representing the number of data points held
     * @return
     */
    public int getDataSize(){
        return data.size();
    }
    /**
     * add record to data list
     * @param r
     */
    public void addRecord(Record r){
        data.add(r);
    }
    /**
     * TODO: make method classify with math
     * @param x
     * @param k number of votes to take
     * @return classification String
     */
    public String knnClassify(ArrayList<Double> x, int k){

    }
    /**
     * TODO: this is where the math for knnclassify goes
     * @param p1
     * @param p2
     * @return
     */
    public double getDistance(double[] p1, double[] p2){

    }
    /**
     * votes + data
     */
    public String toString(){
        return vC+" votes : "+data;
    }
}
