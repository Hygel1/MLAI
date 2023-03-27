import java.util.ArrayList;
import java.util.Stack;
public class Route extends Stack<City>{
    private int distance=0;
    private ArrayList<City> visited;
    public Route(Route r){
        distance=r.distance();
        for(City c: r){
            add(c);
        }
    }
    public Route(Route r, related rel){
        distance=r.distance();
        for(City c: r){
            add(c);
        }
        distance+=rel.getDistance();
        add(rel.getCity());
    }
    public Route(City r){
        add(r);
    }
    public void addDistance(int d){
        distance+=d;
    }
    public related shortestStep(){
        int ind=0;
        for(int i=1;i<peek().relSize();i++)if(peek().getRelatedDistance(ind)>peek().getRelatedDistance(i)) ind=i;
        return peek().getRelated(ind);
    }
    public int distance(){
        return distance;
    }

}
