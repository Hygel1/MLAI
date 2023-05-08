import java.util.ArrayList;
public class KNNClassifier {
    private ArrayList<KNNRecord> data;
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
    public KNNClassifier(ArrayList<KNNRecord> data){
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
    public void addRecord(KNNRecord r){
        data.add(r);
    }
    /**
     * @param x set of data being classified
     * @param k number of votes to take
     * @return classification String
     */
    public String knnClassify(ArrayList<Double> x, int k){
        ArrayList<KNNRecord> bucket=new ArrayList<>(); //holds list of values that were taken from data list
        VoteCounter cnt=new VoteCounter(); //clear VoteCounter to start
        for(int i=0;i<k||cnt.isTied();i++){ //runs if not at K or there is a tie
            int minIndex=0;//set as first value to begin
            double minDis=getDistance(x,data.get(0).getValues());
            for(int n=1;n<data.size();n++){ //loop through all data to find min distance
                double dis=getDistance((x),data.get(n).getValues()); 
                if(dis<minDis){ //if smaller distance found, replace smallest trackers
                    minIndex=n;
                    minDis=dis;
                }
            }
            cnt.addVote(data.get(minIndex).getClassification()); //add vote accordingly
            bucket.add(data.remove(minIndex)); //remove from data set and add to temp bucket data
        }
        for(KNNRecord r:bucket) data.add(r); //replace stolen record from bucket
        return cnt.getVote(); //return highest vote
    }
    /**
     * @param p1 list of values being typed
     * @param p2 list of values being chceked against
     * @return euclidian distance from p1 set to p2 set
     */
    public double getDistance(ArrayList<Double> p1, ArrayList<Double> p2){
        double x=0;
        for(int i=0;i<p1.size();i++){
            x+=(p1.get(i)-p2.get(i))*(p1.get(i)-p2.get(i));
        }
        return Math.sqrt(x);
    }
    /**
     * votes + data
     */
    public String toString(){
        return vC+" votes : "+data;
    }
}
