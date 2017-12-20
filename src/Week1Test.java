import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Week1Test {
    @Test
    void evalRP_generic() {
        String[] tokens = {"(", "4", "+", "3", ")", "*", "2", "+", "1"};
        int res = week1.evalRP_generic(tokens);
        System.out.println(res);
    }

    @Test
    void evalRPN() {
        String[] tokens = {"3", "1", "+", "4", "*"};
        int res = week1.evalRPN(tokens);
        System.out.println(res);
    }

    Week1 week1 = new Week1();
    @Test
    void twoSum_method_1() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int[] res = week1.twoSum_method_1(nums, 9);
        System.out.println(Arrays.toString(res));
    }

    @Test
    void twoSum_method_2() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int[] res = week1.twoSum_method_1(nums, 10);
        System.out.println(Arrays.toString(res));
    }

}