![Progress](http://progressed.io/bar/75?title=completed) 
### Course Syllabus - Java Foundation

| week1 | week2 | week3 | week4 |
|-------|-------| ----- | ----- |
|Array,List|Recursion,Heap,Stack|Sort Algorithm|JUnit, Test Driven Development|
|String, StringBuilder|Inner Class, TreeNode|Graph Node Representation|Java 8 Features|
|Queue,Stack,Deque|Tree Structure, TreeSet| Heap, Priority Queue|Guava(Google Core Java Libraries|
|LinkedList,ArrayDeque|Hash,HashTable|LRU Cache,LinkedHashMap|Other Open Source Java Library|

### Course Syllabus - Java Algorithm

| week1 - Linear Structure| week2 - Tree Structure | week3 - Graph Search | week4 - DP and advance|
|-------|-------| ----- | ----- |
|Pointers|Recursion|DFS|Strategy/Solution Tree and Basic DP|
|LinkedList|Binary Search and sorting|Breath First Search|Memorized Search|
|Queue,Deque|Binary Tree|Dijkstra algorithm(BFS)|Advanced DP|
|Stack|Binary Search Tree|Union Find|Advanced Structure(e.g. Segment tree, Binary Indexed Tree, etc)

### Course Syllabus - Java Advanced Algorithm

Algorithm Advance: New Problems on Same Topic Correspondingly

---
### week4


### Reivew Concept
- Simple Sorting: Insertion Sort is best of three(Comparsion,Swap)
- Advanced Sort: QuickSort (pivot, in-place, not stable)
- Advanced Sort: MergeSort (low + heigh) >>> 1, stable, extra space
- Heap: Complete tree, array impolementation
- Heap Sort: O(nlogn), not stable, extra space
- LinkedHashmap: Extends HashMap, Insertion order


### Review Coding
- Top K use minHeap
- Use LinkedHashMap

### Java 8 new Features
- Lambda expression ->
- Functional Interface Predicates,Co
- Method Reference System.out::println
- Default Methods implementation in interface
- Stream sort, map, reduce, max
- Collectors


### Homework
- Migrate old java code to java 8
- Review some APIs

- Guava
#### Java pre-Algorithm
- Climbing Chairs dp
- Count Primes dp
- Maximum subArray dp
- Edit distance dfs dp
- Unique binary search tree

#### Java Algorithm
- 64. Minimum Path Sum
- Regular Expression Matching
- Paint house --- online algr 
    1. soultion tree
    2. dp
    3. optim space
    4. greedy
- Product
- Jump Game I && II
- Range sum Query

- Segment Tree
- Binary Indexed Tree
---

### week3

#### Java Foundation
- Simple Sorting: Insertion Sort is the best of three
- Advanced Sort: Quick Sort(pivot, in-place, not stable)
- Advanced Sort: Merge Sort((low + high) >>> 1, extra space, stable)
- Heap: Complete tree, Array implementation
- Heap Sort:O(log(n)), extra space, not stable
- HW1: Top K numbers
- LinkedListHashMap: Extends HashMap, Insertion order
- Hw2: LRU cache


#### Java pre-Algorithm
- Permutation I II
- Kth Largest Element in an Array
- Kth smallest Element in a Sorted Array - bfs bd
- Number of Island I II - bfs dfs uf
- Word Ladder - bfs bi-bfs


Permutation
bfs
```
                    []
                     |           
                    [1]
                 /         \
        [2,1]                   [1,2]
        
      /    \     \          /      |     \
[3,2,1] [2,3,1] [2,1,3] [3,1,2] [1,3,2] [1,2,3]
```
dfs methd 1
```
                  []
               /  |    \
            [1]  [2]    [3]
         / \     /   \     /   \
    [1,2][1,3] [2,1][2,3] [3,1][3,2]
    /    \        /   \       \     \
[1,2,3][1,3,2][2,1,3][2,3,1][3,1,2][3,2,1]
```

dfs method 2 3
```
                  []
               /  |    \
            [1]  [2]    [3]
         / \     /   \     /   \
    [1,2][1,3] [2,1][2,3] [3,2][3,1]
    /    \        /   \       \     \
[1,2,3][1,3,2][2,1,3][2,3,1][3,2,1][3,1,2]
```
#### Java Algorithm
- Factor Combination - Solution / Recursive / Strategy Tree
- Sodoku Solver - dfs
- Merge k sorted List - bfs merge
- Find k pairs with smallest sums - bfs
- Clone graph - bfs dfs
- Course schedule - directed graph
- Wall and Gate - multi-bfs

#### Java Advanced Algorithm
- [x] Trapping Water II
- [x] Longest consecutive sequence
- [x] Number of island II
- [x] House robber 3
- [x] Shortest Distance from all building
- [x] Word ladder II
- [x] Course schedule I && II
- [x] Alien Dictionary

#### Discussion
- 138_Copy_List_with_Random_Pointer
- 278_First_Bad_Version
- 102 Binary Tree Level Order Traversal
- 74_Search_2D_Matrix
---
####Extra
230. Kth Smallest Element in a BST 

### week0

- Primitive idea -> Work Solution -> Optimization
- A. 150. Evaluate Reverse Polish Notation
- B. [198. House Robber](https://leetcode.com/problems/house-robber/description/)
   213 House Robber II --- Strategy Tree -> DP
- C. [323. Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/)

---

### week1
#### Java Foundation
1. Utility Class: Arrays, Math
2. ArrayList: 1.5x Grow, >> 1
3. ArrayList: no holes
4. LinkedList: Nested static class, Double Link
5. LinkedList: Space consuming
6. ArrayDeque: circular queue
7. Array Deque: 2x Grow, (head - 1) & (length - 1)
8. ArrayDeque: O(1) Amortized performance for queue
9. ArrayDeque: fastest practice for algorithm

Basically, Read the source code from Official library
http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/8u40-b25/java/util/Arrays.java?av=f

e.g.
Arrays toString()
 
## Node<E> in LinkedList<E>

<details><summary>CLICK ME</summary>
<p>
#### yes, even hidden code blocks!

```java
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
    Node (Node<E> prev, E item, Node<E> next) {
        this.item = item;
        this.prev = prev;
        this.next = next;
    }
}
```
</p>
</details>

#### Java pre-Algorithm

- Two Sum - method 1
- Two Sum - method 2
- Evaluate Reverse Polish Rotation
- Gas Station
- Minimum Window Substring
- Partition List
- Remove Nth Node From Tail
- Sort Colors
- Valid Parentheses

#### Java Algorithm

- Reorder List
- Reverse Nodes in k-Group
- Three Sum - method 1
- Three Sum - method 2
- Length of Longest substring - method 1
- Length of Longest substring - method 2
- Moving Average from Data Stream
- Simply Path - method 1
- Simply Path - method 2
- Largest Rectangle in Histogram
- Trapping Water - method 1
- Trapping Water - method 2



#### Java Advanced Algorithm

- highest product of 3 (not in leetcode) -
- Container with most water
- candy
- Top K elements
- Sort transformed array
- Linked List cycle 2 - configure out math prove
- Remove k digits
- 3 Sum smaller
- Closest Binary tree value 2 -

#### Discussion 12_22
- Sorted Array to BST --- divide conquer
- Spiral Matrix --- scan l->r u->b r->l b->u
- Merge Sort --- divide conquer merge
- Search in Rotated Sorted Array --- binary search
- ZigZag Level Order Traversal  --- bfs --- if the res save treenode, use res sublist as pointer
- Delete a Node in BST 

---

### week2
#### Java Foundation
- 2's complement, Modular/Remainder operation, X mod 2 ^ n = X & (2 ^ n - 1)
- Hash Code: many to one
- Hashing: Probing vs Separate Chain
- Hash Table: Load factor, Rehashing
- Java HashMap: Use separate chain, modular optimization
- Java HashSet: Wrapper of Hashmap
- TreeMap: Red-black tree, sorted keys, O(log N)
- JVM: virtual machine as running environment
- Stack space, heap space, GC

#### Java pre-Algorithm
- copyRandomList
- copyRandomList_method2
- isBadVersion
- upsideDownBinaryTree
- upsideDownBinaryTree_method2
- Level Order Traversal
- Search a 2D Matrix
- Symmetric tree
- Longest consecutive Sequence
- Search/Insert a Node in BST
- Validate a BST

#### Java Algorithm
- Sorted Array to BST
- Spiral Matrix
- Merge Sort
- Search in Rotated Sorted Array
- ZigZag Level Order Traversal
- Delete a Node in BST
- Path Sum I
- Path Sum II
- Path Sum III
- Path Sum IV leet code 666
- Path Sum V
- Largest BST SubTree

#### Java Advanced Algorithm
- sqrt
- Count Complete Tree Nodes
- Find Leaves of Tree - sumOfLeftLeaves leetcode 404
- Invert Binary tree -?Iterative need to discussion
- Largest BST
- Russian Doll Envolopes -tricky
- Is Subsequence - need to consider follow up using binary search/Red black tree
- Split Array Largest Sum - binary search, need to figure out dp solution later

#### Discussion 12_26
- path Sum
- Two sum - method 1
- Two sum - method 2
- Remove N th Node From Tail 
- Sort Colors
- Valid Parentheses
- Three Sum - method1
- Three Sum - method2
- Reverse Node in k-Group

#### Discussion 12_30
- clean room dfs + grid
- isValid BST
- levelOrder
- find Leaf

<details><summary>source code</summary>
<p>

```java
class Solution{
    public boolean isValidBST(TreeNode root) {
        if(root = null) {
            return true;
        }
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    
    private boolean isValidBST(TreeNode root, long max, long min) {
        if (root == null) {
            return true;
        }
    
        //Current Level : Check root.val
    
        if(root.val >= max || root.val <= min) {
            return false;
        }
    
        // Return Left and Right
        return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val); 
    }
}
```


```java
class Solution {
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
}
```

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
            
        }
        return list;
    }    
}
```


```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
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
}

class Solution{
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }
    
    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(root.val);
        levelHelper(res, root.left, height+1);
        levelHelper(res, root.right, height+1);
    }
}

class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs_findLeaves(root, res);
        return res;
    }
    private int dfs_findLeaves(TreeNode root, List<List<Integer>> res) {
        if (root == null) return -1;
        int h = Math.max(dfs_findLeaves(root.left, res),
                dfs_findLeaves(root.right, res)) + 1;
        if (h == res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(h).add(root.val);
        return h;
    }
}
```

</p>
</details>

