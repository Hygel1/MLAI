import java.util.ArrayList;
public class Workspace{
    public static void main(String[] args){
    DataReader reader=new DataReader();
    DataDisplay display=new DataDisplay();
    String[] path={"DataAnalysis\\OneDData_02_long.txt","DataAnalysis\\TwoDData_02_long.txt","DataAnalysis\\ThreeDData_02.txt","DataAnalysis\\MysteryData.txt"};
    ArrayList<City> data=reader.readMysteryData(path[3]);
    display.displayMystery(data);
    }
}
