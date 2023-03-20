import java.util.ArrayList;
public class DataDisplay {
    public void displayOneD(ArrayList<Double> data){
        int h=800,w=2000;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(0,w);
        StdDraw.setYscale(-1,1);

        for(int i=0;i<data.size();i++){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(i*10, data.get(i), .5);;
        }
        StdDraw.show();
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
            StdDraw.text(data.get(i).getp1(),data.get(i).getp2()-10,data.get(i).getName()); //draw point for each city
            for(int n=0;n<data.get(i).relSize();n++){ //draw line to each related city
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(data.get(i).getp1(), data.get(i).getp2(), data.get(i).getRel(n).getp1(), data.get(i).getRel(n).getp2());
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text((data.get(i).getp1()+data.get(i).getRel(n).getp1())/2,(data.get(i).getp2()+data.get(i).getRel(n).getp2())/2,data.get(i).getRelatedDistance(n)+"");
            }
        }
        StdDraw.show();
    }
    public void display2D(ArrayList<double[]> data){
        int h=1000,w=1000;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(w,h);
        StdDraw.setXscale(0,w);
        StdDraw.setYscale(0,h);
        for(int i=0;i<data.size();i++){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(data.get(i)[0], data.get(i)[1], 2);
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
