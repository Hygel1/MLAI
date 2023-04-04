public class Neighbor implements Comparable<Neighbor>{
    private City city;
    private int distance;
    public Neighbor(City city, int distance){
        this.city=city;
        this.distance=distance;
    }
    public int getDistance(){
        return distance;
    }
    public City getCity(){
        return city;
    }
    public String toString(){
        return city.getName()+" is "+distance+" away";
    }
    public int compareTo(Neighbor o) {
        return distance=o.distance;
    }
}