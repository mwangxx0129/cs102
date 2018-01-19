import java.util.*;

public class Week3 {

    /**
     *
     * @param nums
     * @return
     */

    // permutate
    // bfs
    public List<List<Integer>> permutate(int[] nums) {
//        return permutate_bfs(nums);
        return permute_dfs_method2(nums);
    }

    public List<List<Integer>> permutate_bfs(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        res.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; ++ i) {
            List<List<Integer>> nextRes = new ArrayList<>();
            for (List list : res) {
                for (int j = 0; j < list.size() + 1; ++ j) {
                    List<Integer> nextList = new ArrayList<>(list);
                    nextList.add(j, nums[i]);
                    nextRes.add(nextList);
                }
            }
            res = nextRes;
        }
        return res;
    }

    private List<List<Integer>> permute_dfs_method1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        permute_dfs_method1_helper(nums, new ArrayList<Integer>(), res);
        return res;
    }
    private void permute_dfs_method1_helper(int[] nums, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; ++ i) {
            if (!path.contains(nums[i])) {
                path.add(nums[i]);
                permute_dfs_method1_helper(nums, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
    private List<List<Integer>> permute_dfs_method2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        permute_dfs_method2_helper(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }
    private void permute_dfs_method2_helper(int[] nums, int startIndex, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = startIndex; i < nums.length; ++ i) {
            path.add(nums[i]);
            swap(nums, startIndex, i);
            permute_dfs_method2_helper(nums, startIndex + 1, path, res);
            swap(nums, startIndex, i);
            path.remove(path.size() - 1);
        }
    }

    private List<List<Integer>> permute_dfs_method3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        permute_dfs_method3_helper(nums, 0, res);
        return res;
    }
    private void permute_dfs_method3_helper(int[] nums, int level, List<List<Integer>> res) {
        if (level == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int e : nums)
                list.add(e);
            res.add(list);
            return;
        }
        for (int i = level; i < nums.length; ++ i) {
            swap(nums, level, i);
            permute_dfs_method3_helper(nums, level + 1, res);
            swap(nums, level, i);
        }
    }



    // swap array, save path, push to res
    public void permutate_dfs_method2(int[] nums, int level, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = level; i < nums.length; ++ i) {
            path.add(nums[i]);
            swap(nums, level, i);
            permutate_dfs_method2(nums, level + 1, path, res);
            swap(nums, level, i);
            path.remove(path.size() - 1);
        }
    }
    private void swap(int[] nums, int l, int r) {
        if (l == r || nums[l] == nums[r]) return;
        nums[l] ^= nums[r];
        nums[r] ^= nums[l];
        nums[l] ^= nums[r];
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        permuteUnique_dfs_method1(nums, res, 0);
//        Arrays.sort(nums);
//        permuteUnique_dfs_method2(nums, new ArrayList<Integer>(), res, new boolean[nums.length]);
        return res;
    }
    private void permuteUnique_dfs_method1(int[] nums, List<List<Integer>> res, int level) {
        if (level == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int e : nums) {
                list.add(e);
            }
            res.add(list);
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = level; i < nums.length; ++ i) {
            if (set.add(nums[i])) {
                swap(nums, level, i);
                permuteUnique_dfs_method1(nums, res, level + 1);
                swap(nums, level, i);
            }
        }
    }


    private void permuteUnique_dfs_method2(int[] nums, List<Integer> path, List<List<Integer>> res, boolean[] isVisited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; ++ i) {
            if (isVisited[i] || i > 0 && nums[i - 1] == nums[i] && !isVisited[i - 1]) continue;
            path.add(nums[i]);
            isVisited[i] =  true;
            permuteUnique_dfs_method2(nums, path, res, isVisited);
            isVisited[i] =  false;
            path.remove(path.size() - 1);
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         return ladderLength_method1(beginWord, endWord, wordList);
        return bfs_method2(beginWord, endWord, wordList);
    }

    private int bfs_method2_pre(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        int step = 0;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            ++ step;
            // swap
            if (beginSet.size() > endSet.size()) {
                Set<String> tmp = endSet;
                endSet = beginSet;
                beginSet = tmp;
            }
            Set<String> nextLevel = new HashSet<>();
            for (String word : beginSet) {
                for (int i = 0; i < word.length(); ++ i) {
                    char[] chars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String temp = new String(chars);
                        if (endSet.contains(temp)) {
                            return step + 1;
                        }
                        if (visited.add(temp) && dict.contains(temp)) {
                            nextLevel.add(temp);
                        }
                    }
                }
            }
            beginSet = nextLevel;
        }
        return 0;
    }

    public int ladderLength_method1(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        int step = 0;
        queue.offerLast(beginWord);
        visited.add(beginWord);

        while(!queue.isEmpty()) {
            ++ step;
            int size = queue.size();
            for (int i = 0; i < size; ++ i) {
                String word = queue.pollFirst();
                for (String newWord : getNextList(word, dict))
                    if (visited.add(newWord))
                        if (newWord.equals(endWord)) return step + 1;
                        else queue.offerLast(newWord);
            }
        }
        return 0;
    }
    private Set<String> getNextList(String word, Set<String> dict) {
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < word.length(); ++ i) {
            char[] sCh = word.toCharArray();
            for (char c = 'a'; c <= 'z'; ++ c) {
                sCh[i] = c;
                String newWord = new String(sCh);
                if ( dict.contains(newWord)) set.add(newWord);
            }
        }
        return set;
    }

    public int bfs_method2(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        int step = 0;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
             if (beginSet.size() > endSet.size()) {
                 Set<String> tmp = beginSet;
                 beginSet = endSet;
                 endSet = tmp;
             }
            // swap
//            if (beginSet.size() > endSet.size()) {
//                Set<String> tmp = endSet;
//                endSet = beginSet;
//                beginSet = tmp;
//            }

            ++ step;
            Set<String> nextLevel = new HashSet<>();
            for (String word : beginSet) {
                for (int i = 0; i < word.length(); ++ i) {
                    char[] sCh = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; ++ c) {
                        sCh[i] = c;
                        String newWord = new String(sCh);
                        if (endSet.contains(newWord)) return step + 1;
                        if (dict.contains(newWord) && visited.add(newWord)) {
                            nextLevel.add(newWord);
                        }
                    }
                }
            }
            beginSet = nextLevel;
        }
        return 0;
    }

    class Point {
        int x, y;
        int val;
        Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<Point> minHeap = new PriorityQueue<>(k, new Comparator<Point>(){
            @Override
            public int compare(Point a, Point b) {
                return a.val - b.val;
            }
        });

        for (int i = 0; i < m; ++ i)
            minHeap.offer(new Point(i, 0, matrix[i][0]));
        int count  = k;
        while (k -- > 1) {
            Point cur = minHeap.poll();
            if (1 + cur.y < n) {
                minHeap.offer(new Point(cur.x, 1 + cur.y, matrix[cur.x][1 + cur.y]));
            }
        }
        return minHeap.poll().val;
    }

    public int numIslands(char[][] grid) {
        return numIslands_uf(grid);
    }
    public int numIslands_dfs(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (grid[i][j] == '1') {
                    ++ count;
                    numIslands_dfs_helper(grid, i, j);
                }
            }
        }
        return count;
    }
    private void numIslands_dfs_helper(char[][] grid, int r, int c) {
        grid[r][c] = '0';
        if (isValid(grid, r + 1, c)) numIslands_dfs_helper(grid, r + 1, c);
        if (isValid(grid, r, c + 1)) numIslands_dfs_helper(grid, r, c + 1);
        if (isValid(grid, r, c - 1)) numIslands_dfs_helper(grid, r, c - 1);
        if (isValid(grid, r - 1, c)) numIslands_dfs_helper(grid, r - 1, c);
    }
    private boolean isValid(char[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        return r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == '1';
    }


    public int numIslands_uf(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UF uf = new UF(m * n);
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (grid[i][j] == '1') {
                    ++ uf.count;
                    if (i - 1 >= 0 && i < m && grid[i - 1][j] == '1') uf.union((i - 1) * n + j, i * n + j);
                    if (j - 1 >= 0 && j < n && grid[i][j - 1] == '1') uf.union(i * n + (j - 1), i * n + j);
                }
            }
        }
        return uf.count;
    }

    class UF {
        public int[] father;
        public int count;
        UF(int n) {
            this.father  = new int[n];
//            this.count = n;
            for (int i = 0; i < n; ++ i) {
                father[i] = i;
            }
        }
        public int find(int a) {
            int fa = father[a];
            if (fa == a)
                return fa;
            return father[a] = find(father[fa]);
        }
        public void union(int a, int b) {
            int fa = find(a);
            int fb = find(b);
            if (fa != fb) {
                -- count;
                father[fa] = father[fb];
            }
        }
    }
}
