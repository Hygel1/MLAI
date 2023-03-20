public class related{
    private City city;
    private int distance;
    public related(City city, int distance){
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
}