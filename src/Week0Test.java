import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Week0Test {
    @Test
    void countComponents_UnionFind() {
        int[][] edges = {
                {0, 1},
                {0, 2},
                {2, 3},

                {4, 5},
                {5, 6},
                {4, 6}
        };
        int n = 8;
        week0.countComponents_UnionFind(n, edges);
    }

    @Test
    void countComponents() {
        int[][] edges = {
                {0, 1},
                {0, 2},
                {2, 3},

                {4, 5},
                {5, 6},
                {4, 6}
        };
        int n = 8;
        week0.countComponents(n, edges);
    }

    @Test
    void house_Robber_II() {
        int[] nums = {1, 4, 9, 4};

        week0.House_Robber_II(nums);
    }

    Week0 week0 = new Week0();

    @Test
    void house_Robber() {
        int[] nums = {1, 4, 9, 4};
        week0.House_Robber(nums);
    }


    @Test
    void ERPNInFix() {
        String s = "((3+4)*5-4*2)+1";
        week0.ERPNInFix(s.toCharArray());
    }

    @Test
    void evaluate_Reverse_Polish_Notation() {
        String[] s = {"1","2","+","3","*"};
        week0.Evaluate_Reverse_Polish_Notation(s);
    }

}