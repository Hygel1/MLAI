import java.util.Stack;
import java.util.PriorityQueue;
import java.util.ArrayList;
public class Route extends Stack<Point> implements Comparable<Route>{
    private int distance=0;
    int ind=-1;
    Point goal;
    private int[] order;
    PriorityQueue<Neighbor> next=new PriorityQueue<>();
    public Route(ArrayList<Point> arr){
        for(Point a:arr){
            add(a);
        }
    }
    public Route(Route r){
        distance=r.distance();
        for(Point c: r){
            add(c);
        }
        for(int i=0;i<peek().relSize();i++){
            next.add(peek().getRelated(i));
        }
        fillOrder();
    }
    public Neighbor getNextN(){
        if(outOfNeighbors()) return null;
        return next.remove();
    }
    public boolean outOfNeighbors(){
        return next.size()==0;
    }
    public Route(Route r, Neighbor rel){
        distance=r.distance();
        for(Point c: r){
            add(c);
        }
        distance+=rel.getDistance();
        add(rel.getPoint());
        for(int i=0;i<peek().relSize();i++){
            next.add(peek().getRelated(i));
        }
        fillOrder();
    }
    public Route(Point r){
        add(r);
        for(int i=0;i<peek().relSize();i++){
            next.add(peek().getRelated(i));
        }
        fillOrder();
    }
    /**
     * constructor for aStar implementation (needs to know goal)
     * @param r
     * @param goal
     */
    public Route(Point r, Point goal){
        add(r);
        this.goal=goal;
        fillOrderAStar();
    }
    /**
     * constructor for aStar implementation (needs to know goal)
     * @param r
     * @param rel
     * @param goal
     */
    public Route(Route r, Neighbor rel, Point goal){
        distance=r.distance();
        for(Point c: r){
            add(c);
        }
        distance+=rel.getDistance();
        add(rel.getPoint());
        this.goal=goal;
        fillOrderAStar();
    }
    public int distance(){
        return distance;
    }
    public Neighbor nextStep(){
        ind++;
        return peek().getRelated(order[ind]);
    }
    public void fillOrder(){
        order=new int[peek().relSize()];
        for(int i=0;i<peek().relSize();i++) order[i]=i;
        for(int i=0;i<peek().relSize();i++){
            int small=i;
            for(int n=i;n<peek().relSize();n++){
                if(peek().getRelatedDistance(n)<peek().getRelatedDistance(small)) small=n;
            }
            int a=order[i];
            order[i]=order[small];
            order[small]=a;           
        }
    }
    public void fillOrderAStar(){
        order=new int[peek().relSize()];
        for(int i=0;i<peek().relSize();i++) order[i]=i;
        for(int i=0;i<peek().relSize();i++){
            int small=i;
            //double minpy=Math.sqrt(Math.abs((peek().getRel(i).getp1()-goal.getp1())*(peek().getRel(i).getp1()-goal.getp1()))+Math.abs((peek().getRel(i).getp2()-goal.getp2())*(peek().getRel(i).getp2()-goal.getp2())));
            for(int n=i;n<peek().relSize();n++){
                //double py=Math.sqrt(Math.abs((peek().getRel(n).getp1()-goal.getp1())*(peek().getRel(n).getp1()-goal.getp1()))+Math.abs((peek().getRel(n).getp2()-goal.getp2())*(peek().getRel(n).getp2()-goal.getp2())));
                if(peek().getRelatedDistance(n)<peek().getRelatedDistance(small)){
                    small=n;
                    //minpy=Math.sqrt(Math.abs((peek().getRel(small).getp1()-goal.getp1())*(peek().getRel(small).getp1()-goal.getp1()))+Math.abs((peek().getRel(small).getp2()-goal.getp2())*(peek().getRel(small).getp2()-goal.getp2())));
                }
            }
            int a=order[i];
            order[i]=order[small];
            order[small]=a;           
        }
    }
    public int compareTo(Route o) {
        return distance()-o.distance();
    }
    public String toString(){
        String rtn=distance+": [";
        Object[] c=this.toArray();
        for(int i=0;i<c.length;i++){
            rtn+=((City)c[i]).getName();
            if(i<c.length-1)rtn+=", ";
        }
        return rtn+"]";
    }
}
