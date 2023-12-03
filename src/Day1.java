import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Character.isDigit;

public class Day1 {

    Scanner scanner;
    Scanner fileReader;
    String possibleWords[] = {"xxxxxxxxx", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public Day1(){
        scanner = new Scanner(System.in);
    }

    public void readInput(){
        System.out.print("Enter File Name: ");
        String filename = scanner.nextLine();
        File file = new File(filename);
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        int runningTotal = 0;
        while (fileReader.hasNext()){
            int current = parseLine(fileReader.nextLine());
            System.out.println(current);
            runningTotal += current;
        }
        System.out.println(runningTotal);

    }

    public int parseLine(String s){
        for (int i = 0; i < 10; i++){
            s = s.replace(possibleWords[i],possibleWords[i] + i + possibleWords[i]);
        }
        System.out.println(s);

        int firstDigit = -1;
        int firstDigitIndex = -1;
        int secondDigit = -1;
        int secondDigitIndex = -1;


        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            int digit = Character.getNumericValue(currentChar);
            if(isDigit(currentChar)){
                if (firstDigit == -1){
                    firstDigit = digit;
                    firstDigitIndex = i;
                } else{
                    secondDigit = digit;
                    secondDigitIndex = i;
                }
            }
        }

        if(secondDigit == -1){
            secondDigit = firstDigit;
        }

        String str = String.valueOf(firstDigit) + String.valueOf(secondDigit);
        int total = Integer.parseInt(str);

        return  total;
    }



    public static void main(String[] args){
        Day1 main = new Day1();
        main.readInput();
    }
}
