import java.util.ArrayList;
public class Point{
    private int p1,p2;
    private ArrayList<Neighbor> rel=new ArrayList<>();
    public Point(int p1,int p2){
        this.p1=p1;
        this.p2=p2;
    }
    public int getp1(){
        return p1;
    }
    public int getp2(){
        return p2;
    }
    public void addRelated(Neighbor r){
        rel.add(r);
    }
    public double getRelatedDistance(int n){
        return rel.get(n).getDistance();
    }
    public int relSize(){
        return rel.size();
    }
    public Point getRel(int n){
        return rel.get(n).getPoint();
    }
    public Neighbor getRelated(int n){
        return rel.get(n);
    }
}
