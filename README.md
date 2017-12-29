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

Node<E> in LinkedList<E> 

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

#### Week2
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
- Path Sum I~V -
- Path Sum IV leetcode 666
- Largest BST SubTree -

#### Java Advanced Algorithm
- sqrt -
- Count Complete Tree Nodes -
- Find Leaves of Tree -
- Invert Binary tree -
- Largest BST -
- Russian Doll Envolopes -
- Is Subsequence -
- Split Array Largest Sum -

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