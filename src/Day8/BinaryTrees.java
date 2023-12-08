package Day8;

import java.util.ArrayList;

public class BinaryTrees {

    public TreeNode root;

    public BinaryTrees(TreeNode root) {
        this.root = root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    TreeNode foundNode;

    public TreeNode findNode(String location){
        foundNode = null;
        treeTraversal(root, location);
        return foundNode;
    }

    ArrayList<TreeNode> travelledThrough = new ArrayList<>();

    private void treeTraversal(TreeNode currentNode, String location){

        if(currentNode.getCurrentLocation().equals(location)){
            foundNode = currentNode;
        }

        if(currentNode.getLeft() != null && !travelledThrough.contains(currentNode)){
            travelledThrough.add(currentNode);
            treeTraversal(currentNode.getLeft(), location);
        }

        if(currentNode.getRight() != null){
            treeTraversal(currentNode.getRight(), location);
        }
    }
}
