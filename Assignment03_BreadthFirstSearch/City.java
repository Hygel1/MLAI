import java.util.ArrayList;
public class City{
    private String name;
    private int p1,p2;
    private ArrayList<related> rel=new ArrayList<>();
    public City(String name, int p1, int p2){
        this.name=name;
        this.p1=p1;
        this.p2=p2;
    }
    public String getName(){
        return name;
    }
    public int getp1(){
        return p1;
    }
    public int getp2(){
        return p2;
    }
    public void addRelated(related r){
        rel.add(r);
    }
    public int getRelatedDistance(int n){
        return rel.get(n).getDistance();
    }
    public int relSize(){
        return rel.size();
    }
    public City getRel(int n){
        return rel.get(n).getCity();
    }
    public related getRelated(int n){
        return rel.get(n);
    }
    public String toString(){
        return name+" "+p1+", "+p2;
    }
    /**
     * compares names
     * @param c
     * @return
     */
    public boolean equals(City c){
        return c.getName().equals(name);
    }
}
