import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {

    public static class Deck implements Comparable<Deck>{
        private int bid, strength;
        private String deck;

        public Deck(String deck, int bid, int strength){
            this.deck = deck;
            this.bid = bid;
            this.strength = strength;
        }

        public int getBid() {
            return bid;
        }

        public int getStrength() {
            return strength;
        }

        public String getDeck() {
            return deck;
        }

        @Override
        public int compareTo(Deck o){
            if(this.strength > o.strength){
                return 1;
            } else if(this.strength < o.strength){
                return -1;
            }

            if(Character.compare(this.deck.charAt(0), o.deck.charAt(0)) > 0){
                return 1;
            } else if(Character.compare(this.deck.charAt(0), o.deck.charAt(0)) < 0){
                return -1;
            }

            if(Character.compare(this.deck.charAt(1), o.deck.charAt(1)) > 0){
                return 1;
            } else if(Character.compare(this.deck.charAt(1), o.deck.charAt(1)) < 0){
                return -1;
            }

            if(Character.compare(this.deck.charAt(2), o.deck.charAt(2)) > 0){
                return 1;
            } else if(Character.compare(this.deck.charAt(2), o.deck.charAt(2)) < 0){
                return -1;
            }

            if(Character.compare(this.deck.charAt(3), o.deck.charAt(3)) > 0){
                return 1;
            } else if(Character.compare(this.deck.charAt(3), o.deck.charAt(3)) < 0){
                return -1;
            }

            if(Character.compare(this.deck.charAt(4), o.deck.charAt(4)) > 0){
                return 1;
            } else if(Character.compare(this.deck.charAt(4), o.deck.charAt(4)) < 0){
                return -1;
            }

            if(this.bid > o.bid){
                return 1;
            } else if(this.bid < o.bid){
                return -1;
            }

            return 0;
        }
    }

    Scanner scanner;
    Scanner fileReader;
    char cards[] = {'0', '2', '3', '4', '5', '6', '7', '8', '9', 'E', 'J', 'Q', 'X', 'A'};
    ArrayList<Deck> decks;

    public Day7(){
        scanner = new Scanner(System.in);
        decks = new ArrayList<>();
    }

    public void readInput() {

        long start = System.currentTimeMillis();

        System.out.print("Enter File Name: ");
        String filename = scanner.nextLine();
        File file = new File(filename);
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long runningTotal = 0;

        while (fileReader.hasNext()) {
            decks.add(parseLine(fileReader.nextLine()));
        }

        Collections.sort(decks);

        for(int i = decks.size() - 1; i >=0; i--){
            runningTotal += decks.get(i).bid * (i + 1);
            System.out.println(decks.get(i).getDeck() + " " + decks.get(i).getBid());
        }

        System.out.println(runningTotal);

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(time + "ms");
    }

    private Deck parseLine(String currentLine) {
        String[] strings = currentLine.split(" ");
        String sortedDeck = manyKind(strings[0]);

        return new Deck(sortedDeck, Integer.valueOf(strings[1]), getValue(sortedDeck));
    }

    private String manyKind(String deck){

        char[] newDeck = new char[deck.length()];

        for(int i = 0; i < 5; i++){
            if(deck.charAt(i) == 'K'){
                newDeck[i] = 'X';
            } else if(deck.charAt(i) == 'T'){
                newDeck[i] = 'E';
            } else if(deck.charAt(i) ==  'A'){
                newDeck[i] = 'Z';
            } else {
                newDeck[i] = deck.charAt(i);
            }
        }

        return String.valueOf(newDeck);
    }

    private int getValue(String deck){
        int numMatching = 0;
        char matchingCharacter = 0;
        char comparingCharacter;
        boolean isFullHouse = false;


        for(int i = 0; i < deck.length(); i++){
            comparingCharacter = deck.charAt(i);
            int matching = 0;
            for(int j = 0; j < deck.length(); j++){
                if (comparingCharacter == deck.charAt(j)){
                    matching++;
                }
            }

            if((numMatching == 3 || numMatching == 2) && matching == 2 && comparingCharacter != matchingCharacter){
                isFullHouse = true;
            }

            if(numMatching == 2 && matching == 3 && comparingCharacter != matchingCharacter){
                isFullHouse = true;
            }

            if (matching > numMatching){
                numMatching = matching;
                matchingCharacter = comparingCharacter;
            }

        }

        if(numMatching > 3){
            numMatching = numMatching + 2;
        }
        if(numMatching == 3){
            numMatching++;
            if(isFullHouse){
                numMatching++;
            }
        }
        if(numMatching ==2 && isFullHouse){
            numMatching++;
        }


        return numMatching;

    }

    public static void main(String[] args){
        Day7 main = new Day7();
        main.readInput();
    }
}
