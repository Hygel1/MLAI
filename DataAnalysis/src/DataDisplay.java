
import java.util.ArrayList;

public class DataDisplay {
    public void displayOneD(ArrayList<Double> data){
        int h=800;
        int w=1000;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(0,w);
        StdDraw.setYscale(-1,1);
        for(int i=0;i<100;i++){
            double x=(double)i*10;
            double y=data.get(i);
            StdDraw.setPenColor(0,0,255);
            StdDraw.filledCircle(x,y,.5);
        }
        StdDraw.show(); //show created visual
        for(double i:data){ //play the audio from each point
            StdAudio.play(i);
            
        }
    }
}
