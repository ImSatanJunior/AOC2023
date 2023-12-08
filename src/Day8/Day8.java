package Day8;

import com.sun.source.tree.BinaryTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8 {
    Scanner scanner;
    Scanner fileReader;
    String startPoint = null;
    String endPoint = null;
    BinaryTrees binaryTree;

    ArrayList<TreeNode> nodesNotInTree = new ArrayList<>();
    ArrayList<String> locationsNotInTree = new ArrayList<>();

    public Day8(){
        scanner = new Scanner(System.in);
    }

    public void readInput() {
        System.out.print("Enter File Name: ");
        String filename = scanner.nextLine();
        File file = new File(filename);
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();

        ArrayList<Character> instructions = new ArrayList<>();
        String instructionList = fileReader.nextLine();;
        for (int i = 0; i < instructionList.length(); i++) {
            instructions.add(instructionList.charAt(i));
        }

        while (fileReader.hasNext()) {
            parseLine(fileReader.nextLine());
        }


        while (!nodesNotInTree.isEmpty()){

        }

        int i = 0;
        int numSteps = 0;

        TreeNode currentNode = binaryTree.root;
        while (!currentNode.getCurrentLocation().equals(endPoint)){
            if (instructions.get(i).equals('L')){
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
            i++;
            i = i % instructions.size();
            numSteps++;
        }

        System.out.println(numSteps);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(time + "ms");
    }

    public void parseLine(String currentLine) {
        String strings[] = currentLine.replaceAll("[()\\s]", "").split("=");
        String[] remainingStrings = strings[1].split(",");

        TreeNode leftNode = null;
        TreeNode rightNode = null;

        if(binaryTree != null){
            //Left Already Exists In Tree
            if(binaryTree.findNode(remainingStrings[0]) != null){
                leftNode = binaryTree.foundNode;
            }

            //Right Already Exists In Tree
            if(binaryTree.findNode(remainingStrings[1]) != null){
                rightNode = binaryTree.foundNode;
            }
        }

        if(leftNode == null){
            if(locationsNotInTree.contains(remainingStrings[0])){
                int i = locationsNotInTree.indexOf(remainingStrings[0]);
                leftNode = nodesNotInTree.get(i);
                locationsNotInTree.remove(i);
                nodesNotInTree.remove(i);
            }
        }

        if(rightNode == null){
            if(locationsNotInTree.contains(remainingStrings[1])){
                int i = locationsNotInTree.indexOf(remainingStrings[1]);
                leftNode = nodesNotInTree.get(i);
                locationsNotInTree.remove(i);
                nodesNotInTree.remove(i);
            }
        }



        if(remainingStrings[0].equals(remainingStrings[1]) && leftNode == null){
            leftNode = new TreeNode(remainingStrings[0], null, null);
            rightNode = leftNode;

        } else {
            if(leftNode == null){
                leftNode = new TreeNode(remainingStrings[0], null, null);
            }
            if(rightNode == null){
                rightNode = new TreeNode(remainingStrings[1], null, null);
            }

        }

        if(remainingStrings[0].equals(strings[0])){
            leftNode = null;
            rightNode = null;
        }

        if(startPoint == null){
            startPoint = strings[0];
            binaryTree = new BinaryTrees(new TreeNode(strings[0], null, null));
            if(leftNode == rightNode){
                binaryTree.root.setLeftNode(leftNode);
                binaryTree.root.setRightNode(leftNode);
            } else {
                binaryTree.root.setLeftNode(leftNode);
                binaryTree.root.setRightNode(rightNode);
            }

        } else {
            TreeNode currentNode = binaryTree.findNode(strings[0]);
            if(currentNode == null){
               currentNode = new TreeNode(strings[0], null, null);
               nodesNotInTree.add(currentNode);
               locationsNotInTree.add(strings[0]);
            }


            if(leftNode == rightNode){
                currentNode.setLeftNode(leftNode);
                currentNode.setRightNode(leftNode);
            } else {
                currentNode.setLeftNode(leftNode);
                currentNode.setRightNode(rightNode);
            }
            endPoint = strings[0];
        }




    }

    public static void main(String[] args){
        Day8 main = new Day8();
        main.readInput();
    }
}

