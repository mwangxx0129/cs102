public class JumpGame {
    public static void main(String[] args) {
        int[] arr = {2,3,1,1,4};
        jump(arr);
    }
    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int[] min = new int[n];
        min[0] = 0;
        int curIndex = 0;
        for (int i = 1; i < nums.length; ++ i) {
            for (int j = curIndex; j < i; ++ j) {
                if (nums[j] + j >= i) {
                    min[i] = min[j] + 1;
                    curIndex = j;
                    break;
                }
            }
        }
        return min[nums.length - 1];
    }
}
