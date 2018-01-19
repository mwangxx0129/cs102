import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Week3Test {
    @Test
    void permutate() {
        Week3 w3 = new Week3();
//        int[] nums = {1,2,3};
//        w3.permutate(nums);

//        String[] dic = {"hot","dot","dog","lot","log","cog"};
//        List<String> list = new ArrayList<>();
//        for (String s : dic) {
//            list.add(s);
//        }
//        int step = w3.ladderLength("hit", "cog", list);
//        System.out.println(step);
//
//        char[][]  grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char[][]  grid = {{'1'},{'1'}};

        int res  = w3.numIslands_uf(grid);
        System.out.println(res);
    }

}