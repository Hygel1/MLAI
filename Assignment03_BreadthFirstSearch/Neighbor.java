public class Neighbor implements Comparable<Neighbor>{
    private Point pnt;
    private Double distance;
    public Neighbor(Point pnt, double distance){
        this.pnt=pnt;
        this.distance=distance;
    }
    public double getDistance(){
        return distance;
    }
    public Point getPoint(){
        return pnt;
    }
    public int compareTo(Neighbor o) {
        double d=distance-o.distance;
        if(d>0) return 1; //if greater than 0 return positive number
        return d<0?-1:0; //if less than 0 return -1, otherwise return 0
    }
}