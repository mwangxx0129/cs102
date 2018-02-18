//public class Windows {
//
//}
import java.util.*;
class Windows {
    public static void main(String[] args) {
//        SolutionInterleavingString test = new SolutionInterleavingString();
        String s = "cbaebabacd";
        String p = "abc";

        findAnagrams(s, p);
    }
    public static List<Integer> findAnagrams(String s, String p) {
        int count = 0;
        int[] map = new int[256];

        int n = p.length();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++ i) {
            if (map[p.charAt(i)]++ == 0) ++ count;
        }
        for (int i = 0; i < n; ++ i) {
            if (map[s.charAt(i)]-- == 1) -- count;
        }
        if (count == 0)
            res.add(0);

        for (int i = 1; i + n - 1 < s.length(); ++ i) {
            char left = s.charAt(i - 1);
            char right = s.charAt(i + n - 1);
            if (map[left]++ == 0)
                ++ count;
            if (map[right]-- == 1)
                -- count;
            System.out.println(i + "#" + s.charAt(i + n - 1) + "#" + count);
            if (count == 0) res.add(i);
        }
        return res;
    }
}
