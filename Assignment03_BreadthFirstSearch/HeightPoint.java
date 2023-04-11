import java.util.ArrayList;
public class HeightPoint extends Point{
    double height;
    public HeightPoint(int x, int y, double height){
        super(x,y);
        this.height=height;
    }
    public double getHeight(){
        return height;
    }
}
