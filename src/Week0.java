import java.util.*;

public class Week0 {
    public Integer Evaluate_Reverse_Polish_Notation(String []tokens) {
//        String[] default_tokens = {"1","2","+","3","*"};
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; ++ i) {
            switch (tokens[i]) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case  "-":
                    stack.push(stack.pop() - stack.pop());
                    break;
                case  "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case  "/":
                    stack.push(stack.pop() / stack.pop());
                    break;
                default:
                    stack.push(Integer.parseInt(tokens[i]));
            }

        }
//        System.out.println(stack.peek());
        return stack.peek();
    }

    public Integer ERPNInFix(char[] tokens) {
        String s = "((3+4)*5-4*2)+1";
        tokens = s.toCharArray();
        Stack<Character> opStack = new Stack<>();
        Stack<Integer> valStack = new Stack<>();
        for (int i = 0; i < tokens.length; ++ i) {
            if (tokens[i] == '(') {
                // offer
                opStack.push(tokens[i]);
            } else if(tokens[i] == ')') {
                // poll + cal
                while (opStack.peek() != '(') {
                    valStack.push(cal(opStack.pop(), valStack.pop(), valStack.pop()));
                }
                opStack.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!opStack.isEmpty() && isLowerThan(tokens[i], opStack.peek())) {
                    // Lower than top
                    valStack.push(cal(opStack.pop(), valStack.pop(), valStack.pop()));
                }
                opStack.push(tokens[i]);

            } else {
                valStack.push(Integer.parseInt(String.valueOf(tokens[i])));
            }
        }
        while (!opStack.isEmpty()) {
            valStack.push(cal(opStack.pop(), valStack.pop(), valStack.pop()));
        }
//        System.out.println(valStack.peek());
        return valStack.peek();
    }

    public boolean isLowerThan(char cur, char toPeek) {
        return (toPeek == '*' || toPeek == '/') && (cur == '+' || cur == '-');
    }

    public int cal(char Oper, int first, int second) {
        int res = 0;
        switch (Oper) {
            case '+':
                res = second + first;
                break;
            case '-':
                res = second - first;
                break;
            case '*':
                res = second * first;
                break;
            case '/':
                res = second / first;
                break;
        }
        return res;
    }

    public int House_Robber(int []nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int size = nums.length;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < size; ++ i) {
            dp[(i + 1) & 0x1] = Math.max(dp[(i - 1) & 0x1] + nums[i], dp[i &0x1]);
        }
        return dp[size &0x1];
    }

    public int House_Robber_II(int []nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int res = Math.max(House_Robber_Generic(nums, 0, nums.length - 2), House_Robber_Generic(nums, 1, nums.length - 1));
        System.out.println(res);
        return res;
    }

    public int House_Robber_Generic(int []nums, int start, int end) {
        int[] dp = new int[2];
        dp[start % 2] = nums[start];
        for (int i = start + 1; i <= end; ++ i) {
            dp[i % 2] = Math.max(dp[i % 2] + nums[i], dp[(i - 1) % 2]);
        }
        return dp[end &0x1];
    }

    private void constructGraph(int[][] edges, List<List<Integer>> graph, int n) {
//        Create List
        for (int i = 0; i < n; ++ i) {
            graph.add(new ArrayList<Integer>());
        }
//        Add neighbors
        for(int i = 0; i < edges.length; ++ i) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
    }
    public int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return n;
        }

        List<List<Integer>> graph = new ArrayList<>();
        constructGraph(edges, graph, n);
        boolean[] isVisited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; ++ i) {
            if (!isVisited[i]) {
                ++ count;
//                dfsHelper(i, graph,isVisited);
                bfsHelper(i, graph,isVisited);
            }
        }
        System.out.println(count);
        return count;
    }

    private void dfsHelper(int i, List<List<Integer>> graph, boolean[] isVisited){
        isVisited[i] = true;
        List<Integer> neighbors =  graph.get(i);
        for (int n: neighbors) {
            if (!isVisited[n]) {
                dfsHelper(n, graph, isVisited);
            }
        }
    }

    private void bfsHelper(int i, List<List<Integer>> graph, boolean[] isVisited) {
        Queue<Integer> q = new LinkedList<>();
        isVisited[i] = true;
        q.offer(i);
        while(!q.isEmpty()) {
            int head = q.poll();
            List<Integer> neighbors =  graph.get(head);
            for (int n: neighbors) {
                if (!isVisited[n]) {
                    isVisited[n] = true;
                    q.offer(n);
                }
            }
        }
    }

    public int countComponents_UnionFind(int n, int[][] edges) {
        // define
        int[] father = new int[n];
        int count = n;
        // init
        for (int i = 0; i < n; ++ i) {
            father[i] = i;
        }

        for (int[] edge: edges) {
            if (union(edge[0], edge[1], father)) {
                count --;
            }
        }

        System.out.println(count);
        return  -1;
    }

    private int find(int x, int[] father) {
        int fa = father[x];
        if (fa == x) return fa;
        return father[x] = find(father[fa], father);
    }

    private boolean union(int a, int b, int[] father) {
        int fa = find(a, father);
        int fb = find(b, father);
        if (fa != fb) {
            father[fa] = fb;
            return true;
        }
        return false;
    }
}
