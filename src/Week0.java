import java.util.Stack;

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
        System.out.println(valStack.peek());
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

}
