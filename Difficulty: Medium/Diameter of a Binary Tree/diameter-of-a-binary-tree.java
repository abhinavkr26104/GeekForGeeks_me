class Solution {
    int maxDiameter = 0; // stores the maximum number of edges

    public int diameter(Node root) {
        height(root);
        return maxDiameter;
    }

    // Helper function to compute height of each subtree
    private int height(Node node) {
        if (node == null) return 0;

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // The diameter passing through this node = leftHeight + rightHeight
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return height (in edges, so +1 for current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
