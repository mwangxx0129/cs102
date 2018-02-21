import java.io.*;
import java.util.*;

public class AmazonSolutions {
    public static void main(String[] args) {
        System.out.println("hello");
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
