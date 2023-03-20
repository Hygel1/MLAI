import java.io.File;
import java.util.Scanner;

public class Tester_00 {
    public static void main(String[] args){
        Tester_00 tester=new Tester_00();
        String path="Assignment00_LibraryTest/data.txt";
        tester.readAndPrintData(path);
        tester.testDrawing();
        tester.playAudio();
        
    }
    public void readAndPrintData(String path){
        try{
            File file=new File(path);
            Scanner input=new Scanner(file);
            while(input.hasNext()){
                String line=input.nextLine();
                System.out.println(line);
            }
            input.close();
        }
        catch(Exception e){}
    }
    public void testDrawing(){
        int w=400,h=400;
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(0,w);
        StdDraw.setYscale(0,h);
        double[][] points={{100,100},{100,200},{200,200}};
        for(int i=1;i<points.length;i++){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(points[i-1][0], points[i-1][1], 5);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(points[i-1][0],points[i-1][1],points[i][0],points[i][1]);
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(points[points.length-1][0],points[points.length-1][1],5);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(points[0][0],points[0][1],points[points.length-1][0],points[points.length-1][1]);
        StdDraw.picture(100, 100, "Assignment00_LibraryTest/thumb.jpg", 100, 100);
    }
    public void playAudio(){
        StdAudio.play("Assignment00_LibraryTest/storm.wav");
    }
}
