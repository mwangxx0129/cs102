import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class LinearDataStructTest {
    LinearDataStruct lds = new LinearDataStruct();
    @Test
    void myToString() {
        int[] a = {1,2,3};

        System.out.println(Arrays.toString(a));
        System.out.println(lds.myToString(a));
        assertEquals(Arrays.toString(a), lds.myToString(a));
    }

}