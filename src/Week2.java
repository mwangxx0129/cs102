import java.util.*;

public class Week2 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length ==  0) {
            return null;
        }
        int l = 0, r = nums.length - 1;
        TreeNode root = helper(nums, l, r);
        return root;
    }

    private TreeNode helper(int[] nums, int l, int r) {
        if (l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode node = new TreeNode(nums[m]);
        node.left = helper(nums, l, m - 1);
        node.right = helper(nums, m + 1, r);
        return node;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int u = 0, b = matrix.length - 1;
        int l = 0, r = matrix[0].length - 1;
        while (u <= b && l <= r) {
            // up left->right
            for (int i = l; i <= r; ++ i) {
                res.add(matrix[u][i]);
            }
            u ++;
            // right up->bottom
            for (int i = u; i <= b; ++ i) {
                res.add(matrix[i][r]);
            }
            r --;

            // bottom right->left
            if (u <= b) {
                for (int i = r; i >= l; -- i) {
                    res.add(matrix[b][i]);
                }
                b --;
            }

            // left bottom->up
            if (l <= r) {
                for (int i = b; i >= u; -- i) {
                    res.add(matrix[i][l]);
                }
                l ++;
            }
        }
        return res;
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int u = 0, b = n - 1;
        int l = 0, r = n - 1;
        int count = 0;
        while (u <= b && l <= r) {
            // up left->right
            for (int i = l; i <= r; ++ i) {
                res[u][i] = ++ count;
            }
            u ++;
            // right up->bottom
            for (int i = u; i <= b; ++ i) {
                res[i][r] = ++ count;
            }
            r --;

            // bottom right->left
            if (u <= b) {
                for (int i = r; i >= l; -- i) {
                    res[b][i] = ++ count;
                }
                b --;
            }

            // left bottom->up
            if (l <= r) {
                for (int i = b; i >= u; -- i) {
                    res[i][l] = ++ count;
                }
                l ++;
            }
        }
        return res;
    }

    public int[] merge(int[] arr) {
        // divide
        // conquer
        // merge
        int[] helper = new int[arr.length];
        partition(arr, helper,0, arr.length - 1);
        return arr;
    }
    private void partition(int[] arr, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int m = left + (right - left) / 2;
        partition(arr, helper, left, m);
        partition(arr, helper,m + 1, right);
        doMerge(arr, helper, left, m, right);
    }

    private void doMerge(int[] arr, int[] helper, int left, int m, int right) {
        int l = left, r = m + 1;
        int index = 0;
        while (l <= m && r <= right) {
            if (arr[l] < arr[r]) {
                helper[index ++] = arr[l ++];
            } else {
                helper[index ++] = arr[r ++];
            }
        }
        //copy back
        for (int i = 0; i < index; ++ i) {
            arr[i + left] = helper[i];
        }
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[l]) {
                // left mid incr
                if (nums[m] > target && nums[l] <= target) {
                    r = m;
                } else {
                    l = m;
                }
            } else if (nums[m] < nums[l]){
                if (nums[m] < target && nums[r] >= target) {
                    l = m;
                } else {
                    r = m;
                }
            } else {
                l ++;
            }
        }

        return nums[l] == target || nums[r] == target;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        boolean leftRight = true;
        while (!queue.isEmpty()) {
            List<Integer> sub = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; ++ i) {
                TreeNode now = queue.pollFirst();
                if (now.left != null) queue.offerLast(now.left);
                if (now.right != null) queue.offerLast(now.right);
                if (leftRight) {
                    sub.add(now.val);
                } else {
                    sub.add(0, now.val);
                }
            }
            res.add(sub);
            leftRight = !leftRight;
        }
        return res;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        TreeNode dummy = new TreeNode(-1);
        TreeNode pre = dummy;
        TreeNode cur = root;
        dummy.left = root;
        // find target TreeNode
        while (cur != null && cur.val != key) {
            pre = cur;
            if (cur.val > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (cur == null) return root;

        TreeNode target = cur;
        // has two children
        if (cur.left != null && cur.right != null) {
            // get the min val in right subtree
            pre = cur;
            cur = cur.right;
            while (cur.left != null) {
                pre = cur;
                cur = cur.left;
            }
        }
        target.val = cur.val;

        // has at most 1 children
        if (pre.left == cur) {
            pre.left = cur.left != null ? cur.left : cur.right;
        } else {
            pre.right = cur.left != null ? cur.left : cur.right;
        }
        return dummy.left;
    }

    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        // copy node
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode copy = new RandomListNode(cur.label);
            copy.next = cur.next;
            cur.next = copy;
            cur = cur.next.next;
        }
        // change random pointer
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        // change next pointer
        cur = head;
        RandomListNode newHead = head.next;
        while (cur != null && cur.next != null) {
            RandomListNode tmp = cur.next;
            cur.next =  cur.next.next;
            cur = tmp;
        }
        return newHead;
    }

    public RandomListNode copyRandomList_method2(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = cur.next != null ? map.get(cur.next) : null;
            map.get(cur).random = cur.random != null ? map.get(cur.random) : null;
            cur = cur.next;
        }
        return map.get(head);
    }

    private boolean isBadVersion(int i) {
        // need to be implemented by api
        return true;
    }
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (isBadVersion(m)) {
                r = m;
            } else {
                l = m;
            }
        }
        return isBadVersion(l) ? l : r;
    }

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(cur != null) {
            stack.offerLast(cur);
            cur = cur.left;
        }

        TreeNode newHead = stack.pollLast();
        TreeNode par = newHead;
        while (!stack.isEmpty()) {
            TreeNode now = stack.pollLast();
            par.left = now.right;
            par.right = now;
            par = now;
        }
        par.left = null;
        par.right = null;

        return newHead;
    }

    public TreeNode upsideDownBinaryTree_method2(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree_method2(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root == null) {
            return res;
        }
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < size; ++ i) {
                TreeNode node = queue.pollFirst();
                sub.add(node.val);
                if (node.left != null) {
                    queue.offerLast(node.left);
                }
                if (node.right != null) {
                    queue.offerLast(node.right);
                }
            }
            res.add(sub);
        }
        return res;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m, n;
        if (matrix == null ||(m = matrix.length) == 0  || (n = matrix[0].length) == 0) {
            return false;
        }
        int l = 0, r = m * n - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (matrix[mid / n][mid % n] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return matrix[l / n][l % n] == target || matrix[r / n][r % n] == target;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return helper(root.left, root.right);
    }
    private boolean helper(TreeNode l, TreeNode r) {
        if (l == null || r == null) return l == r;
        if (l.val != r.val) return false;
        return helper(l.left, r.right) && helper(l.right, r.left);
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> hash = new HashSet<>();
        for(int num : nums) {
            hash.add(num);
        }

        int max = 0;
        for (int num : nums) {
            int t = num;
            int count = 1;
            while (hash.remove( ++ t)) count ++;
            t = num;
            while (hash.remove(-- t)) count ++;
            max = Math.max(max, count);
        }
        return max;
    }

    private class RT {
        boolean isValid;
        int min;
        int max;
        RT(boolean isValid, int min, int max) {
            this.isValid = isValid;
            this.min = min;
            this.max = max;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root).isValid;
    }

    private RT helper(TreeNode root) {
        if (root == null) {
            return new RT(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        RT l = helper(root.left);
        RT r = helper(root.right);
        if (!l.isValid || !r.isValid) {
            return new RT(false, 0, 0);
        }
        if (root.left != null && l.max >= root.val
                || root.right != null && r.min <= root.val) {
            return new RT(false, 0, 0);
        }
        return new RT(true, Math.min(l.min, root.val), Math.max(r.max, root.val));
    }

    public TreeNode search(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == val) {
                return cur;
            } else if (cur.val < val){
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return null;
    }

    public TreeNode search_method2(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        if (root.val > val) {
            return search_method2(root.left, val);
        } else {
            return search_method2(root.right, val);
        }
    }

    public TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val > val) {
            root.left = insert(root.left, val);
        } else if(root.val < val) {
            root.right =  insert(root.right, val);
        }
        return root;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum == root.val) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, sum, res, new ArrayList<Integer>());
        return res;
    }
    private void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> path) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            res.add(new ArrayList<Integer>(path));
        } else {
            dfs(root.left, sum - root.val, res, path);
            dfs(root.right, sum - root.val, res, path);
        }

        path.remove(path.size() - 1);
    }

    int count = 0;
    public int pathSum_III(TreeNode root, int sum) {
        pathSum_III_dfs(root, sum, new ArrayList<Integer>());
        return count;
    }
    private void pathSum_III_dfs(TreeNode root, int sum, List<Integer> path) {
        if (root == null)
            return;
        path.add(root.val);
        int tmp = sum;
        for (int i = path.size() - 1; i >= 0; -- i) {
            tmp -= path.get(i);
            if (tmp == 0) {
                count ++;
            }
        }
        pathSum_III_dfs(root.left, sum, path);
        pathSum_III_dfs(root.right, sum, path);
        path.remove(path.size() - 1);
    }

}
