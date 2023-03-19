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
    public void displayMystery(ArrayList<City> data){
        int h=1000,w=1000;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(0,w);
        StdDraw.setYscale(0,h);
        for(int i=0;i<data.size();i++){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(data.get(i).getp1(),data.get(i).getp2(),5);
            StdDraw.text(data.get(i).getp1(),data.get(i).getp2()-10,data.get(i).getName());
        }
        for(int i=0;i<data.size();i++){
            for(int n=0;n<data.get(i).relSize();n++){
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(data.get(i).getp1(), data.get(i).getp2(), data.get(i).getRel(n).getp1(), data.get(i).getRel(n).getp2());
            }
        }
        StdDraw.show();
    }
    public void display2D(ArrayList<double[]> data){
        int h=400,w=1000;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(0,w);
        StdDraw.setYscale(0,h);
        for(int i=0;i<data.size();i++){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(data.get(i)[0], data.get(i)[1], 5);
        }
        StdDraw.show();
    }
    public void display3D(ArrayList<double[]> data){
        int h=200 ,w=400;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(0,w);
        StdDraw.setYscale(0,h);
        for(int i=0;i<data.size();i++){
            StdDraw.setPenColor((int)data.get(i)[2],(int)data.get(i)[2],(int)data.get(i)[2]);
            StdDraw.filledCircle(data.get(i)[1], data.get(i)[0], 5);
        }
        StdDraw.show();
    }
}
