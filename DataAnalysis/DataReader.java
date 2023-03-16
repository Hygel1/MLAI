import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class DataReader {
    public ArrayList<Double> getOneData(String path){
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
    public ArrayList<Point> makePointList(String path){
        ArrayList<Point> rtn=new ArrayList<>();
        try{
            File file=new File(path);
            Scanner sc=new Scanner(file);
            while(sc.hasNext()){
                String[] hold=sc.nextLine().split(", ");
                rtn.add(new Point(hold[0],Double.valueOf(hold[1]),Double.valueOf(hold[2])));
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
