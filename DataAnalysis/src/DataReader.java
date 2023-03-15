
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
}
