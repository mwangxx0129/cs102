public class Week4 {
    public static void main(String[] args) {
        Week4 test = new Week4();
        boolean res = test.isOneEditDistance("a", "");
        System.out.println(res);
    }
    private int getEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        // def
        int[][] dp = new int[m + 1][n + 1];

        // init
        for (int i = 0; i <= m; ++ i) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; ++ i) {
            dp[0][i] = i;
        }

        for (int i = 1; i < dp.length; ++ i) {
            for (int j = 1; j < dp[0].length; ++ j) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m > n) return isOneEditDistance(t, s);
        if (n - m >= 2) return false;

        // try insert
        int i = 0;
        for (; i < m; ++ i) {
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(i).equals(t.substring(i + 1)) ||
                        s.substring(i + 1).equals(t.substring(i + 1));
            }
        }
        return n - i == 1;
    }

    class SolutionNumTrees {
        public int numTrees(int n) {
            // int[] root = new int[n + 1];
            // return dfs(n, root);
            return dp(n);
        }
        private int dfs(int n, int[] root) {
            if (n == 0 || n == 1) return 1;
            if (root[n] != 0) return root[n];

            int sum = 0;
            for (int i = 0; i < n; ++ i) {
                sum += dfs(i, root) * dfs(n - 1 - i, root);
            }
            root[n] = sum;
            return sum;
        }
        private int dp(int n) {
            int[] G = new int[n + 1];
            G[0] = G[1] = 1;
            for (int i = 2; i <= n; ++ i) {
                for (int j = 0; j < i; ++ j) {
                    G[i] += G[j] * G[i - j - 1];
                }
            }
            return G[n];
        }
    }
}
