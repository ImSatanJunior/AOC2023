import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    Scanner scanner;
    Scanner fileReader;

    public Day4(){
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

        int numCopies[] = new int[196];

        /**while (fileReader.hasNext()){
            runningTotal += parseLine(fileReader.nextLine());
        } **/
        for(int i = 0; i < 196; i++){
            numCopies[i] = numCopies[i] + 1;
            runningTotal = parseLine(fileReader.nextLine());
            int j = i + 1;
            while (runningTotal > 0){
                numCopies[j] += numCopies[i];
                runningTotal--;
                j++;
            }
        }
        int answer = 0;
        for(int i = 0; i < 196; i++){
            answer += numCopies[i];
        }

        System.out.println(answer);
    }

    public int parseLine(String currentLine){
        String strings[] = currentLine.split(" ");
        ArrayList<String> stringArrayList = new ArrayList<>();

        ArrayList<Integer> winningNumbers = new ArrayList<>();
        List<Integer> scratchCardNumbers = new ArrayList<>();
        int max = 11;
        for(int i = 2; i <= max; i++){
            //System.out.println(strings[i]);
            if(strings[i] == ""){
                max++;
            } else {
                //System.out.println(strings[i]);
                winningNumbers.add(Integer.parseInt(strings[i]));
            }

        }

        int numberMatches = 0;

        for(int i = max + 2; i < strings.length; i++){
            if(strings[i] != ""){
                int currentDigit = Integer.parseInt(strings[i]);
                if(winningNumbers.contains(currentDigit)){
                    numberMatches++;
                }
            }
        }
        /**int answer = 0;
        if(numberMatches != 0){
            answer = (int) Math.pow(2, numberMatches - 1);
        } **/

        System.out.println(numberMatches);
        return numberMatches;
    }

    public static void main(String[] args){
        Day4 main = new Day4();
        main.readInput();
    }
}