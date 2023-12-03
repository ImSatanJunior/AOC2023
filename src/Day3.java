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

        int xMax = 10;
        int yMax = 10;

        char[][] characters = new char[yMax][xMax];
        for(int y = 0; y < yMax; y++){
            String currentLine = fileReader.nextLine();
            for(int x = 0; x < xMax; x++){
                char currentCharacter = currentLine.charAt(x);
                //System.out.println(currentCharacter);
                if (currentCharacter != '.'){
                    characters[y][x] = currentCharacter;
                } else {
                    characters[y][x] = 'a';
                }
            }
        }

        for(int y = 0; y < yMax; y++){
            String currentDigit = null;
            int digitStart = -1;
            int digitEnd = -1;
            for(int x = 0; x < xMax; x++){

                char currChar = 'a';

                if(characters[y][x] == 'a'){
                    //Do Nothing
                } else {
                    currChar = characters[y][x];
                }
                //System.out.println(currChar);

                if(Character.isDigit(currChar)){
                    if(currentDigit == null){
                        currentDigit = String.valueOf(currChar);
                        digitStart = x;
                    } else {
                        currentDigit += currChar;
                    }

                    digitEnd = x;
                } else {
                    if(currentDigit != null){

                        boolean hasSymbol = false;

                        int digit = Integer.parseInt(currentDigit);

                        //System.out.println(digit);
                        int xStart = digitStart;
                        //System.out.println(xStart);
                        int xEnd = digitEnd;
                        //System.out.println(xEnd);

                        if(xStart > 0){
                            xStart--;
                        }

                        if (xEnd < xMax - 1){
                            xEnd++;
                        }

                       if(y > 0){
                           for(int i = xStart; i <= xEnd; i++){
                               char character = characters[y-1][i];

                               if(!Character.isDigit(character) && character != 'a'){
                                   hasSymbol = true;
                               }
                           }
                       }

                       if(y < yMax - 1){
                           for(int i = xStart; i <= xEnd; i++){
                               char character = characters[y+1][i];

                               if(!Character.isDigit(character) && character != 'a'){
                                   hasSymbol = true;
                               }
                           }
                       }

                        if(!Character.isDigit(characters[y][xStart]) && characters[y][xStart] != 'a'){
                            hasSymbol = true;
                        } else if(!Character.isDigit(characters[y][xEnd]) && characters[y][xStart] != 'a'){
                            hasSymbol = true;
                        }



                        currentDigit = null;
                        digitStart = -1;
                        digitEnd = -1;

                        if(hasSymbol){
                            runningTotal+= digit;
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
