package teste;

public class Pos {
    private float x, y;
    public Pos(float x, float y){
        this.x = x; 
        this.y = y;
    }

    public void moveX(float d){
        this.x += d;
    }
    public void moveY(double d){
        this.y += d;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
