import java.util.ArrayList;
public class Workspace{
    public static void main(String[] args){
    DataReader reader=new DataReader();
    DataDisplay display=new DataDisplay();
    String[] path={"Assignment02_DataAnalysis\\OneDData_02_long.txt","Assignment02_DataAnalysis\\TwoDData_02_long.txt","Assignment02_DataAnalysis\\ThreeDData_02.txt","Assignment02_DataAnalysis\\MysteryData.txt"};
    ArrayList<City> data=reader.readMysteryData(path[3]);
    display.displayMystery(data);
    //ArrayList<Double> data=reader.read1D(path[0]);
    //display.displayOneD(data);
    }
}
