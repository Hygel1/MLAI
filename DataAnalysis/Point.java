public class Point{
    private String name;
    private double p1,p2;
    public Point(String name, double p1, double p2){
        this.name=name;
        this.p1=p1;
        this.p2=p2;
    }
    public double getP1(){return p1;}
    public double getP2(){return p2;}
    public String getName(){return name;}
}