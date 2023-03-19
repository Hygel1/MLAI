import java.util.ArrayList;
public class Workspace{
    public static void main(String[] args){
    DataReader reader=new DataReader();
    DataDisplay display=new DataDisplay();
    String path="DataAnalysis\\MysteryData.txt";
    ArrayList<City> data=reader.readMysteryData(path);
    display.displayMystery(data);
    }
}
