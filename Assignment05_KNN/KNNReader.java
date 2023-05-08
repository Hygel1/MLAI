import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

// Example for Reading from File
// T will store number of test cases

public class KNNReader
{
    public static String INPUT_FILE_NAME = "Assignment05_KNN\\data\\Prob20Large.txt";
    
    public static void main(String[] args) {
        ArrayList<KNNRecord> data=readData(INPUT_FILE_NAME);
        for(KNNRecord a:data) System.out.println(a);
        KNNClassifier classifier=new KNNClassifier(data);
        ArrayList<ArrayList<Double>> testData=getTestData();
        for(ArrayList<Double> d:testData) System.out.println(classifier.knnClassify(d, 5));
    }
    public static ArrayList<KNNRecord> readData(String path){
        ArrayList<KNNRecord> attr=new ArrayList<>();
        try{
            File inFile = new File(path);
            FileReader fr = new FileReader(inFile);
            BufferedReader br = new BufferedReader(fr);
            Integer numVals=Integer.parseInt(br.readLine());
            String[] h=br.readLine().split(" ");
            int[] top=new int[2];
            top[0]=Integer.parseInt(h[0]);top[1]=Integer.parseInt(h[1]);

            for(int i=0;i<top[0];i++){
                attr.add(new KNNRecord(br.readLine().split(" ")));
            }
            fr.close();
            br.close();
        }
        catch(Exception e){
            System.out.println("oopsie in reader");
            e.printStackTrace();
            return null;
        }
        return attr;
    }
    public static ArrayList<ArrayList<Double>> getTestData(){
        ArrayList<ArrayList<Double>> rtn=new ArrayList<>();
        File inFile=new File(INPUT_FILE_NAME);
        try{
            FileReader fR=new FileReader(inFile);
            BufferedReader bR=new BufferedReader(fR);
            bR.readLine(); //skip first line
            String[] strs=bR.readLine().split(" "); //read number of data types
            int h=Integer.parseInt(strs[0]); //number of training data sets
            int t=Integer.parseInt(strs[1]); //number of testing data sets
            for(int i=0;i<h;i++) bR.readLine(); //skip past training data to get to testing data
            for(int i=0;i<t;i++){
                ArrayList<Double> newVals=new ArrayList<>();
                String[] vals=bR.readLine().split(" ");
                for(int n=0;n<vals.length;n++) newVals.add(Double.parseDouble(vals[n]));
                rtn.add(newVals);
            }
            bR.close();
        }
        catch(Exception e){System.out.println("prblem in getTestData()");e.printStackTrace();}
        return rtn;
    }
}