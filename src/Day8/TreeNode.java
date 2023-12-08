package Day8;

public class TreeNode {
    private String currentLocation;
    private TreeNode leftNode;
    private TreeNode rightNode;


    public TreeNode(String currentLocation, TreeNode left, TreeNode right) {
        this.currentLocation = currentLocation;
        this.leftNode = left;
        this.rightNode = right;
    }


    public TreeNode getLeft() {
        return leftNode;
    }


    public TreeNode getRight() {
        return rightNode;
    }


    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
}
