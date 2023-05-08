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
    }
    public static void readDataDef(){
        try {
            // prepare to read the file
            File inFile = new File(INPUT_FILE_NAME);
            FileReader fr = new FileReader(inFile);
            BufferedReader br = new BufferedReader(fr);
            String inLine = null;
            
            // get the number of test cases
            int T = Integer.parseInt(br.readLine());
            T=Integer.parseInt(br.readLine().split(" ")[0]);
            
            // loop through test cases
            for (int i = 0; i < T; i++) {
                //Read next line as String
                inLine = br.readLine();
                // Print the Line    
                System.out.println(inLine);
            }
            // clean up
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            for(int i=0;i<numVals;i++){
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
}