import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    Scanner scanner;
    Scanner fileReader;

    public static class Range{
        private long upper, lower;

        public Range(long lower, long upper){
            this.lower = lower;
            this.upper = upper;
        }

        public long getUpper() {
            return upper;
        }

        public long getLower() {
            return lower;
        }
    }

    public Day5(){
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

        long runningTotal = 1000000000;

        List<Range> seeds = new ArrayList<>();

        HashMap<Range, Range> seedToSoil = new HashMap<>();
        HashMap<Range, Range> soilToFertiliser = new HashMap<>();
        HashMap<Range, Range> fertilizerToWater = new HashMap<>();
        HashMap<Range, Range> waterToLight = new HashMap<>();
        HashMap<Range, Range> lightToTemperature = new HashMap<>();
        HashMap<Range, Range> temperatureToHumidity = new HashMap<>();
        HashMap<Range, Range> humidityToLocation = new HashMap<>();


        String lastLine= "";
        while (fileReader.hasNext()){
            String currentLine = fileReader.nextLine();
            if(currentLine.startsWith("seeds")){
                seeds = parseSeeds(currentLine);
            } else {
                if (Character.isDigit(currentLine.charAt(0))){

                    if(lastLine.startsWith("seed-")){
                        seedToSoil.putAll(parseHashMap(currentLine));
                    }

                    if(lastLine.startsWith("soil-")){
                        soilToFertiliser.putAll(parseHashMap(currentLine));
                    }

                    if(lastLine.startsWith("fertilizer-")){
                        fertilizerToWater.putAll(parseHashMap(currentLine));
                    }

                    if(lastLine.startsWith("water-")){
                        waterToLight.putAll(parseHashMap(currentLine));
                    }

                    if(lastLine.startsWith("light-")){
                        lightToTemperature.putAll(parseHashMap(currentLine));
                    }

                    if(lastLine.startsWith("temperature-")){
                        temperatureToHumidity.putAll(parseHashMap(currentLine));
                    }

                    if(lastLine.startsWith("humidity-")){
                        humidityToLocation.putAll(parseHashMap(currentLine));
                    }

                } else {
                    lastLine=currentLine;
                }
            }



         }

        for(Range currentSeeds : seeds) {
            long currentSeed = currentSeeds.getLower();

            while (currentSeed <= currentSeeds.getUpper()){

                long soil = -1;
                Set<Range> sourceRange = seedToSoil.keySet();
                for (Range currentRange : sourceRange) {
                    if (currentSeed >= currentRange.getLower() && currentSeed <= currentRange.getUpper()) {
                        Range destinationRange = seedToSoil.get(currentRange);
                        long difference = destinationRange.getLower() - currentRange.getLower();
                        soil = currentSeed + difference;
                        break;
                    }
                }

                if (soil == -1) {
                    soil = currentSeed;
                }

                long fertilizer = -1;

                sourceRange = soilToFertiliser.keySet();
                for (Range currentRange : sourceRange) {
                    if (soil >= currentRange.getLower() && soil <= currentRange.getUpper()) {
                        Range destinationRange = soilToFertiliser.get(currentRange);
                        long difference = destinationRange.getLower() - currentRange.getLower();
                        fertilizer = soil + difference;
                        break;
                    }
                }

                if (fertilizer == -1) {
                    fertilizer = soil;
                }

                long water = -1;
                sourceRange = fertilizerToWater.keySet();
                for (Range currentRange : sourceRange) {
                    if (fertilizer >= currentRange.getLower() && fertilizer <= currentRange.getUpper()) {
                        Range destinationRange = fertilizerToWater.get(currentRange);
                        long difference = destinationRange.getLower() - currentRange.getLower();
                        water = fertilizer + difference;
                        break;
                    }
                }

                if (water == -1) {
                    water = fertilizer;
                }

                long light = -1;
                sourceRange = waterToLight.keySet();
                for (Range currentRange : sourceRange) {
                    if (water >= currentRange.getLower() && water <= currentRange.getUpper()) {
                        Range destinationRange = waterToLight.get(currentRange);
                        long difference = destinationRange.getLower() - currentRange.getLower();
                        light = water + difference;
                        break;
                    }
                }

                if (light == -1) {
                    light = water;
                }

                long temperature = -1;
                sourceRange = lightToTemperature.keySet();
                for (Range currentRange : sourceRange) {
                    if (light >= currentRange.getLower() && light <= currentRange.getUpper()) {
                        Range destinationRange = lightToTemperature.get(currentRange);
                        long difference = destinationRange.getLower() - currentRange.getLower();
                        temperature = light + difference;
                        break;
                    }
                }

                if (temperature == -1) {
                    temperature = light;
                }

                long humidity = -1;
                sourceRange = temperatureToHumidity.keySet();
                for (Range currentRange : sourceRange) {
                    if (temperature >= currentRange.getLower() && temperature <= currentRange.getUpper()) {
                        Range destinationRange = temperatureToHumidity.get(currentRange);
                        long difference = destinationRange.getLower() - currentRange.getLower();
                        humidity = temperature + difference;
                        break;
                    }
                }

                if (humidity == -1) {
                    humidity = temperature;
                }

                long location = -1;
                sourceRange = humidityToLocation.keySet();
                for (Range currentRange : sourceRange) {
                    if (humidity >= currentRange.getLower() && humidity <= currentRange.getUpper()) {
                        Range destinationRange = humidityToLocation.get(currentRange);
                        long difference = destinationRange.getLower() - currentRange.getLower();
                        location = humidity + difference;
                        break;
                    }
                }

                if (location == -1) {
                    location = humidity;
                }

                if (location < runningTotal) {
                    runningTotal = location;
                }

                currentSeed++;
            }
            System.out.println("Here");
        }

        System.out.println(runningTotal);
    }

    public List<Range> parseSeeds(String currentLine){
        List<Range> seeds = new ArrayList<>();
        String strings[] = currentLine.split(" ");
        for (int i = 1; i < strings.length; i = i + 2){
            seeds.add(new Range(Long.parseLong(strings[i]), Long.parseLong(strings[i]) + Long.parseLong(strings[i+1]) - 1));
        }
        return seeds;
    }

    public HashMap<Range, Range> parseHashMap(String currentLine){
        HashMap<Range, Range> hashMap = new HashMap<>();
        String strings[] = currentLine.split(" ");
        long destination = Long.parseLong(strings[0]);
        long source = Long.parseLong(strings[1]);
        long range = Long.parseLong(strings[2]);

        hashMap.put(new Range(source, source + range - 1), new Range(destination, destination + range - 1));

        return hashMap;
    }

    public static void main(String[] args){
        Day5 main = new Day5();
        main.readInput();
    }
}