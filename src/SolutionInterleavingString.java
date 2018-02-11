import java.util.HashSet;
import java.util.Set;

public class SolutionInterleavingString {
    public static void main(String[] args) {
        SolutionInterleavingString test = new SolutionInterleavingString();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";

        boolean res = test.isInterleave(s1, s2, s3);
        System.out.println(res);
    }
//    public boolean isInterleave(String s1, String s2, String s3) {
//        if (s3.isEmpty() && s1.isEmpty() && s2.isEmpty()) return true;
//        if (s3.isEmpty()) return false;
//        if ( !s1.isEmpty() && s1.charAt(0) == s3.charAt(0) && isInterleave(s1.substring(1), s2, s3.substring(1)))
//            return true;
//        if ( !s2.isEmpty() && s2.charAt(0) == s3.charAt(0) && isInterleave(s1, s2.substring(1), s3.substring(1)))
//            return true;
//        return false;
//    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        Set<Integer> s = new HashSet<>();
        boolean res = isInterleave(s1, 0, s2, 0, s3, 0, new HashSet<Integer>());
        return res;
    }

    private boolean isInterleave(String s1, int i, String s2, int j, String s3, int k, HashSet<Integer> s) {
        int key = i * s3.length() + j;
        if (s.contains(key)) return false;
        if (i == s1.length()) return s2.substring(j).equals(s3.substring(k));
        if (j == s2.length()) return s1.substring(i).equals(s3.substring(k));
        if (s1.charAt(i) == s3.charAt(k) && isInterleave(s1, i + 1, s2, j, s3, k + 1, s) ||
                s2.charAt(j) == s3.charAt(k) && isInterleave(s1, i, s2, j + 1, s3, k + 1, s))
            return true;
        System.out.println("--");
        s.add(key);
        return false;
    }
}
