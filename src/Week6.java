import java.util.*;

public class Week6 {
    public static void main(String[] args) {
        String s = "AAABBB";
        char[] tasks = s.toCharArray();
        leastInterval(tasks, 2);
        int[] nums = {1, 1, 0};
        int k = 1;
        System.out.println(maxSubArrayLen(nums, k));
    }

    public static int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int max = 0;
        map.put(k, 0);
        for (int i = 0; i < nums.length; ++ i) {
            sum += nums[i];
            if (map.containsKey(sum)) {

                System.out.println(i + "," + sum + "," + map.get(sum));
                max = Math.max(max, i - map.get(sum) + 1);
            }
                map.putIfAbsent(sum + k, i + 1);

        }
        return max;
    }

    public static int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;

        int[] counts = new int[26];
        boolean[] visited = new boolean[26];
        for (int i = 0; i < tasks.length; ++ i) {
            ++ counts[tasks[i] - 'A'];
        }

        int cnt = 0;
        List<Integer> seq = new ArrayList<>();


        for (int num = tasks.length; num > 0; ) {
            int max = -1;

            // find a task
            for (int i = 0; i < 26; ++ i) {
                if (visited[i]) continue;
                if (counts[i] == 0) continue;
                if (max == -1 || counts[i] > counts[max]) {
                    max = i;
                }
            }
            System.out.println(max);

            if (max != -1) {
                -- num;
                visited[max] = true;
                -- counts[max];
            }

            // release the head
            if (cnt >= n && seq.get(cnt - n) >= 0) {
                visited[seq.get(cnt - n)] = false;
            }

            seq.add(max);
            ++ cnt;
        }
        return cnt;
    }
}
