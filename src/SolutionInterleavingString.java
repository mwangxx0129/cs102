public class SolutionInterleavingString {
    public static void main(String[] args) {
        SolutionInterleavingString test = new SolutionInterleavingString();
        String s1 = "a";
        String s2 = "b";
        String s3 = "a";

        boolean res = test.isInterleave(s1, s2, s3);
        System.out.println(res);
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.isEmpty() && s1.isEmpty() && s2.isEmpty()) return true;
        if (s3.isEmpty()) return false;
        if ( !s1.isEmpty() && s1.charAt(0) == s3.charAt(0) && isInterleave(s1.substring(1), s2, s3.substring(1)))
            return true;
        if ( !s2.isEmpty() && s2.charAt(0) == s3.charAt(0) && isInterleave(s1, s2.substring(1), s3.substring(1)))
            return true;
        return false;
    }
}
