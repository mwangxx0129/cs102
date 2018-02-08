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



    class SolutionGetPathSum {
//        问一道题：
//        1. dp题。给一个grid的宽和长，求得从左下的点到右下的点所有可能的路径之和。
//        起点当然是左下，要求每次只能走三个方向， ➡↗↘
//
//        follow up: 2d dp -> 1d dp

//        public static void main (String[] args) {
//            System.out.println("Hello Java");
//            int[][] grid = {
//                    {1,2,3},
//                    {4,5,6},
//                    {7,8,9}
//            };
//            int m = grid.length;
//            int n = grid[0].length;
//            System.out.println(getPathSumII(grid, 3, 3));
//
//        }

        private int getPathSum(int[][] grid, int m, int n) {
            int[][] dp = new int[m][n];
            // init
            dp[m - 1][0] = grid[m - 1][0];
            for (int i = m - 2; i >= 0; -- i) {
                dp[i][0] = dp[i + 1][0] + grid[i][0];
            }
            for (int i = 1; i < n; ++ i) {
                dp[m - 1][i] = dp[m - 1][i - 1] + grid[m - 1][i - 1];
            }

            for (int i = m - 2; i >= 0; -- i) {
                for (int j = 1; j < n; ++ j) {
                    dp[i][j] = dp[i + 1][j] + dp[i + 1][j - 1] + dp[i][j - 1] + grid[i][j];
                }
            }
            return dp[0][n - 1];
        }

        private int getPathSumII(int[][] grid, int m, int n) {
            int[]dp = new int[n];
            // init
            dp[0] = grid[m - 1][0];


            for (int i = 1; i < n; ++ i) {
                dp[i] = dp[i - 1] + grid[m - 1][i - 1];
            }

            // fill dp
            for (int i = m - 2; i >= 0; -- i) {
                // left <- right
                for (int j = n - 1; j >= 1; -- j) {
                    dp[j] = dp[j] + dp[j - 1];
                }
                // the left one
                dp[0] = dp[0] + grid[i][0];

                // left -> right
                for (int j = 1; j < n; ++ j) {
                    dp[j] = dp[j] + dp[j - 1] + grid[i][j];
                }
            }
            return dp[n - 1];
        }

    }
    class SolutionMinPathSum {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0)
                return 0;
            int m = grid.length;
            int n = grid[0].length;
            int[][] dp = new int[m][n];

            dp[0][0] = grid[0][0];
            for (int i = 1; i < m; ++ i) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            for (int i = 1; i < n; ++ i) {
                dp[0][i] = dp[0][i - 1] + grid[0][i];
            }
            for (int i = 1; i < m; ++ i) {
                for (int j = 1; j < n; ++ j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            return dp[m - 1][n - 1];
        }

        public int minPathSum_optimized_space(int[][] grid) {
            // conrer case
            if (grid == null || grid.length == 0 || grid[0].length == 0)
                return 0;
            int m = grid.length;
            int n = grid[0].length;

            // init dp[0... n]
            int[] dp = new int[n];
            dp[0] = grid[0][0];
            for (int i = 1; i < n; ++ i) {
                dp[i] += dp[i - 1] + grid[0][i];
            }

            // iterate row by row
            for (int i = 1; i < m; ++ i) {
                dp[0] += grid[i][0];
                for (int j = 1; j < n; ++ j) {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
            return dp[n - 1];
        }
    }
    class SolutionPaintHouse {
//        public static void main(String[] args) {
//
//        }
        public int minCost(int[][] costs) {
            if (costs == null || costs.length == 0 || costs[0].length < 3) return 0;
            int m = costs.length;
            int[][] dp = new int[m][3];

            dp[0][0] = costs[0][0];
            dp[0][1] = costs[0][1];
            dp[0][2] = costs[0][2];
            System.out.println(dp[0][1]);
            for (int i = 1; i < m; ++ i) {
                for (int j = 0; j < 3; ++ j) {
                    dp[i][j] = costs[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
                }
            }
            return Math.min(dp[m - 1][0], Math.min(dp[m - 1][1], dp[m - 1][2]));
        }
    }

    class SolutionPaintHouseII {
        public int minCostII(int[][] costs) {
            if (costs == null || costs.length == 0 || costs[0].length < 1) return 0;
            int m = costs.length;
            int n = costs[0].length;
            for (int i = 1; i < m; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    costs[i][j] += findMin(costs[i - 1], j);
                }
            }
            return findMin(costs[m - 1], - 1);
        }
        private int findMin(int[] cost, int except) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < cost.length; ++ i) {
                if (i == except) continue;
                min = Math.min(min, cost[i]);
            }
            return min;
        }
    }

    class SolutionJumpGame {
        public boolean canJump(int[] nums) {
            if (nums == null || nums.length == 0) return false;
            int lastIndex = nums.length - 1;
            for (int i = nums.length - 1; i >= 0; -- i)
                if (nums[i] + i >= lastIndex)
                    lastIndex = i;
            return lastIndex == 0;
        }
    }

    class SolutionJumpGameII {
        public int jump(int[] nums) {
            if (nums == null || nums.length == 0) return -1;
            int n = nums.length;
            int[] min = new int[n];
            min[0] = 0;
            int curIndex = 0;
            for (int i = 1; i < nums.length; ++ i) {
                for (int j = curIndex; j < i; ++ j) {
                    if (nums[j] + j >= i) {
                        min[i] = min[j] + 1;
                        curIndex = j;
                        break;
                    }
                }
            }
            return min[nums.length - 1];
        }
    }
}
