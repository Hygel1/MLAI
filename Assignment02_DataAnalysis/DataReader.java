import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    public ArrayList<Double> read1D(String path){
        ArrayList<Double> output=new ArrayList<>();
        try{
            File file=new File(path);
            Scanner input=new Scanner(file);
            while(input.hasNext()){ //intake data points and load into arraylist
                double v=Double.valueOf(input.nextLine());
                output.add(v);
            }
        input.close();
        }
        catch(Exception e){e.printStackTrace();}
        return output;
    }
    public ArrayList<City> readMysteryData(String path){
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
    public ArrayList<double[]> read2D(String path){
        ArrayList<double[]> rtn=new ArrayList<>();
        try{
            File file=new File(path);
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                String hold[]=sc.nextLine().split(", ");
                rtn.add(new double[] {Double.valueOf(hold[0]),Double.valueOf(hold[1])});
            }
            sc.close();
        }catch(Exception e){e.printStackTrace();}
        return rtn;
    }
    public ArrayList<double[]> read3D(String path){
        ArrayList<double[]> rtn=new ArrayList<>();
        try{
            File file=new File(path);
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                String hold[]=sc.nextLine().split(", ");
                rtn.add(new double[] {Double.valueOf(hold[0]),Double.valueOf(hold[1]),Double.valueOf(hold[2])});
            }
            sc.close();
        }catch(Exception e){e.printStackTrace();}
        return rtn;
    }
}
