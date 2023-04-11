public class City extends Point{
    private String name;
    private int p1,p2;
    public City(String name, int p1, int p2){
        super(p1,p2);
        this.name=name;
    }
    public String getName(){
        return name;
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
