import java.util.*;
public class KMP {
    //Pattern
    private static String pat;
    //Length
    private static int M;
    private static int R;
    //Dfa
    private static int[][] dfa;

    static
    {
        R = 256;
        pat = "ABABAC";
        M = pat.length();
        dfa = new int[R][M];

    }

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//
//        dfa[pat.charAt(0)][0] = 1;
//        for (int X = 0,j = 1; j < M; j++) {
//
//            for (int c = 0; c < R; c++) {
//                dfa[c][j] = dfa[c][X];
//            }
//
//            dfa[pat.charAt(j)][j] = j+1;
//
//            X = dfa[pat.charAt(j)][X];
//
//            System.out.println("The KMP is A " + X);
//        }
//
//        for (int i = 0; i < dfa.length; ++ i) {
//            for (int j = 0; j < dfa[0].length; ++ j) {
//                System.out.print(dfa[i][j] + ",");
//            }
//            System.out.println();
//        }
//
//    }
    public static void main(String[] args) {
        // build pattern next DFA
        String pattern = "aabaaac";
//        String pattern = "abcabcc";
        int[] next = getNextII(pattern);
        System.out.println(Arrays.toString(next));

        // check pattern in target based on DFA
        String target = "aabaaabaaac";
        int m = target.length();
        int n = pattern.length();
        int index = indexOfKMP(target, m, pattern, n, next);
        System.out.println(index);
    }

    public static int indexOfKMP(String source, int sourceLen, String pattern, int patternLen, int[] next) {
        int s = 0, p = 0;
        while (s < sourceLen && p < patternLen && patternLen <= sourceLen) {
            if (source.charAt(s) == pattern.charAt(p)) {
                ++ s; ++ p;
            } else {
                if (p == 0) ++ s;
                else p = next[p - 1];
            }
        }
        return p == patternLen ? s - patternLen : -1;
    }

    public static int[] getNextII(String s) {
        int len = s.length();
        int[] next = new int[len];
        for (int i = 1; i < len; ++ i) {
            int j = next[i - 1];// matched len
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = next[j - 1];
            if (s.charAt(i) == s.charAt(j)) next[i] = j + 1;
            else next[i] = 0;
        }
        return next;
    }

}