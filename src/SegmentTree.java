public class SegmentTree {
    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,6,7,8,9};
        SegmentTree test = new SegmentTree();
        Node root = test.build(A, 0, A.length -1 );
        System.out.println(test.sumRange(root,0, 1));
        test.update(root, 1, 5);
        System.out.println(test.sumRange(root,0, 1));

    }
    class Node {
        int start, end;
        int sum;
        Node left, right;
        Node (int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            left = null;
            right = null;
        }
    }
    public Node build(int[] A, int left, int right) {
        if (left > right) return null;
        if (left == right) return new Node(left, right, A[left]);
        int mid = (left + right) >>> 1;
        Node root = new Node(left, right, 0);
        root.left = build(A, left, mid);
        root.right = build(A, mid + 1, right);
        root.sum = root.left.sum + root.right.sum;
        return root;
    }

    public void update(Node root, int index, int newVal) {
        if (root.start == root.end) {
            root.sum = newVal;
            return;
        }
        int mid = (root.start + root.end) >>> 1;
        if (index <= mid) {
            update(root.left, index, newVal);
        } else {
            update(root.right, index, newVal);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    public int sumRange(Node root, int start, int end) {
        if (root.start == start && root.end == end)
            return root.sum;
        int mid = (root.start + root.end) >>> 1;
        if (end <= mid) {
            return sumRange(root.left, start, end);
        } if (mid < start) {
            return sumRange(root.right, start, end);
        } else {
            return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
        }
    }

    // Timt out, so have to use BIT
    class NumMatrixRangeSumQuery2DMutable {
        int m, n;
        SegmentNode root = null;

        public NumMatrixRangeSumQuery2DMutable(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return;
            this.m = matrix.length;
            this.n = matrix[0].length;
            int[] A = new int[m * n];
            A = new int[m * n];
            for (int i = 0; i < m; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    A[i * n + j] = matrix[i][j];
                }
            }
            root = build(A, 0, m * n - 1);
        }

        public void update(int row, int col, int val) {
            put(root, row * n + col, val);
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; ++ i) {
                sum += _sumRange(root, i * n + col1, i * n + col2);
            }
            return sum;
        }

        class SegmentNode {
            int start, end;
            SegmentNode left, right;
            int sum;
            SegmentNode (int start, int end, int sum) {
                this.start = start;
                this.end = end;
                this.sum = sum;
            }
        }

        private SegmentNode build(int[] nums, int start, int end) {
            if (start > end) return null;
            if (start == end) return new SegmentNode(start, end, nums[start]);

            int mid = (start + end) >>> 1;
            SegmentNode root = new SegmentNode(start, end, 0);
            root.left = build(nums, start, mid);
            root.right = build(nums, mid + 1, end);
            root.sum = root.left.sum + root.right.sum;
            return root;
        }

        private void put(SegmentNode root, int i, int val) {
            if (root.start == root.end) {
                root.sum = val;
                return;
            }
            int mid = (root.start + root.end) >>> 1;
            if (i <= mid) {
                put(root.left, i, val);
            } else {
                put(root.right, i, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
        private int _sumRange(SegmentNode root, int i, int j) {
            if (root.start == i && root.end == j) return root.sum;
            int mid = (root.start + root.end) >>> 1;
            if (j <= mid) {
                return _sumRange(root.left, i, j);
            } else if (i > mid) {
                return _sumRange(root.right, i, j);
            } else {
                return _sumRange(root.left, i, mid) + _sumRange(root.right, mid + 1, j);
            }
        }
    }

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
    class BIT {
        int []BIT;
        int []a;
        int n;
        void update(int x, int val)
        {
            for(; x <= n; x += x&-x)
                BIT[x] += val;
        }

        int query(int x)
        {
            int sum = 0;
            for(; x > 0; x -= x&-x)
                sum += BIT[x];
            return sum;
        }
    }

    public class NumMatrix {

        int[][] tree;
        int[][] nums;
        int m;
        int n;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            m = matrix.length;
            n = matrix[0].length;
            tree = new int[m+1][n+1];
            nums = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        public void update(int row, int col, int val) {
            if (m == 0 || n == 0) return;
            int delta = val - nums[row][col];
            nums[row][col] = val;
            for (int i = row + 1; i <= m; i += i & (-i)) {
                for (int j = col + 1; j <= n; j += j & (-j)) {
                    tree[i][j] += delta;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (m == 0 || n == 0) return 0;
            return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col1);
        }

        public int sum(int row, int col) {
            int sum = 0;
            for (int i = row; i > 0; i -= i & (-i)) {
                for (int j = col; j > 0; j -= j & (-j)) {
                    sum += tree[i][j];
                }
            }
            return sum;
        }
    }

}
