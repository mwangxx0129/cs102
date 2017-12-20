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
