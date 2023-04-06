import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.File;
import java.util.Scanner;
public class RouteFinder {
    static int stepCounter=0;
    public static void main(String[] args){
        ArrayList<City> data=readMapData("Assignment02_DataAnalysis\\MysteryData.txt");
        //System.out.println(1);
        //displayMap(data);
        drawMaze("Assignment03_BreadthFirstSearch\\dataFiles\\maze08-large.txt");
        ArrayList<City> mazeData=readMazeData("Assignment03_BreadthFirstSearch\\dataFiles\\maze07-large.txt");
        //ArrayList<City> rte=findPathBreadthFirst(data, data.get(0),data.get(13));
        Route rte=uniformCost(data, data.get(2), data.get(12));
        Route rte2=aStarSearch(data, data.get(10), data.get(5));
        Route rte3=uniformCost(mazeData, mazeData.get(0), mazeData.get(mazeData.size()-1));
        //mazeVisualizer(rte3);
        //System.out.println(mazeData);
        System.out.println(rte3+"\n"+stepCounter);
        
        /*System.out.println("2");
        System.out.println(rte);
        System.out.println(stepCounter);
        System.out.println(rte2);
        System.out.println(stepCounter); */
    }
    public static void drawMaze(String path){
        int h=1000,w=2000;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(0,w);
        StdDraw.setYscale(0,h);
        StdDraw.setPenRadius(.005);
        try{
            File f=new File(path);
            Scanner s=new Scanner(f);
            StdDraw.setPenColor(StdDraw.BLACK);
            s.nextLine();s.nextLine();
            for(int i=0;s.hasNextLine();i++){ //this is just not how thats supposed to work but ig it works
                String[] hold=s.nextLine().split(" ");
                for(int n=0;n<hold.length;n++){
                    if(hold[n].equals("0")) StdDraw.circle(n*10+100, h-i*10-60, 5);
                }
            }
            StdDraw.show();
            s.close();
        }catch(Exception e){}
    }
    public static void mazeVisualizer(Route r){
        Object[] a=r.toArray();
        StdDraw.setPenColor(StdDraw.RED);
        for(Object o:a){
            StdDraw.circle(((City)o).getp1()*10+100,1000-((City)o).getp2()*10-60,5);
            StdDraw.show(100);
        }
        
    }
    public static void mapVisualizer(Route r){
        Route rte=new Route(r);
        StdDraw.setPenColor(StdDraw.BLACK);
        while(rte.size()>1){
            StdDraw.line(rte.peek().getp1(),rte.pop().getp2(),rte.peek().getp1(),rte.peek().getp2());
        }
        StdDraw.setPenColor(StdDraw.RED);
        while(rte.size()>1){
            StdDraw.line(rte.peek().getp1(),rte.pop().getp2(),rte.peek().getp1(),rte.peek().getp2());
        }
        StdDraw.show(500);
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
                        rtn.get(r).addRelated(new Neighbor(rtn.get(w), Integer.valueOf(h[i+1])));
                    }
                }catch(Exception e){e.printStackTrace();}
            }
            sc.close();
        }catch(Exception e){e.printStackTrace();}
        return rtn;
    }
    private static int[] mazeDimensions=new int[2];
    /**
     * reads text file in maze standard and returns list of "cities" as arraylist
     * @param path
     * @return
     */
    public static ArrayList<City> readMazeData(String path){
        ArrayList<City> rtn=new ArrayList<City>();
        try{
            File f=new File(path);
            Scanner s=new Scanner(f);
            //if(!s.nextLine().equals("maze")) throw new Exception("FileTypeException");
            //s.nextLine();
            String[] holdVal;
            String type=s.nextLine();
            String[] bounds=s.nextLine().split(" ");
            mazeDimensions[0]=Integer.parseInt(bounds[0]);
            mazeDimensions[1]=Integer.parseInt(bounds[1]);
            int num=0;
            while(s.hasNextLine()){
                holdVal=s.nextLine().split(" ");
                for(int i=0;i<holdVal.length;i++){
                    if(holdVal[i].equals("1")) rtn.add(new City(i+","+num,i,num));
                }
                num++;
            }
            s.close();
            //declare bounds (probably unnecessary tbh)
            //loop through declared "cities" and find out if there's a neighboring city (only 4 possible neighbors, vert/horizontal)
            for(int i=0;i<rtn.size();i++){
                int pnt[]=new int[]{rtn.get(i).getp1(),rtn.get(i).getp2()};
                for(int n=0;n<rtn.size();n++){
                    if(rtn.get(n).getp1()==pnt[0]+1&&rtn.get(n).getp2()==pnt[1]||rtn.get(n).getp1()==pnt[0]-1&&rtn.get(n).getp2()==pnt[1]||rtn.get(n).getp1()==pnt[0]&&rtn.get(n).getp2()==pnt[1]+1||rtn.get(n).getp1()==pnt[0]&&rtn.get(n).getp2()==pnt[1]-1){
                        rtn.get(i).addRelated(new Neighbor(rtn.get(n), 1));
                    }
                }
            }
            
        }
        catch(Exception e){e.printStackTrace();};
        return rtn;
    }
    /*
    public ArrayList<City> readTerrain(String path){
        try{
            File f=new File(path);
            Scanner s=new Scanner(f);
            Object[][] hold;
            s.nextLine();
            String[] holdDim=s.nextLine().split(" ");
            hold=new Object[Integer.parseInt(holdDim[0])][Integer.parseInt(holdDim[1])];
            for(int i=0;s.hasNext();i++){ //set 2D array
                hold[i]=s.nextLine().split(" ");
                for(int n=0;n<hold[i].length;n++) hold[i][n]=new City((String)hold[i][n],i,n);
            }
            for(int i=0;i<hold.length;i++){
                for(int n=0;n<hold[i].length;n++)
            }
        }catch(Exception e){};
    }*/
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
    public static Route uniformCost(ArrayList<City> cities, City start, City end){
        stepCounter=0;
        ArrayList<City> explored=new ArrayList<>();
        PriorityQueue<Route> frontier=new PriorityQueue<>();
        frontier.add(new Route(start));
        explored.add(start);
        PriorityQueue<Route> possible=new PriorityQueue<>();
        while(frontier.size()>0){
           stepCounter++;
                Neighbor node=frontier.peek().getNextN();
                while(node==null){
                    try{throw new ArrayIndexOutOfBoundsException();} catch(Exception e){}
                    frontier.remove();
                    if(frontier.isEmpty()) break;
                    node=frontier.peek().getNextN();
                }
                if(!frontier.peek().contains(node.getCity())){
                    Route rte=new Route(frontier.peek(),node);
                    if(node.getCity().equals(end)) return rte;//possible.add(rte);
                    else{
                        if(!explored.contains(node.getCity())){
                            explored.add(node.getCity());
                            frontier.add(rte);
                        }
                        else optimizeRoutes(frontier, rte);
                    }
                }
            if(frontier.peek().outOfNeighbors()) frontier.remove();
        }
        return possible.peek();
    }
    public static void optimizeRoutes(PriorityQueue<Route> r, Route rte){
        Object[] com=r.toArray();
        for(int i=0;i<com.length;i++){
            if(((Route)com[i]).distance()>rte.distance()&&((Route)com[i]).peek().equals(rte.peek())){
                r.remove((Route)com[i]);
            }
        }
        r.add(rte);
    }

    public static Route aStarSearch(ArrayList<City> cities, City start, City end){
        stepCounter=0;
        ArrayList<City> explored=new ArrayList<>();
        PriorityQueue<Route> frontier=new PriorityQueue<>();
        frontier.add(new Route(start, end));
        explored.add(start);
        PriorityQueue<Route> possible=new PriorityQueue<>();
        while(frontier.size()>0){
            stepCounter++;
            for(int p=0;p<frontier.peek().peek().relSize();p++){
                Neighbor node=frontier.peek().nextStep();///////////////
                if(!frontier.peek().contains(node.getCity())){
                    Route rte=new Route(frontier.peek(),node,end);
                    //visualizer(rte);
                    if(node.getCity().equals(end)) possible.add(rte);
                    else{
                        if(!explored.contains(node.getCity())){
                            explored.add(node.getCity());
                            frontier.add(rte);
                        }
                        else optimizeRoutes(frontier, rte);
                    }
                }
            }
            frontier.remove();
        }
        if(possible.size()==0)return null;
        return possible.peek();
    }
    
}


