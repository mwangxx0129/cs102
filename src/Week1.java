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
}
