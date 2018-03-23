import java.util.*;

public class LRUCache {
    int capacity;
    Map<Integer, Integer> keyValMap;
    Map<Integer, ListNode> keyNodeMap;
    ListNode head, tail;
    class ListNode {
        int val;
        ListNode prev, next;
        ListNode(int v) {
            val = v;
            this.prev = this.next = null;
        }
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyValMap = new HashMap<>();
        keyNodeMap = new HashMap<>();
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!keyValMap.containsKey(key)) {
            return -1;
        }

        ListNode cur = keyNodeMap.get(key);
        // remove
        remove(cur);
        // add to tail
        moveToTail(cur);
        return keyValMap.get(key);
    }

    private void remove(ListNode cur) {
        ListNode prev = cur.prev;
        ListNode next = cur.next;
        prev.next = next;
        next.prev = prev;
    }

    private void moveToTail(ListNode cur) {
        ListNode prev = tail.prev;
        prev.next = cur;
        cur.next = tail;
        tail.prev = cur;
        cur.prev = prev;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            keyValMap.put(key, value);
            return;
        }
        if (keyValMap.size() == capacity) {
            System.out.println(key + "," + value + "," + head.next.val);
            ListNode delete = head.next;
            keyValMap.remove(delete.val);
            keyNodeMap.remove(delete.val);
            remove(delete);
        }
        ListNode cur = new ListNode(key);
        keyValMap.put(key, value);
        keyNodeMap.put(key, cur);
        moveToTail(cur);
    }
}
