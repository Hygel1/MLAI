import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.io.File;
import java.util.Scanner;
public class RouteFinder {
    static int stepCounter=0;
    public static void main(String[] args){
        //ArrayList<City> data=readMapData("Assignment02_DataAnalysis\\MysteryData.txt");
        //MAZE DATA TESTING!!!
        /*ArrayList<City> mazeData=readMazeData("Assignment03_BreadthFirstSearch\\dataFiles\\maze03-small.txt");
        ArrayList<City> mazeData1=readMazeData("Assignment03_BreadthFirstSearch\\dataFiles\\maze04-small.txt");
        ArrayList<City> mazeData2=readMazeData("Assignment03_BreadthFirstSearch\\dataFiles\\maze05-medium.txt");
        ArrayList<City> mazeData3=readMazeData("Assignment03_BreadthFirstSearch\\dataFiles\\maze06-medium.txt");
        ArrayList<City> mazeData4=readMazeData("Assignment03_BreadthFirstSearch\\dataFiles\\maze07-large.txt");
        ArrayList<City> mazeData5=readMazeData("Assignment03_BreadthFirstSearch\\dataFiles\\maze08-large.txt");
        Route rteB=findPathBreadthFirst(mazeData, mazeData.get(0),mazeData.get(mazeData.size()-1));
        Route rte=uniformCost(mazeData, mazeData.get(0), mazeData.get(mazeData.size()-1));
        Route rte2=aStarSearch(mazeData, mazeData.get(0), mazeData.get(mazeData.size()-1));
        
        Route rteB1=findPathBreadthFirst(mazeData1, mazeData1.get(0),mazeData1.get(mazeData1.size()-1));
        Route rte1=uniformCost(mazeData1, mazeData1.get(0), mazeData1.get(mazeData1.size()-1));
        Route rte21=aStarSearch(mazeData1, mazeData1.get(0), mazeData1.get(mazeData1.size()-1));
        
        Route rteB2=findPathBreadthFirst(mazeData2, mazeData2.get(0),mazeData2.get(mazeData2.size()-1));
        Route rte112=uniformCost(mazeData2, mazeData2.get(0), mazeData2.get(mazeData2.size()-1));
        Route rte22=aStarSearch(mazeData2, mazeData2.get(0), mazeData2.get(mazeData2.size()-1));

        Route rteB3=findPathBreadthFirst(mazeData3, mazeData3.get(0),mazeData3.get(mazeData3.size()-1));
        Route rte3=uniformCost(mazeData3, mazeData3.get(0), mazeData3.get(mazeData3.size()-1));
        Route rte23=aStarSearch(mazeData3, mazeData3.get(0), mazeData3.get(mazeData3.size()-1));

        Route rteB4=findPathBreadthFirst(mazeData4, mazeData4.get(0),mazeData4.get(mazeData4.size()-1));
        Route rte4=uniformCost(mazeData4, mazeData4.get(0), mazeData4.get(mazeData4.size()-1));
        Route rte24=aStarSearch(mazeData4, mazeData4.get(0), mazeData4.get(mazeData4.size()-1));

        Route rteB5=findPathBreadthFirst(mazeData5, mazeData5.get(0),mazeData5.get(mazeData5.size()-1));
        Route rte5=uniformCost(mazeData5, mazeData5.get(0), mazeData5.get(mazeData5.size()-1));
        Route rte25=aStarSearch(mazeData5, mazeData5.get(0), mazeData5.get(mazeData5.size()-1));*/ 
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
    public static void mapVisualizer(Route rte){
        StdDraw.setPenColor(StdDraw.BLACK);
        while(rte.size()>1){
            StdDraw.line(rte.peek().getp1(),rte.pop().getp2(),rte.peek().getp1(),rte.peek().getp2());
        }
        StdDraw.show();
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
    } /*
    public static ArrayList<HeightPoint> readTerrainMapData(String path){
        ArrayList<ArrayList<HeightPoint>> rtn2D=new ArrayList<>();
        try{
            File f=new File(path);
            Scanner s=new Scanner(f);
            for(int i=0;s.hasNextLine();i++){
                rtn2D.add(new ArrayList<>());
                String[] hold=s.nextLine().split(" ");
                for(int n=0;n<hold.length;n++){
                    rtn2D.get(i).add(new HeightPoint(n, i, Integer.valueOf(hold[n])));
                }
            }
        }catch(Exception e){e.printStackTrace();}
        ArrayList<HeightPoint> rtn=new ArrayList<>(rtn2D.size()*rtn2D.get(0).size());
        for(int i=0;i<rtn2D.size();i++){
            for(int n=0;n<rtn2D.get(i).size();n++){
                rtn.add(rtn2D.get(i).get(n));
                if(i-1>-1&&n>-1&&i<rtn2D.size()&&n<rtn2D.get(i).size()) rtn.get(rtn.size()-1).addRelated(new Neighbor(rtn2D.get(i-1).get(n), Math.abs(rtn2D.get(i-1).get(n).getHeight()-rtn2D.get(i).get(n).getHeight())));
                if(i+1>-1&&n>-1&&i<rtn2D.size()&&n<rtn2D.get(i).size()) rtn.get(rtn.size()-1).addRelated(new Neighbor(rtn2D.get(i+1).get(n), Math.abs(rtn2D.get(i+1).get(n).getHeight()-rtn2D.get(i).get(n).getHeight())));
                if(i>-1&&n-1>-1&&i<rtn2D.size()&&n<rtn2D.get(i).size()) rtn.get(rtn.size()-1).addRelated(new Neighbor(rtn2D.get(i).get(n-1), Math.abs(rtn2D.get(i).get(n-1).getHeight()-rtn2D.get(i).get(n).getHeight())));
                if(i>-1&&n+1>-1&&i<rtn2D.size()&&n<rtn2D.get(i).size()) rtn.get(rtn.size()-1).addRelated(new Neighbor(rtn2D.get(i).get(n+1), Math.abs(rtn2D.get(i).get(n+1).getHeight()-rtn2D.get(i).get(n).getHeight())));
            }
        }
        return rtn;
    } */
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
            //String type=s.nextLine();
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
    /**
     * takes an ArrayList of cites, one start city, and one end city; finds the shortest path by node count
     * @param cities cities to choose from
     * @param start city to start at
     * @param end city to end at
     * @return shortest path (by node count) from start to end
     */
    public static Route findPathBreadthFirst(ArrayList<? extends Point> cities, Point start, Point end){
        long time=System.currentTimeMillis();
        int stepCounter=0;
        if(start.equals(end)) return null;
        ArrayList<Point> visited=new ArrayList<>();
        visited.add(start);
        ArrayList<ArrayList<Point>> frontier=new ArrayList<>();
        for(int i=0;i<start.relSize();i++){
            ArrayList<Point> hold=new ArrayList<>();hold.add(start);hold.add(start.getRel(i));
            frontier.add(hold);
        }
        if(frontier.size()==0) return null;//if frontier starts empty, start city has no related cities, return null (failed)
        while(0<frontier.size()){
            for(int i=0;i<frontier.get(0).get(frontier.get(0).size()-1).relSize();i++){ //for every rel city of the last city of the first direction in frontier...
                stepCounter++;
                if(!visited.contains(frontier.get(0).get(frontier.get(0).size()-1).getRel(i))){ //if the city hasn't been visited yet...       
                    visited.add(frontier.get(0).get(frontier.get(0).size()-1).getRel(i)); //add the current city to the list of visited cities
                    frontier.add(new ArrayList<>(frontier.get(0)));frontier.get(frontier.size()-1).add(frontier.get(0).get(frontier.get(0).size()-1).getRel(i)); //append frontier with a new direction including the unvisited rel city                   
                    if(frontier.get(frontier.size()-1).get(frontier.get(frontier.size()-1).size()-1).equals(end)) {System.out.println("BF:"+stepCounter+", "+((double)(System.currentTimeMillis()-time)));return new Route(frontier.get(frontier.size()-1));} //if the last checked city is the final destination, return the last made direction set
                }
            }
            frontier.remove(0);
        }
        return null; //if final destination wasn't found, return null (failed)
    }
    public static Route uniformCost(ArrayList<? extends Point> cities, Point start, Point end){
        long time=System.currentTimeMillis();
        stepCounter=0;
        ArrayList<Point> explored=new ArrayList<>();
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
                if(!frontier.peek().contains(node.getPoint())){
                    Route rte=new Route(frontier.peek(),node);
                    if(node.getPoint().equals(end)) possible.add(rte);
                    else{
                        if(!explored.contains(node.getPoint())){
                            explored.add(node.getPoint());
                            frontier.add(rte);
                        }
                        else optimizeRoutes(frontier, rte);
                    }
                }
            if(frontier.peek().outOfNeighbors()) frontier.remove();
        }
        System.out.println("UC: "+stepCounter+", "+(System.currentTimeMillis()-time)/1000.0);
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

    public static Route aStarSearch(ArrayList<? extends Point> cities, Point start, Point end){
        stepCounter=0;
        ArrayList<Point> explored=new ArrayList<>();
        PriorityQueue<Route> frontier=new PriorityQueue<>();
        frontier.add(new Route(start, end));
        explored.add(start);
        PriorityQueue<Route> possible=new PriorityQueue<>();
        while(frontier.size()>0){
            stepCounter++;
            for(int p=0;p<frontier.peek().peek().relSize();p++){
                Neighbor node=frontier.peek().nextStep();
                if(!frontier.peek().contains(node.getPoint())){
                    Route rte=new Route(frontier.peek(),node,end);
                    if(node.getPoint().equals(end)) possible.add(rte);
                    else{
                        if(!explored.contains(node.getPoint())){
                            explored.add(node.getPoint());
                            frontier.add(rte);
                        }
                        else optimizeRoutes(frontier, rte);
                    }
                }
            }
            frontier.remove();
        }
        if(possible.size()==0)return null;
        System.out.println("A*: "+stepCounter);
        return possible.peek();
    }
    public class uniComp implements Comparator<Route>{
        public int compare(Route o1, Route o2) {
            if(o2.distance()-o2.distance()==0) return 0;
            return o1.distance()-o2.distance()>0?1:-1;
        }
    }
}


