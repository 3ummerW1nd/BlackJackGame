import java.util.ArrayList;
import java.util.List;

public enum Point {
    A, 二, 三, 四, 五, 六, 七,
    八, 九, 十, J, Q, K;

    public static List<Point> getAllPoints() {
        List<Point> points = new ArrayList<>();
        points.add(A);
        points.add(二);
        points.add(三);
        points.add(四);
        points.add(五);
        points.add(六);
        points.add(七);
        points.add(八);
        points.add(九);
        points.add(十);
        points.add(J);
        points.add(Q);
        points.add(K);
        return points;
    }

    public int getValue() {
        switch (this) {
            case 二:
                return 2;
            case 三:
                return 3;
            case 四:
                return 4;
            case 五:
                return 5;
            case 六:
                return 6;
            case 七:
                return 7;
            case 八:
                return 8;
            case 九:
                return 9;
            case 十:
            case J:
            case Q:
            case K:
                return 10;
        }
        return 0;
    }

}
