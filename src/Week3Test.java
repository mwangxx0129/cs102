import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Week3Test {
    @Test
    void permutate() {
        Week3 w3 = new Week3();
        int[] nums = {1,2,3,4,5};
        w3.permutate_dfs(nums);
    }

}