import java.util.*;

public class Week3 {

    /**
     *
     * @param nums
     * @return
     */

    // bfs
    public List<List<Integer>> permutate(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        res.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; ++ i) {
            List<List<Integer>> nextRes = new ArrayList<>();// next level
            for (List<Integer> list : res) {
                for (int j = 0; j < list.size() + 1; ++ j) {
                    List<Integer> nextList = new ArrayList<>(list);
                    nextList.add(j, nums[i]);
                    nextRes.add(nextList);
                }
            }
            res = nextRes;
        }
        System.out.println(res);
        return res;
    }

    public List<List<Integer>> permutate_dfs(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
//        permutate_dfs_method1(nums, new ArrayList<Integer>(),  res);
//        permutate_dfs_method2(nums, 0, new ArrayList<Integer>(), res);
        permutate_dfs_method3(nums, 0, res);
        System.out.println(res);
        return res;
    }

    // from the view of a collection, use contain to check element is in current set
    private void permutate_dfs_method1(int[] nums, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; ++ i) {
            if(path.contains(nums[i])) continue;
            path.add(nums[i]);
            permutate_dfs_method1(nums, path, res);
            path.remove(path.size() - 1);
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

    // swap array, just push leaf node set to res
    public void permutate_dfs_method3(int[] nums, int level, List<List<Integer>> res) {
        if (level == nums.length - 1) {
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            res.add(path);
            return;
        }
        for (int i = level; i < nums.length; ++ i) {
            swap(nums, level, i);
            permutate_dfs_method3(nums, level + 1, res);
            swap(nums, level, i);
        }
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
        // return bfs_method1(beginWord, endWord, wordList);
        return bfs_method2(beginWord, endWord, wordList);
    }

    private int bfs_method2(String beginWord, String endWord, List<String> wordList) {
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

    private int bfs_method1(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>(1000);

        int count = 1;
        queue.offerLast(beginWord);
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++ i) {
                String head = queue.pollFirst();
                if (head.equals(endWord)) return count;
                for (String nextWord : getNextWords(head, dict))
                    if (visited.add(nextWord)) queue.offerLast(nextWord);

            }
            ++ count;
        }
        return 0;
    }

    private Set<String> getNextWords(String curr, Set<String> dict) {
        Set<String> nextWords = new HashSet<String>();
        for (int i = 0; i < curr.length(); i++) {
            char[] chars = curr.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String temp = new String(chars);
                if (dict.contains(temp)) {
                    nextWords.add(temp);
                }
            }
        }
        return nextWords;
    }
}
