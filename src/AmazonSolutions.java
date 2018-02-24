import java.io.*;
import java.util.*;

public class AmazonSolutions {
    public static void main(String[] args) throws Exception {
        System.out.println("hello");
        int[][] input = {
                {0,0,0},
                {0,1,1},
                {0,0,1},
                {1,1,1}
        };
        int resIndex = getMost(input);
        System.out.println(resIndex);
        System.out.println(isAnagramPalindrome("aabba"));
        System.out.println(minEdit("abc","a"));

        Subscriber subscriber = new Subscriber();
        subscriber.subscribe(123, "abc");
        subscriber.subscribe(123, "nba");
        subscriber.subscribe(1, "nba");
        subscriber.subscribe(2, "nba");
        List<Integer> res = subscriber.getSubscribers("nba");
        System.out.println(res);

        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        car.park(parkingLot);
        boolean fee = car.unpark(parkingLot);
    }

    public static int minEdit(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; ++ i) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= n; ++ i) {
            dp[0][i] = dp[0][i - 1] + 1;
        }
        for (int i = 1; i <= m; ++ i) {
            for (int j = 1; j <= n; ++ j) {
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    // Return true if the string is a palindrome or an anagram of palindrome.
    // False otherwise.
    private static boolean isAnagramPalindrome(String s) {
        boolean[] map = new boolean[26];
        char[] sCh = s.toCharArray();
        for (char c : sCh) {
            map[c - 'a'] = !map[c - 'a'];
        }
        int count = 0;
        for (int i = 0; i < 26; ++ i) {
            if (map[i] ) {
                ++ count;
                if (count == 2)
                    return false;
            }
        }
        return true;
    }

    // brute force
    // binary search
    // tricky
    private static int getMost(int[][] input) {
        int lastIndex = input[0].length;
        int maxRow = 0;
        for (int i = 0; i < input.length; ++ i) {
            while (lastIndex - 1 >= 0 && input[i][lastIndex - 1] == 1) {
                -- lastIndex;
                maxRow = i;
            }
        }
        return maxRow;
    }

    private static List<List<String>> groupAnagrams(String []strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : strs) {
            char[] sCh = word.toCharArray();
            Arrays.sort(sCh);
            String key = String.valueOf(sCh);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(word);
        }
        return new ArrayList<>(map.values());
    }
}

interface iSubscriber {
    public void subscribe(Integer userId, String topic);
    public List<Integer> getSubscribers(String topic);
}

class Subscriber implements iSubscriber {
    Map<String, List<Integer>> map = new HashMap<>();
    public void subscribe(Integer userId, String topic){
        if (userId == null || topic == null || topic.length() == 0) {
            // invalid input
            return;
        }
        if (!map.containsKey(topic)) map.put(topic, new ArrayList<Integer>());
        map.get(topic).add(userId);
    }

    public List<Integer> getSubscribers(String topic){
        if (topic == null || topic.length() == 0) {
            // invalid input
            return new ArrayList<>();
        }
        if (!map.containsKey(topic)) return new ArrayList<Integer>();
        return map.get(topic);
    }
}


class ParkingSpace {
    public enum ParkingSpaceType {
        MOTOBIKE, CAR, HANDICAPPED;
    }
    class Meter {
        public int start, end;
    }
    private int id;
    private ParkingSpaceType pSpaceType;
    private Meter meter;
    public ParkingSpace(int id, ParkingSpaceType pSpaceType) {
        this.id = id;
        this.pSpaceType = pSpaceType;
    }
    public int getId () {
        return this.id;
    }
    public void setStart() {
        meter.start = 0;
    }
    public void setEnd() {
        meter.end = 0;
    }

    public ParkingSpaceType getpSpaceType() {
        return pSpaceType;
    }
}

class ParkingLot {
    private List<ParkingSpace> freeRegularSpace;
    private List<ParkingSpace> freeCompactSpace;
    private List<ParkingSpace> freeHandicappedSpace;
    public ParkingLot() {}
    public ParkingSpace allocateFreeSpace(ParkingSpace.ParkingSpaceType parkingSpaceType) throws Exception {
        ParkingSpace pSpace = null;
        switch (parkingSpaceType) {
            case MOTOBIKE:
                if (freeCompactSpace.size() == 0)
                    return null;
                pSpace = freeCompactSpace.remove(0);
                pSpace.setStart();
                break;
            case CAR:
                if (freeRegularSpace.size() == 0)
                    return null;
                pSpace = freeRegularSpace.remove(0);
                pSpace.setStart();
                break;
            case HANDICAPPED:
                if (freeHandicappedSpace.size() == 0)
                    return null;
                pSpace = freeHandicappedSpace.remove(0);
                pSpace.setStart();
                break;
            default:
                throw new Exception("Invalid parking type: the valid type is [MOTOBIKE, CAR, HANDICAPPED]");
        }
        return pSpace;
    }

    public ParkingSpace reclainFreeSpace(ParkingSpace parkingSpace) {
        parkingSpace.setEnd();
        switch (parkingSpace.getpSpaceType()) {
            case MOTOBIKE:
                freeCompactSpace.add(parkingSpace);
                break;
            case CAR:
                freeRegularSpace.add(parkingSpace);
                break;
            case HANDICAPPED:
                freeHandicappedSpace.add(parkingSpace);
                break;
            default:
                break;
        }
        return parkingSpace;
    }
}

abstract class Vehicle {
    protected ParkingSpace parkingSpace;
    public abstract boolean park(ParkingLot parkingLot) throws Exception;
    public boolean unpark(ParkingLot parkingLot) {
        if (parkingLot == null) return false;

        parkingLot.reclainFreeSpace(parkingSpace);
        return true;
    }
}

class Motor extends Vehicle {
    @Override
    public boolean park(ParkingLot parkingLot) throws Exception {
        if (parkingLot.allocateFreeSpace(ParkingSpace.ParkingSpaceType.MOTOBIKE) == null)
            return  false;
        return true;
    }
}
class Car extends Vehicle {
    @Override
    public boolean park(ParkingLot parkingLot) throws Exception {
        if (parkingLot.allocateFreeSpace(ParkingSpace.ParkingSpaceType.CAR) == null)
            return false;
        return true;
    }
}

class HandicappedCars extends Vehicle {
    @Override
    public boolean park(ParkingLot parkingLot) throws Exception {
      if (parkingLot.allocateFreeSpace(ParkingSpace.ParkingSpaceType.HANDICAPPED) == null)
          return false;
      return true;
    }
}