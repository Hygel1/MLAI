import java.util.Stack;
public class Route extends Stack<City> implements Comparable<Route>{
    private int distance=0;
    int ind=-1;
    private int[] order;//=new int[peek().relSize()];
    public Route(Route r){
        distance=r.distance();
        for(City c: r){
            add(c);
        }
        fillOrder();
    }
    public Route(Route r, Neighbor rel){
        distance=r.distance();
        for(City c: r){
            add(c);
        }
        distance+=rel.getDistance();
        add(rel.getCity());
        fillOrder();
    }
    public Route(City r){
        add(r);
        fillOrder();
    }
    public void addDistance(int d){
        distance+=d;
    }
    public int distance(){
        return distance;
    }
    public Neighbor nextStep(){
        ind++;
        return peek().getRelated(order[ind]);
    }
    public void stepAdv(){
        ind++;
    }
    public boolean has(City c){
        for(int i=0;i<size()-1;i++){
            if(get(i).equals(c)) return true;
        }
        return false;
    }
    public int numSteps(){
        return order.length;
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
    public int compareTo(Route o) {
        return distance()-((Route)o).distance();
    }
}
