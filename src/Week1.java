import java.util.*;

public class Week1 {
    public int[] twoSum_method_1(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length < 2) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++ i) {
            if (map.containsKey(nums[i])) {
                res[0] = map.get(nums[i]);
                res[1] = i;
                return res;
            } else {
                map.put(target - nums[i], i);
            }
        }
        return res;
    }

    public int[] twoSum_method_2(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length < 2) {
            return res;
        }
        Arrays.sort(nums);
        int head = 0, tail = nums.length - 1;
        while (head < tail) {
            if (nums[head] + nums[tail] == target) {
                res[0] = head;
                res[1] = tail;
                return res;
            } else if (nums[head] + nums[tail] < target) {
                ++ head;
            } else {
                -- tail;
            }
        }
        return res;
    }

    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length < 2) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < tokens.length; ++ i) {
            switch (tokens[i]) {
                case "+":
                    stack.offerLast(stack.pollLast() + stack.pollLast());
                    break;
                case "-":
                    int a1 = stack.pollLast();
                    int b1 = stack.pollLast();
                    stack.offerLast(b1 - a1);
                    break;
                case "*":
                    stack.offerLast(stack.pollLast() * stack.pollLast());
                    break;
                case "/":
                    int a2 = stack.pollLast();
                    int b2 = stack.pollLast();
                    stack.offerLast(b2 / a2);
                    break;
                default:
                    stack.offerLast(Integer.parseInt(tokens[i]));
                    break;
            }
        }
        return stack.pollLast();
    }

    public int evalRP_generic(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Deque<Integer> valStack = new ArrayDeque<>();
        Deque<String > opStack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; ++ i) {
            if(tokens[i] == "(") {
                opStack.offerLast(tokens[i]);
            } else if(tokens[i] == ")") {
                while (opStack.peekLast() != "(") {
                    valStack.offerLast(cal(opStack.pollLast(), valStack.pollLast(), valStack.pollLast()));
                }
                opStack.pollLast(); // poll (
            } else if (tokens[i] == "+" || tokens[i] == "-" || tokens[i] == "*" || tokens[i] == "/") {
                while (!opStack.isEmpty() && isLowerThan(tokens[i], opStack.peekLast())) {
                    valStack.offerLast(cal(opStack.pollLast(), valStack.pollLast(), valStack.pollLast()));
                }
                opStack.offerLast(tokens[i]);
            } else {
                valStack.offerLast(Integer.parseInt(tokens[i]));
            }
        }
        while (!opStack.isEmpty()) {
            valStack.offerLast(cal(opStack.pollLast(), valStack.pollLast(),valStack.pollLast()));
        }
        return valStack.pollLast();
    }

    public boolean isLowerThan(String op, String peekOp) {
        return (peekOp == "*" || peekOp == "/") && (op == "+" || op == "-");
    }

    public int cal(String op, int first, int second) {
        switch (op) {
            case "+":
                return second + first;
            case "-":
                return second - first;
            case "*":
                return second * first;
            case "/":
                return second / first;
            default:
                return 0;
        }
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) {
            return -1;
        }
        if (gas.length != cost.length) {
            return -1;
        }
        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];
        while (end < start) {
            if (sum < 0) {
                sum += (gas[++ start] - cost[start]);
            } else {
                sum += (gas[end] - cost[end --]);
            }
        }
        return sum >= 0 ? start : -1;
    }

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return new String();
        }
        int r = 0;
        int L = 0, R = 0, min = Integer.MAX_VALUE;
        int [] map = new int[128];
        int count = 0;

        for (int i = 0; i < t.length(); ++ i) {
            // In case T has duplicate character
            if (map[t.charAt(i)] ++ == 0) {
                ++ count;
            }
        }

        char[] sCh = s.toCharArray();
        for (int l = 0; l < sCh.length; ++ l) {
            while(r < sCh.length && count > 0) {
                if ( -- map[sCh[r ++]] == 0) {
                    -- count;
                }
            }
            if (count == 0 && min > r - l) {
                L = l;
                R = r;
                min = r - l;
            }
            if (map[sCh[l]] ++ == 0)
                ++ count;
        }
        return s.substring(L, R);
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public ListNode partition(ListNode head, int x) {

        ListNode lessPre = new ListNode(-1);
        ListNode greaterPre = new ListNode(-1);
        ListNode lessDummy = lessPre;
        ListNode greaterDummy = greaterPre;

        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                lessPre.next = cur;
                lessPre = lessPre.next;
            } else {
                greaterPre.next = cur;
                greaterPre = greaterPre.next;
            }
            cur = cur.next;
        }
        lessPre.next = greaterDummy.next;
        greaterPre.next = null;

        return lessDummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(-1);
        ListNode slow = dummy;
        ListNode fast = dummy;
        dummy.next = head;

        int count = n;
        while (count != 0) {
            -- count;
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public void sortColor(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int l = 0, r = arr.length - 1;
        int i = 0;
        while (i <= r) {
            if (arr[i] < 1) {
                swap(arr, l ++, i ++);
            } else if (arr[i] == 1) {
                ++ i;
            } else {
                swap(arr, i, r --);
            }
        }
    }

    private void swap(int[] arr, int l, int r) {
        if (arr[l] != arr[r]) {
            arr[l] ^= arr[r];
            arr[r] ^= arr[l];
            arr[l] ^= arr[r];
        }
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] sCh = s.toCharArray();
        for (char c : sCh) {
            switch (c) {
                case '(':
                    stack.offerLast(')');
                    break;
                case '[':
                    stack.offerLast(']');
                    break;
                case '{':
                    stack.offerLast('}');
                    break;
                default:
                    if (stack.isEmpty() || stack.pollLast() != c) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

    public void reorderList(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return ;
        }
        // 1. find mid (odd even)
        ListNode mid = findMid(head);
        ListNode secondHead = mid.next;
        mid.next = null;

        // 2. reverse second part
        ListNode secondDummy = new ListNode(-1);

        // 3. merge two list
        merge(head, secondDummy.next);

    }
    private void printListNode(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    private ListNode findMid(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println("slow: " + slow.val);
        return slow; // slow is the last element in first part
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    private void merge(ListNode firstHead, ListNode secondHead) {
        ListNode dummy = new ListNode(-1);
        dummy.next = firstHead;
        ListNode first = firstHead;
        ListNode second = secondHead;
        ListNode pre = dummy;
        while (first != null && second != null) {
            pre.next = first;
            first = first.next;
            pre = pre.next;

            pre.next = second;
            second = second.next;
            pre = pre.next;
        }
        pre.next = first;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        int count = k;

        while (head != null) {
            -- count;
            if (count == 0) {
                pre = reverse(pre, head.next);
                head = pre.next;
                count = k;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode preHead, ListNode nextHead) {
        ListNode tail = preHead.next;
        ListNode cur = tail.next;
        while(cur != nextHead) {
            ListNode tmp = cur.next;
            cur.next = preHead.next;
            preHead.next = cur;
            cur = tmp;
        }
        tail.next = nextHead;
        return tail;
    }

    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int j = 0;
        char[] sCh = s.toCharArray();
        int max = 0;
        for (int i = 0; i < sCh.length; ++ i) {
            while(j < sCh.length && map[sCh[j]] == 0) {
                ++ map[sCh[j]];
                ++ j;
            }
            if (j == sCh.length || map[sCh[j]] == 1) {
                max = Math.max(max, j - i);
            }
            map[sCh[i]] = 0;
        }
        return max;
    }

    class MovingAverage {
        private Deque<Integer> queue;
        private int sum;
        private int size;
        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            queue = new ArrayDeque<>();
            this.size = size;
            this.sum = 0;
        }

        public double next(int val) {

            if (queue.size() == size) {
                sum -= queue.pollFirst();
            }
            queue.offerLast(val);
            sum += val;
            return 1.0 * sum / queue.size();
        }
    }

    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        Deque<String> deck = new ArrayDeque<>();
        for (String s : paths) {
            switch (s) {
                case ".":
                    //nothing
                    break;
                case "..":
                    deck.pollLast();
                    break;
                default:
                    if (s.length() > 0 )
                        deck.offerLast(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        while(!deck.isEmpty()) {
            sb.append(deck.pollFirst());
            if (deck.isEmpty()) {
                break;
            }
            sb.append("/");
        }
        return sb.toString();
    }

    public String simplifyPath_method_II(String path) {
        if (path == null || path.length() == 0) {
            return new String();
        }
        String[] paths = path.split("/");
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = paths.length - 1; i >= 0; -- i) {
            if (paths[i].equals("..")) {
                ++ count;
            } else {
                if (!paths[i].equals(".") && !paths[i].equals("")) {
                    if (count > 0) {
                        -- count;
                    } else {
                        sb.insert(0, "/" + paths[i]);
                    }
                }
            }
        }

        return sb.length() == 0 ? "/" : sb.toString();

    }

    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; ++ i) {
            int num = i == heights.length ? -1 : heights[i];
            while (!stack.isEmpty() && num < heights[stack.peekLast()]){
                int h = heights[stack.pollLast()];
                int w = i - (stack.isEmpty() ? -1 : stack.peekLast()) - 1;
                maxArea = Math.max(h * w, maxArea);
            }
            stack.offerLast(i);
        }
        return maxArea;
    }

    public int trap_two_pointer(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int Area = 0;
        for (int i = 0; i < height.length; ++ i) {
            int num = height[i];
            while(!stack.isEmpty() && height[stack.peekLast()] < num) {
                int h = height[stack.pollLast()];
                if (!stack.isEmpty()) {
                    int width = i - stack.peekLast() - 1;
                    int high = Math.min(height[stack.peekLast()], num) -  h;
                    Area += width * high;
                }
            }
            stack.offerLast(i);
        }
        return Area;
    }

    public int trap_stack(int[] height) {
        int l = 0, r = height.length - 1;
        int area = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                area += Math.max(height[l] - height[l + 1], 0);
                height[l + 1] = Math.max(height[l], height[++ l]);
            } else {
                area += Math.max(height[r] - height[r - 1], 0);
                height[r - 1] = Math.max(height[r], height[-- r]);
            }
        }
        return area;
    }

    public List<List<Integer>> threeSum(int[] array) {
        List<List<Integer>> res = new ArrayList<>();
        if (array == null || array.length < 3) {
            return res;
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length - 2; ) {
            int fix = array[i];
            int l = i + 1;
            int r = array.length - 1;
            while (l < r) {
                if (array[l] + array[r] + fix == 0) {
                    List<Integer> sub = new ArrayList<>();
                    sub.add(fix);
                    sub.add(array[l ++]);
                    sub.add(array[r --]);
                    res.add(sub);
                    // skip duplicate
                    while (l < r && array[l] == array[l - 1]) {
                        l ++;
                    }
                    while (l < r && array[r] == array[r + 1]) {
                        r --;
                    }
                } else if (array[l] + array[r] + fix < 0){
                    r --;
                } else {
                    l ++;
                }
            }
            ++ i;
            // skip duplicate for fix
            while (i < array.length - 2 && array[i] == array[i - 1]) {
                ++ i;
            }
        }
        return res;
    }
}
