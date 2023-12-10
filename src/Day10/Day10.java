package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {

    private static class Coordinate{
        int x;
        int y;
        int direction;
        int distance;

        public Coordinate(int x, int y, int direction, int currentDistance) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.distance = currentDistance;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Coordinate other = (Coordinate) obj;
            return x == other.x && y == other.y;
        }
    }

    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;
    static final int LEFT = 4;


    Scanner scanner;
    Scanner fileReader;
    int startX;
    int startY;

    int currentDistance = -1;
    int maxDistance = 0;

    int xLength = 140;
    int yLength = 140;

    public Day10(){
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

        char[][] characters = new char[yLength][xLength];
        for(int y = 0; y < yLength; y++){
            String currentLine = fileReader.nextLine();
            for(int x = 0; x < xLength; x++){
                char currentCharacter = currentLine.charAt(x);
                if (currentCharacter == 'S'){
                    startX = x;
                    startY = y;
                }
                System.out.print(currentCharacter+ ",");
                characters[y][x] = currentCharacter;
            }
            System.out.println();
        }

        //0 None, 1 Up, 2 Right, 3 Left, 4 Down;

        traversePipes(characters, new Coordinate(startX, startY, 0, 0));


        System.out.println(maxDistance);
    }


    Queue<Coordinate> nextTraverse = new LinkedList<>();
    ArrayList<Coordinate> visited = new ArrayList<>();

    private Queue<Coordinate> traversePipes(char[][] pipes, Coordinate currentCoordinate){

        Queue<Coordinate> coordinateQueue = new LinkedList<>();

        if(currentCoordinate.x < 0 || currentCoordinate.y < 0 || currentCoordinate.x >= xLength || currentCoordinate.y >= yLength){
            System.out.println("Character Not In Range");
            return null;
        }

        char currentChar = pipes[currentCoordinate.y][currentCoordinate.x];

        if(currentChar == '.'){
            return null;
        }

        if(visited.contains(currentCoordinate)){
            return null;
        } else {
            visited.add(currentCoordinate);
        }

        if(currentCoordinate.distance > maxDistance){
            maxDistance = currentCoordinate.distance;
        }



        switch (currentChar){
            case 'S':
                //Check All Possible Surrounding Squares;

                char nextChar = pipes[currentCoordinate.y - 1][currentCoordinate.x];

                if(nextChar == '|' || nextChar == 'F' || nextChar == '7'){
                    nextTraverse.add(new Coordinate(currentCoordinate.x, currentCoordinate.y - 1, UP, currentCoordinate.distance+ 1));
                }

                nextChar = pipes[currentCoordinate.y + 1][currentCoordinate.x];
                if(nextChar == '|' || nextChar == 'L' || nextChar == 'J'){
                    nextTraverse.add(new Coordinate(currentCoordinate.x, currentCoordinate.y + 1, DOWN, currentCoordinate.distance + 1));
                }

                nextChar = pipes[currentCoordinate.y][currentCoordinate.x +1];
                if(nextChar == '-' || nextChar == '7' || nextChar == 'J'){
                    nextTraverse.add(new Coordinate(currentCoordinate.x + 1, currentCoordinate.y, RIGHT, currentCoordinate.distance + 1));
                }

                nextChar = pipes[currentCoordinate.y][currentCoordinate.x - 1];
                if(nextChar == '-' || nextChar == 'L' || nextChar == 'F'){
                    nextTraverse.add(new Coordinate(currentCoordinate.x - 1, currentCoordinate.y, LEFT, currentCoordinate.distance + 1));
                }



                break;
            case '|':
                //Check Vertically Opposite Of Previous Direction
                if(currentCoordinate.direction == 1){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x, currentCoordinate.y - 1, UP, currentCoordinate.distance+ 1));
                } else if(currentCoordinate.direction == 3){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x, currentCoordinate.y + 1, DOWN, currentCoordinate.distance+ 1));
                }
                return coordinateQueue;
            case '-':
                //Check Horizontally Opposite Of Previous Direction
                if(currentCoordinate.direction == 2){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x + 1, currentCoordinate.y, RIGHT, currentCoordinate.distance+ 1));
                } else if(currentCoordinate.direction == 4){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x - 1, currentCoordinate.y, LEFT, currentCoordinate.distance+ 1));
                }
                return coordinateQueue;
            case 'L':
                //Connects North To East, Check Direction
                if(currentCoordinate.direction == 3){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x + 1, currentCoordinate.y, RIGHT, currentCoordinate.distance+ 1));
                } else if(currentCoordinate.direction == 4){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x, currentCoordinate.y - 1, UP, currentCoordinate.distance+ 1));
                }
                return coordinateQueue;
            case 'J':
                //Check Horizontally Opposite Of Previous Direction
                if(currentCoordinate.direction == 3){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x - 1, currentCoordinate.y, LEFT, currentCoordinate.distance+ 1));
                } else if(currentCoordinate.direction == 2){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x, currentCoordinate.y - 1, UP, currentCoordinate.distance+ 1));
                }
                return coordinateQueue;
            case '7':
                //Check Horizontally Opposite Of Previous Direction
                if(currentCoordinate.direction == 1){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x - 1, currentCoordinate.y, LEFT, currentCoordinate.distance+ 1));
                } else if(currentCoordinate.direction == 2){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x, currentCoordinate.y + 1, DOWN, currentCoordinate.distance+ 1));
                }
                return coordinateQueue;
            case 'F':
                //Check Horizontally Opposite Of Previous Direction
                if(currentCoordinate.direction == 1){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x + 1, currentCoordinate.y, RIGHT, currentCoordinate.distance+ 1));
                } else if(currentCoordinate.direction == 4){
                    coordinateQueue.add(new Coordinate(currentCoordinate.x, currentCoordinate.y + 1, DOWN, currentCoordinate.distance+ 1));
                }
                return coordinateQueue;
            default:
                //Is A Dot, Not A Valid Path
                return null;
        }

        while (!nextTraverse.isEmpty()){

            Coordinate nextCoordinate = nextTraverse.remove();

            System.out.println("X: " + nextCoordinate.x + ",Y: " + nextCoordinate.y);
            System.out.println(maxDistance);
            Queue<Coordinate> newQueue = traversePipes(pipes, nextCoordinate);
            if(newQueue != null){
                nextTraverse.addAll(newQueue);
            }
        }


        return null;
    }

    /** private int parseLine(String currentLine){
        return 0;
    } **/

    public static void main(String[] args){
        Day10 main = new Day10();
        main.readInput();
    }
}
