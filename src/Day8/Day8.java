package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.google.common.math.LongMath.gcd;

public class Day8 {
    Scanner scanner;
    Scanner fileReader;
    String startPoint = "AAA";
    String endPoint = "ZZZ";

    ArrayList<TreeNode> treeNodes = new ArrayList<>();
    ArrayList<String> locations = new ArrayList<>();

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



        ArrayList<Integer> foundZ = new ArrayList<>();

        String currentLocation = null;
        int l = 0;
        TreeNode currentNode = null;


        for(int j = 0; j < locations.size(); j++){
            int i = 0;
            int numSteps = 0;
            if(locations.get(j).charAt(2) == 'A'){
                currentLocation = locations.get(j);
                l = locations.indexOf(currentLocation);
                currentNode = treeNodes.get(l);

                while (currentNode.getCurrentLocation().charAt(2) != 'Z'){

                    if (instructions.get(i).equals('L')){
                        currentLocation = currentNode.getLeft().getCurrentLocation();
                    } else if(instructions.get(i).equals('R')){
                        currentLocation = currentNode.getRight().getCurrentLocation();
                    } else {
                        System.err.println("Instruction Incorrect");
                    }

                    l = locations.indexOf(currentLocation);
                    currentNode = treeNodes.get(l);

                    i++;
                    if(i == instructions.size()){
                        i = 0;
                    }
                    numSteps++;
                }
                foundZ.add(numSteps);
            }

        }



        System.out.println(lcm(foundZ));
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(time + "ms");
    }

    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    private static long lcm(ArrayList<Integer> input)
    {
        long result = input.get(0);
        for(int i = 1; i < input.size(); i++){
            result = lcm(result, input.get(i));
        }
        return result;
    }

    public void parseLine(String currentLine) {
        String strings[] = currentLine.replaceAll("[()\\s]", "").split("=");
        String[] remainingStrings = strings[1].split(",");

        TreeNode leftNode = null;
        TreeNode rightNode = null;

        if(locations.contains(remainingStrings[0])){
            int i = locations.indexOf(remainingStrings[0]);
            leftNode = treeNodes.get(i);
        }

        if(locations.contains(remainingStrings[1])){
            int i = locations.indexOf(remainingStrings[1]);
            rightNode = treeNodes.get(i);
        }

        if(leftNode == null){
            leftNode = new TreeNode(remainingStrings[0], null, null);
        }
        if(remainingStrings[0].equals(remainingStrings[1])){
            rightNode = leftNode;
        } else if(rightNode == null){
            rightNode = new TreeNode(remainingStrings[1], null, null);
        }

        if(remainingStrings[0].equals(strings[0])){
            leftNode = null;
            rightNode = null;
        }

            TreeNode currentNode = new TreeNode(strings[0], null, null);
            if(leftNode == rightNode){
                currentNode.setLeftNode(leftNode);
                currentNode.setRightNode(leftNode);
            } else {
                currentNode.setLeftNode(leftNode);
                currentNode.setRightNode(rightNode);
            }

            treeNodes.add(currentNode);
            locations.add(strings[0]);





    }

    public static void main(String[] args){
        Day8 main = new Day8();
        main.readInput();
    }
}

