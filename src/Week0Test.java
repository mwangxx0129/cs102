import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Week0Test {
    Week0 week0 = new Week0();

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