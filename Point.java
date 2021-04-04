/**
 * Point
 */
public class Point {
    private int X;
    private int Y;
    // private String NAME;

    public Point(){
        this.X = 0;
        this.Y = 0;
        // this.NAME = "noName";
    }

    public Point(int x, int y){
        this.X = x;
        this.Y = y;
        // this.NAME = name;
    }
    
    public void setX(int x){
        this.X = x;
    }

    public void setY(int y){
        this.Y = y;
    }

    // public void setNAME(String name){
    //     this.NAME = name;
    // }

    public int getX(){
        return this.X;
    }

    public int getY(){
        return this.Y;
    }

    // public String getNAME(){
    //     return this.NAME;
    // }

    public void printPoint(){
        // System.out.print(this.NAME);
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