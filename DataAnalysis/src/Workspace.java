
import java.util.ArrayList;
public class Workspace {
    public static void main(String[] args){
    DataReader reader=new DataReader();
    DataDisplay display=new DataDisplay();
    String path="DataAnalysis\\OneDData_02_long.txt";
    ArrayList<Double> data=reader.getOneData(path);
    display.displayOneD(data);
    }
}
