/**
 * Main
 */
public class MainPoint {
    public static void main(String[] args) {
        Point A = new Point();
        Point B = new Point(2,7, "Bandung");
        Point C = new Point(-3, -4, "Jakarta");

        A.printPoint();
        B.printPoint();
        C.printPoint();

        A.setNAME("Surabaya");
        A.setX(5);
        A.setY(-7);
        A.printPoint();


        System.out.println(A.getX());
        System.out.println(A.getY());
        System.out.println(B.getX());
        System.out.println(C.getY());
        System.out.println(A.getNAME());

        System.out.println(A.getEucledianDistance(B));
        System.out.println(B.getEucledianDistance(A));
        System.out.println(C.getEucledianDistance(A));
    }
}