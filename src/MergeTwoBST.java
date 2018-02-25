
public class MergeTwoBST {
    public static void main() {

    }
    public static void merge(Node root1 , Node root2)
    {
        //Your code here
        Deque<Node> stack1 = new ArrayDeque<>();
        Deque<Node> stack2 = new ArrayDeque<>();
        stack1.addLeft(root1);
        stack2.addLeft(root2);

        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            if (stack1.peekLast() < stack2.peekLast()) {
                Node cur = stack1.pollFirst();
                if (cur.right != null) {
                    stack1.pollLast(cur.right);
                }
                System.out.print(cur.data + " ");
            } else {
                Node cur = stack2.pollFirst();
                if (cur.right != null) {
                    stack2.pollLast(cur.right);
                }
                System.out.print(cur.data + " ");
            }
        }
    }

    private static void addLeft(Node root, Deque<Node> stack) {
        if (root == null) return;
        Node cur = root;
        while (cur != null) {
            stack.offerLast(cur);
            cur = cur.left;
        }
    }
}
class Node {
    int data;
    Node left, right;
    Node (int data) {
        this.data = data;
        left = right =
    }
}