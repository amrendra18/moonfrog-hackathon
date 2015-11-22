package in.titans.funwithdots.utils;

public class PointPx{
    float X, Y;

    public PointPx(){

    }

    public PointPx(float x, float y){
        this.X = x;
        this.Y = y;
    }

    public void setX(float x){
        this.X = x;
    }

    public void setY(float y){
        this.Y = y;
    }

    public float getX(){
        return X;
    }

    public float getY(){
        return Y;
    }


    @Override
    public String toString() {
        return "Point("+X+","+Y+")";
    }
}
