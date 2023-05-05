import java.util.ArrayList;
public class VoteCounter {
    private ArrayList<String> votes; //index matched with counts description in constructor
    private ArrayList<Integer> counts;
    /**
     * new vote counter with empty votes and counts lists
     */
    public VoteCounter(){
        votes=new ArrayList<>(); //list of classifications encountered with votes
        counts=new ArrayList<>(); //list of number of votes for each encountered element
    }
    /**
     * adds vote to an element and creates a new element if it doesn't already exist
     * @param v
     */
    public void addVote(String v){
        int h=votes.indexOf(v);
        if(h==-1){ //if the element has never been seen in this counter, add the element with a starting count of 1
            votes.add(v); //make new element to vote on
            counts.add(1); //default vote to one
        }
        else counts.set(h,counts.get(h)+1); //if the element has been seen before, add to the count for that element
    }
    /**
     * returns vote with the highest count
     * @return
     */
    public String getVote(){
        int max=0;
        for(int i=1;i<counts.size();i++){
            if(counts.get(i)>counts.get(max)) max=i;
        }
        return votes.get(max);
    }
    /**
     * checks to see if the winning vote is tied, disregards irrelevant ties
     * @return
     */
    public boolean isTied(){
        int max=0; //holds max value in counts list
        for(int i=1;i<counts.size();i++){ //find max value in counts list
            if(counts.get(i)>counts.get(max)) max=i;
        }
        boolean b=false; //set to true if one value of the max value has been found
        for(int a:counts){ //scan list
            if(a==max){
                if(b) return true; //if there are two instances of the max value, return true
                b=true; //if the first instance of the max value has been found, set to true
            }
        }
        return false; //if nothing is found, return false
    }
}
