import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class RouteFinder {
    public static void main(String[] args){
        ArrayList<City> data=readMapData("Assignment02_DataAnalysis\\MysteryData.txt");
        ArrayList<City> route=findPathBreadthFirst(data, data.get(0),data.get(13));
        ArrayList<City> route1=uniformCost(data, data.get(0), data.get(13));
        System.out.println(route);
        System.out.println(route1);
        displayMap(data);

        //displayRouteSequence(route);
    }
    /**
     * traces route defined in c
     * @param c
     */
    public static void displayRoute(ArrayList<City> c){
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        for(int i=0;i<c.size()-1;i++){
            StdDraw.line(c.get(i).getp1(),c.get(i).getp2(),c.get(i+1).getp1(),c.get(i+1).getp2());
        }
        StdDraw.show();
        StdDraw.setPenRadius(.005);
    }
    /**
     * draws line between adjacent points in c
     * should add 1 second intervals between lines but doesn't work 
     * @param c route to be traced
     */
    public static void displayRouteSequence(ArrayList<City> c){
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        Object o=new Object();
        for(int i=0;i<c.size()-1;i++){
           try{
            o.wait(1000);
           }catch(Exception e){}
            StdDraw.line(c.get(i).getp1(),c.get(i).getp2(),c.get(i+1).getp1(),c.get(i+1).getp2());
            StdDraw.show();
        }
        StdDraw.setPenRadius(.005);
    }
    /**
     * takes ArrayList of City classes and displays map according to data
     * @param data
     */
    public static void displayMap(ArrayList<City> data){
        int h=1000,w=1000;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(0,w);
        StdDraw.setYscale(0,h);
        StdDraw.setPenRadius(.005);
        for(int i=0;i<data.size();i++){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(data.get(i).getp1(),data.get(i).getp2(),5);
            StdDraw.text(data.get(i).getp1(),data.get(i).getp2()-10,data.get(i).getName()); //draw point for each city
            for(int n=0;n<data.get(i).relSize();n++){ //draw line to each related city
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(data.get(i).getp1(), data.get(i).getp2(), data.get(i).getRel(n).getp1(), data.get(i).getRel(n).getp2());
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text((data.get(i).getp1()+data.get(i).getRel(n).getp1())/2,(data.get(i).getp2()+data.get(i).getRel(n).getp2())/2,data.get(i).getRelatedDistance(n)+"");
            }
        }
        StdDraw.show();
    }
    /**
     * reads map data from txt using scanner and exports as ArrayList of City classes
     * @param path
     * @return
     */
    public static ArrayList<City> readMapData(String path){
        ArrayList<City> rtn=new ArrayList<>();
        try{
            File file=new File(path);
            Scanner sc=new Scanner(file);
            while(sc.hasNext()){
                String[] h=sc.nextLine().split(", ");
                rtn.add(new City(h[0], Integer.valueOf(h[1]), Integer.valueOf(h[2])));
            }
            sc.close();sc=new Scanner(file);
            while(sc.hasNext()){
                String[] h=sc.nextLine().split(", ");
                try{
                    int r=Integer.MAX_VALUE;
                    for(int n=0;n<rtn.size();n++){
                        if(rtn.get(n).getName().equals(h[0])){ r=n;break;}
                    }
                    for(int i=3;i<h.length;i+=2){
                        int w=Integer.MAX_VALUE;
                        for(int a=0;a<rtn.size();a++){
                            if(rtn.get(a).getName().equals(h[i])){
                                w=a;break;
                            }
                        }
                        rtn.get(r).addRelated(new related(rtn.get(w), Integer.valueOf(h[i+1])));
                    }
                }catch(Exception e){e.printStackTrace();}
            }
            sc.close();
        }catch(Exception e){e.printStackTrace();}
        return rtn;
    }
    /**
     * takes an ArrayList of cites, one start city, and one end city; finds the shortest path by node count
     * @param cities cities to choose from
     * @param start city to start at
     * @param end city to end at
     * @return shortest path (by node count) from start to end
     */
    public static ArrayList<City> findPathBreadthFirst(ArrayList<City> cities, City start, City end){
        if(start.equals(end)) return new ArrayList<City>();
        ArrayList<City> visited=new ArrayList<>();
        visited.add(start);
        ArrayList<ArrayList<City>> frontier=new ArrayList<>();
        for(int i=0;i<start.relSize();i++){
            ArrayList<City> hold=new ArrayList<>();hold.add(start);hold.add(start.getRel(i));
            frontier.add(hold);
        }
        if(frontier.size()==0) return null;//if frontier starts empty, start city has no related cities, return null (failed)
        while(0<frontier.size()){
            for(int i=0;i<frontier.get(0).get(frontier.get(0).size()-1).relSize();i++){ //for every rel city of the last city of the first direction in frontier...
                if(!visited.contains(frontier.get(0).get(frontier.get(0).size()-1).getRel(i))){ //if the city hasn't been visited yet...       
                    visited.add(frontier.get(0).get(frontier.get(0).size()-1).getRel(i)); //add the current city to the list of visited cities
                    frontier.add(new ArrayList<>(frontier.get(0)));frontier.get(frontier.size()-1).add(frontier.get(0).get(frontier.get(0).size()-1).getRel(i)); //append frontier with a new direction including the unvisited rel city                   
                    if(frontier.get(frontier.size()-1).get(frontier.get(frontier.size()-1).size()-1).equals(end)) return frontier.get(frontier.size()-1); //if the last checked city is the final destination, return the last made direction set
                }
            }
            frontier.remove(0);
        }
        return null; //if final destination wasn't found, return null (failed)
    }
    /**
     * returns the best path from start to end using distance between cities as cost rather then node count
     * @param c
     * @param start
     * @param end
     * @return
     */
    public static ArrayList<City> uniformCost(ArrayList<City> c, City start, City end){
        if(start.equals(end)) return null;
        ArrayList<City> visited=new ArrayList<>();
        visited.add(start);
        ArrayList<Integer> finalD=new ArrayList<>();
        ArrayList<ArrayList<City>> finalR=new ArrayList<>();
        ArrayList<ArrayList<City>> frontier=new ArrayList<>();
        ArrayList<Integer> distances=new ArrayList<>();
        for(int i=0;i<start.relSize();i++){
            frontier.add(new ArrayList<>());
            frontier.get(i).add(start);
            frontier.get(i).add(start.getRel(i));
            distances.add(start.getRelatedDistance(i));
        }
        if(frontier.size()==0) return null;
        while(0<frontier.size()){
            for(int i=0;i<frontier.get(0).get(frontier.get(0).size()-1).relSize();i++){
                if(!visited.contains(frontier.get(0).get(frontier.get(0).size()-1).getRel(i))){
                    visited.add(frontier.get(0).get(frontier.get(0).size()-1).getRel(i));
                    frontier.add(new ArrayList<>(frontier.get(0)));frontier.get(frontier.size()-1).add(frontier.get(0).get(frontier.get(0).size()-1).getRel(i));
                    distances.add(distances.get(0)+frontier.get(0).get(frontier.get(0).size()-1).getRelatedDistance(i));
                    if(frontier.get(frontier.size()-1).get(frontier.get(frontier.size()-1).size()-1).equals(end)){ finalR.add(frontier.get(frontier.size()-1));finalD.add(distances.get(0)+frontier.get(0).get(frontier.get(0).size()-1).getRelatedDistance(i));break;}
            }
            }
            frontier.remove(0);distances.remove(0);
        }
        if(finalD.size()==0) return new ArrayList<City>(); //catching on this line, which likely means that 154 is not being activated properly
        int min=0;
        for(int i=1;i<finalD.size();i++) min=Math.min(finalD.get(min),finalD.get(i));
        return finalR.get(min);
    }
}
