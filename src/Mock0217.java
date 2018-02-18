
// package whatever; // don't place package name!

import java.io.*;
import java.util.*;

class Mock0217 {
    public static void main (String[] args) {
        System.out.println("Hello Java");
//     String s = "abacbadeec";
//     int k = 4;
//     List<String> res = kDist(s, k);
//     for (String e : res) {
//       System.out.println(e);
//     }

        String[] t = {"2abc","bcd","cab"};
        String[] a = {"dbc", "aef", "2abc", "cab", "xyz","bcd", "bcd"};
        List<String> T = new ArrayList<>();
        for (String e : t) {
            T.add(e);
        }

        List<String> A = new ArrayList<>();
        for (String e : a) {
            A.add(e);
        }


        List<Integer> res = subSequenceTags(T, A);
        System.out.println(res.toString());
    }

    public static List<String> kDist(String s, int k) {
        // corner case
        // TODO

        int[] map = new int[256];
        int count = 0;
        Set<String> res = new HashSet<>();

        // init k
        for (int i = 0; i < k; ++ i) {
            if (map[s.charAt(i)] ++ == 0) {
                ++ count;
            }
        }
        if (count == k) {
            res.add(s.substring(0, k));
        }

        for (int i = k; i < s.length(); ++ i) {
            if (map[s.charAt(i)] ++ == 0) {
                ++ count;
            }
            if (map[s.charAt(i - k)]-- == 1) {
                -- count;
            }
            if (count == k) {
                res.add(s.substring(i - k + 1, i + 1));
            }
        }
        return new ArrayList<String>(res);
    }

    public  static List<Integer> subSequenceTags(List<String> T,
                                                 List<String> A) {
        Map<String, Integer> map = new HashMap<>();
        // init targetList
        for (String e : T) {
            int val = map.getOrDefault(e, 0);
            map.put(e, val + 1);
        }
        int count = map.size();
        int minLen = Integer.MAX_VALUE;
        int L = 0;
//     System.out.println(count);

        int left, right = 0;
        for(left = 0; left < A.size(); ++ left) {
            while(right < A.size() && count > 0) {
                String rightVal = A.get(right);
                if (map.containsKey(rightVal)) {
                    int rightCount = map.getOrDefault(rightVal, 0);
                    if (rightCount == 1) {
                        count --;
                    }
                    map.put(rightVal, rightCount - 1);

                }
                right ++;
            }
            if (count == 0 && right - left < minLen) {
                minLen = right - left;
                L = left;
//           System.out.println();

            }

            String leftVal = A.get(left);
            if (map.containsKey(leftVal)) {
                int leftCount = map.get(leftVal);
                if (leftCount == 0)
                    count ++;
                map.put(leftVal, leftCount + 1);

            }
        }

        List<Integer> res = new ArrayList<>();
        if (minLen != 0x7fffffff) {
            res.add(L);
            res.add(L + minLen - 1);
        }
        return res;
    }
}



