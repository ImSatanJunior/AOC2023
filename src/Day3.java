import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Day3 {
    Scanner scanner;
    Scanner fileReader;

    public Day3(){
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

        int xMax = 140;
        int yMax = 140;

        char[][] characters = new char[yMax][xMax];
        for(int y = 0; y < yMax; y++){
            String currentLine = fileReader.nextLine();
            for(int x = 0; x < xMax; x++){
                char currentCharacter = currentLine.charAt(x);
                //System.out.print(currentCharacter+ ",");
                characters[y][x] = currentCharacter;
            }
            //System.out.println();
        }

        int[][] gears = new int[yMax][xMax];

        for(int y = 0; y < yMax; y++){
            String currentDigit = null;
            int digitStart = -1;
            int digitEnd = -1;
            for(int x = 0; x < xMax; x++) {

                char currChar = '.';

                if (characters[y][x] == '.') {
                    //Do Nothing
                } else {
                    currChar = characters[y][x];
                }
                //System.out.println(currChar);

                if (Character.isDigit(currChar)) {
                    //System.out.println(currChar);
                    //System.out.println("a");
                    if (currentDigit == null) {
                        currentDigit = String.valueOf(currChar);
                        digitStart = x;
                    } else {
                        currentDigit += currChar;
                    }

                    digitEnd = x;
                }

                if (!Character.isDigit(currChar) || (x == (xMax - 1)) && Character.isDigit(currChar)) {
                    if (currentDigit != null) {
                        //System.out.println(y);
                        //System.out.println(currentDigit);

                        boolean hasSymbol = false;

                        int digit = Integer.parseInt(currentDigit);

                        //System.out.println(digit);
                        int xStart = digitStart;
                        //System.out.println(xStart);
                        int xEnd = digitEnd;
                        //System.out.println(xEnd);

                        if (xStart > 0) {
                            xStart--;
                        }

                        if (xEnd < xMax - 1) {
                            xEnd++;
                        }
                        int answer = 0;
                        if (y > 0) {
                            for (int i = xStart; i <= xEnd; i++) {
                                char character = characters[y - 1][i];

                                if (character == '*') {
                                    if (gears[y - 1][i] == 0) {
                                        System.out.println(digit);
                                        System.out.println("here");
                                        gears[y - 1][i] = digit;
                                    } else {
                                        gears[y - 1][i] = gears[y - 1][i] * digit;
                                        answer = gears[y - 1][i];
                                        hasSymbol = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if (y < (yMax - 1)) {
                            for (int i = xStart; i <= xEnd; i++) {
                                char character = characters[y + 1][i];

                                if (character == '*') {
                                    if (gears[y + 1][i] == 0) {
                                        System.out.println(digit);
                                        System.out.println("here");
                                        gears[y + 1][i] = digit;
                                    } else {
                                        gears[y + 1][i] = gears[y + 1][i] * digit;
                                        answer = gears[y + 1][i];
                                        hasSymbol = true;
                                        break;
                                    }
                                }
                            }
                        }

                            if (!Character.isDigit(characters[y][xStart]) && characters[y][xStart] != '.') {
                                char character = characters[y][xStart];

                                if (character == '*') {
                                    //System.out.println(digit);
                                    if (gears[y ][xStart] == 0) {
                                        gears[y][xStart] = digit;
                                    } else {
                                        gears[y][xStart] = gears[y][xStart] * digit;
                                        answer = gears[y][xStart];
                                        hasSymbol = true;
                                    }
                                }
                            } else if (!Character.isDigit(characters[y][xEnd]) && characters[y][xEnd] != '.') {
                                    char chars = characters[y][xEnd];
                                    if (chars == '*') {
                                        //System.out.println(digit);
                                        if (gears[y][xEnd] == 0) {
                                            gears[y][xEnd] = digit;
                                        } else {
                                            gears[y][xEnd] = gears[y][xEnd] * digit;
                                            answer = gears[y][xEnd];
                                            hasSymbol = true;
                                        }
                                    }
                                }


                                    currentDigit = null;
                                    digitStart = -1;
                                    digitEnd = -1;

                                    if (hasSymbol) {
                                        System.out.println(answer);
                                        runningTotal += answer;
                                    }
                    }




                }

                }
            }
        System.out.println(runningTotal);
        }

    public int parseLine(String currentLine){
        return 0;
    }

    public static void main(String[] args){
        Day3 main = new Day3();
        main.readInput();
    }
}
