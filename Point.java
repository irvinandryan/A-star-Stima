/**
 * Point
 */
public class Point {
    private int X;
    private int Y;

    public Point(){
        this.X = 0;
        this.Y = 0;
    }

    public Point(int x, int y){
        this.X = x;
        this.Y = y;
    }
    
    public void setX(int x){
        this.X = x;
    }

    public void setY(int y){
        this.Y = y;
    }

    public int getX(){
        return this.X;
    }

    public int getY(){
        return this.Y;
    }

    public void printPoint(){
        System.out.print(" (");
        System.out.print(this.X);
        System.out.print(",");
        System.out.print(this.Y);
        System.out.println(")");
    }

    public double getEucledianDistance(Point other){
        return Math.sqrt(Math.pow(this.X - other.X, 2) + Math.pow(this.Y - other.Y, 2));
    }
}