import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {
        Scanner scanner;
        Scanner fileReader;

        public Day6(){
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

            long runningTotal = 1;

            int numRaces = 1;

            long times[] = new long[numRaces];
            long distances[] = new long[numRaces];

            while (fileReader.hasNext()) {
                if (times[0] == 0) {
                    times = parseLine(fileReader.nextLine(), numRaces);
                } else {
                    distances = parseLine(fileReader.nextLine(), numRaces);
                }

            }


            for(int i = 0; i < numRaces; i++){
                int isBeatable = 0;
                long currentTime;
                long beatableDistance = distances[i];
                for(int j = 1 ; j < times[i] ; j++){
                    currentTime = j;
                    long distanceCompleted = (times[i] - j) * j;
                    //System.out.println(distanceCompleted);
                    if(distanceCompleted > beatableDistance){
                        isBeatable++;
                    }
                }
                runningTotal = runningTotal * isBeatable;
            }

            System.out.println(runningTotal);
        }

        public long[] parseLine(String currentLine, int length) {
            String strings[] = currentLine.split(" ");
            long array[] = new long[length];
            int i = 0;
            for (int j = 1; j < strings.length; j++) {
                if(strings[j] != ""){
                    array[i] = Long.parseLong(strings[j]);
                    i++;
                }
            }
            return array;
        }

        public static void main(String[] args){
            Day6 main = new Day6();
            main.readInput();
        }
}
