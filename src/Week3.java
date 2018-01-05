import java.util.ArrayList;
import java.util.List;

public class Week3 {
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
}
