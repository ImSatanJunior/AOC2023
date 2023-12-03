import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Character.isDigit;

public class Day2 {
    Scanner scanner;
    Scanner fileReader;
    HashMap<String, Integer> values;

    public Day2(){
        scanner = new Scanner(System.in);
        values = new HashMap<>();
        values.put("red", 12);
        values.put("red,", 12);
        values.put("red;", 12);
        values.put("blue", 14);
        values.put("blue,", 14);
        values.put("blue;", 14);
        values.put("green", 13);
        values.put("green,", 13);
        values.put("green;", 13);
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
        //int i = 1;
        while(fileReader.hasNext()){

            runningTotal += parseLine(fileReader.nextLine());

        }
        System.out.println(runningTotal);
    }

    public int parseLine(String currentLine){
        String currentColour;
        int colourCubes;
        String[] strings = currentLine.split(" ");
        int digits[] = new int[strings.length / 2];
        int currentDigit = 0;
        String colours[] = new String[digits.length];
        int colour = 0;

        for(int i = 2; i < strings.length; i++){

            if (i % 2 == 0){
                digits[currentDigit] = Integer.parseInt(strings[i]);
                currentDigit++;
            } else {
                colours[colour] = strings[i];
                colour++;
            }
        }

        int maxRed = 0;
        int maxBlue = 0;
        int maxGreen = 0;

        int currentRed = 0;
        int currentBlue = 0;
        int currentGreen = 0;

        for(int i = 0; i < digits.length; i++){
            //int max = 1000000000;

            if(colours[i] == null){
                break;
            }

            System.out.println(colours[i]);
            System.out.println(digits[i]);
            //max = values.get(colours[i]);


            if(colours[i].contains("red")){
                currentRed += digits[i];
            } else if(colours[i].contains("blue")){
                currentBlue += digits[i];
            } else if(colours[i].contains("green")){
                currentGreen += digits[i];
            }

            if(colours[i].contains(";")){
                if(currentRed > maxRed){
                    maxRed = currentRed;
                }
                if(currentBlue > maxBlue){
                    maxBlue = currentBlue;
                }
                if(currentGreen > maxGreen){
                    maxGreen = currentGreen;
                }
                currentRed = 0;
                currentBlue = 0;
                currentGreen = 0;
            }


        }

        if(currentRed > maxRed){
            maxRed = currentRed;
        }
        if(currentBlue > maxBlue){
            maxBlue = currentBlue;
        }
        if(currentGreen > maxGreen){
            maxGreen = currentGreen;
        }

        return maxRed * maxBlue * maxGreen;
    }

    public static void main(String[] args){
        Day2 main = new Day2();
        main.readInput();
    }
}
