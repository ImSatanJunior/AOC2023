import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day9 {
    Scanner scanner;
    Scanner fileReader;

    public Day9(){
        scanner = new Scanner(System.in);
    }

    public void readInput() {
        System.out.print("Enter File Name: ");
        String filename = scanner.nextLine();
        File file = new File(filename);
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        int runningTotal = 0;
        while(fileReader.hasNext()){
            runningTotal += parseLine(fileReader.nextLine());
        }
        System.out.println(runningTotal);
    }

    public int parseLine(String currentLine){
        String strings[] = currentLine.split(" ");
        ArrayList<ArrayList<Integer>> sequences = new ArrayList<>();
        ArrayList<Integer> currentSequence = new ArrayList<>();

        for (int i = 0; i < strings.length; i++) {
            currentSequence.add(Integer.parseInt(strings[i]));
        }
        sequences.add(currentSequence);



        boolean allZeroes = true;
        boolean exit = true;

        int previousSequence = 0;

        while(exit){
            currentSequence = new ArrayList<>();
            for(int i = 0; i < sequences.get(previousSequence).size() - 1; i++){
                int currentDifference = sequences.get(previousSequence).get(i + 1) - sequences.get(previousSequence).get(i);

                if(currentDifference != 0){
                    allZeroes = false;
                }
                currentSequence.add(currentDifference);
            }

            sequences.add(currentSequence);

            if(allZeroes){
                exit = false;
            } else {
                allZeroes = true;
                previousSequence++;
            }

        }

        int numSequences = sequences.size() - 1;
        int newDifference = 0;
        newDifference = sequences.get(numSequences).get(0);

        sequences.get(numSequences).add(0);

        for(int i = numSequences - 1; i >= 0; i--){
            int currentDifference = sequences.get(i).get(0);

            newDifference = currentDifference - newDifference;
        }

        return newDifference;
    }

    public static void main(String[] args){
        Day9 main = new Day9();
        main.readInput();
    }
}
