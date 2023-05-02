import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// Example for Reading from File
// T will store number of test cases

public class KNNReader
{
    public static String INPUT_FILE_NAME = "Assignment05_KNN\\data\\Prob20Large.txt";
    
    public static void main(String[] args) {
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
                // Get the Line
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
}