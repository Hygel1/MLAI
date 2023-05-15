import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// Example for Reading from File
// T will store number of test cases

public class KNNReader
{
    public static String INPUT_FILE_NAME = "Assignment05_KNN\\data\\fake_bills.csv";
    
    public static void main(String[] args) {
        System.out.println(classifyBillData(INPUT_FILE_NAME, 5)*100+"%");
    }
    /**
     * 
     * @param path file to be read and tested
     * @return percent correct classifications
     */
    public static double classifyBillData(String path, int k){
        ArrayList<KNNRecord> test=new ArrayList<>();
        ArrayList<KNNRecord> train=new ArrayList<>();
        try{ //read and parse data
            
            File inFile = new File(path);
            Scanner s=new Scanner(inFile);
            //int c=0;
            s.nextLine(); //skip first line
            while(s.hasNextLine()){
                //c++;
                train.add(new KNNRecord(s.nextLine().split(";"))); //split values by semicolon
            }
            for(int i=0;i<100;i++){
                test.add(train.remove((int)(Math.random()*train.size()))); //add random 100 to testing set
            }
            s.close();
        }
        catch(Exception e){e.printStackTrace();}
        KNNClassifier classifier=new KNNClassifier(train);
        int c=0,w=0; //c=correct, w=wrong
        for(int i=0;i<test.size();i++){
            if(classifier.knnClassify(test.get(i).getValues(), k).equals(test.get(i).getClassification())) c++;
            else w++;
        }
        return (double)c/(c+w);
    }
    public static void testBirdData(String path){
        ArrayList<KNNClassifier> trainData=readBirdData(path);
        ArrayList<ArrayList<ArrayList<Double>>> testData=getTestDataBird(path);
        for(int i=0;i<trainData.size();i++){
            for(int n=0;n<testData.get(i).size();n++){
                System.out.println(trainData.get(i).knnClassify(testData.get(i).get(n), 7));
            }
        }
    }
    public static ArrayList<KNNClassifier> readBirdData(String path){
        ArrayList<KNNClassifier> rtn=new ArrayList<>();
        try{
            File inFile = new File(path);
            FileReader fr = new FileReader(inFile);
            BufferedReader br = new BufferedReader(fr);
            Integer numVals=Integer.parseInt(br.readLine());
            for(int q=0;q<numVals;q++){
                ArrayList<KNNRecord> attr=new ArrayList<>();
                String[] h=br.readLine().split(" ");
                int[] top=new int[2];
                top[0]=Integer.parseInt(h[0]);
                top[1]=Integer.parseInt(h[1]);
                for(int i=0;i<top[0];i++){
                    attr.add(new KNNRecord(br.readLine().split(" ")));
                }
                for(int i=0;i<top[1];i++) br.readLine();
                rtn.add(new KNNClassifier(attr));
            }
            fr.close();
            br.close();
        }
        catch(Exception e){
            System.out.println("oopsie in reader");
            e.printStackTrace();
            return null;
        }
        return rtn;
    }
    public static ArrayList<ArrayList<ArrayList<Double>>> getTestDataBird(String path){
        ArrayList<ArrayList<ArrayList<Double>>> rtn=new ArrayList<>();
        File inFile=new File(path);
        try{
            FileReader fR=new FileReader(inFile);
            BufferedReader bR=new BufferedReader(fR);
            int numVals=Integer.parseInt(bR.readLine()); //skip first line
            for(int q=0;q<numVals;q++){
                ArrayList<ArrayList<Double>> hold=new ArrayList<>();
                String[] strs=bR.readLine().split(" "); //read number of data types
                int h=Integer.parseInt(strs[0]); //number of training data sets
                int t=Integer.parseInt(strs[1]); //number of testing data sets
                for(int i=0;i<h;i++) bR.readLine(); //skip past training data to get to testing data
                for(int i=0;i<t;i++){
                    ArrayList<Double> newVals=new ArrayList<>();
                    String[] vals=bR.readLine().split(" ");
                    for(int n=0;n<vals.length;n++) newVals.add(Double.parseDouble(vals[n]));
                    hold.add(newVals);
                }
                rtn.add(hold);
            }
            bR.close();
        }
        catch(Exception e){System.out.println("prblem in getTestData()");e.printStackTrace();}
        return rtn;
    }
}