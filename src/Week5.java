import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Week5 {
    public static void main(String[] args) {
//        LRUCache lru = new LRUCache(2);
        LinkedHashMap<Integer, Integer> lru = new LinkedHashMap<>();

        lru.put(1,1);
        lru.put(2,2);
        System.out.println(lru.get(1));
        lru.put(3,3);
        System.out.println(lru.get(2));

        lru.put(4,4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));
    }

    public static void fly(String s) {
        String[] commands = s.split(" ");
        int x = 0, y = 0;
        for (String command : commands) {
            if (command.equals("N")) {
                y += 1;
            } else if (command.equals("S")) {
                y -= 1;
            } else if (command.equals("W")) {
                x -= 1;
            } else if (command.equals("E")) {
                x += 1;
            }
            System.out.println("(" + x +"," + y + ")");
        }
        // distance
        System.out.println(Math.sqrt(x * x + y * y));
    }



    public static void cubeSumIII(int n) {
        class Point {
            int x, y;
            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        Map<Integer, Point> map = new HashMap<>();
        for (int i = 1; i < n; ++ i) {
            for (int j = i + 1; j < n; ++ j) {
                int sum = i * i * i + j * j * j;
                if (sum > n) break;
                map.put(sum, new Point(Math.min(i, j), Math.max(i, j)));

            }
        }

        for (int k = 1; k < n; ++ k) {
            for (int p = k + 1; p < n; ++ p) {
                int tmp = k * k * k + p * p * p;
                if(map.containsKey(tmp)) {
                    Point cur = map.get(tmp);
                    if (cur.x == Math.min(k, p)) continue;
                    else {
                        System.out.println(tmp + ",(" + cur.x +"," + cur.y + ")" + "(" + k + "," + p + ")");
                    }
                }

            }
        }
    }

    public static void cubeSum(int n) {
        for (int i = 1; i < n; ++ i) {
            for (int j = i + 1; j < n; ++ j) {
                int sum = i * i * i + j * j * j;
                if (sum > n) break;

                for (int k = 1; k < n; ++ k) {
                    for (int p = k + 1; p < n; ++ p) {
                        int tmp = k * k * k + p * p * p;
                        if (tmp == sum) {
                            if (Math.min(i,j) == Math.min(k, p)) continue;
                            System.out.println(sum + ",(" + i +"," + j + ")" + "(" + k + "," + p + ")");
                        }
                    }
                }
            }
        }
    }

}
