import java.util.Stack;
public class Route extends Stack<City> implements Comparable<Route>{
    private int distance=0;
    int ind=-1;
    City goal;
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
    /**
     * constructor for aStar implementation (needs to know goal)
     * @param r
     * @param goal
     */
    public Route(City r, City goal){
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
    public Route(Route r, Neighbor rel, City goal){
        distance=r.distance();
        for(City c: r){
            add(c);
        }
        distance+=rel.getDistance();
        add(rel.getCity());
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
            double minpy=Math.sqrt(Math.abs((peek().getRel(i).getp1()-goal.getp1())*(peek().getRel(i).getp1()-goal.getp1()))+Math.abs((peek().getRel(i).getp2()-goal.getp2())*(peek().getRel(i).getp2()-goal.getp2())));
            for(int n=i;n<peek().relSize();n++){
                double py=Math.sqrt(Math.abs((peek().getRel(n).getp1()-goal.getp1())*(peek().getRel(n).getp1()-goal.getp1()))+Math.abs((peek().getRel(n).getp2()-goal.getp2())*(peek().getRel(n).getp2()-goal.getp2())));
                if(peek().getRelatedDistance(n)<peek().getRelatedDistance(small)){
                    small=n;
                    minpy=Math.sqrt(Math.abs((peek().getRel(small).getp1()-goal.getp1())*(peek().getRel(small).getp1()-goal.getp1()))+Math.abs((peek().getRel(small).getp2()-goal.getp2())*(peek().getRel(small).getp2()-goal.getp2())));
                }
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
