public class SerializeBST {
    public static void main(String[] args) {
        Codec c = new Codec();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        String input = c.serialize(root);
        System.out.println(input);
        String test = "1+i";
        System.out.println(test.split("/+"));
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(" N");
            return;
        }
        sb.append(" " + root.val);
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.trim().split("\\s+");
        return buildTree(tokens, new int[]{0});
    }
    private TreeNode buildTree(String[] tokens, int[] pos) {
        if(tokens[pos[0]].equals("N")) return null;
        if (pos[0] == tokens.length) return null;
        TreeNode root = new TreeNode(Integer.parseInt(tokens[pos[0]]));
        pos[0] ++;
        root.left = buildTree(tokens, pos);
        pos[0] ++;
        root.right = buildTree(tokens, pos);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));