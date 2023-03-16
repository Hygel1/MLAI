import java.util.ArrayList;
public class Workspace{
    public static void main(String[] args){
    DataReader reader=new DataReader();
    DataDisplay display=new DataDisplay();
    String path="DataAnalysis\\ThreeDData_02.txt";
    ArrayList<double[]> data=reader.read3D(path);
    display.display3D(data);
    }
}
