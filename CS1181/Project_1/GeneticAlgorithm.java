import java.util.*;
import java.io.*;

public class GeneticAlgorithm {

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "Items.txt";
        // String filenameTwo = "more_Items.txt";
        int currIterations;
        int totalIterations = 20;
        int i;

        Random grng = new Random();
        
        ArrayList<Item> itemList = readData(filename);
        try {
            ArrayList<Item> itemList = readData(filename);
            // for (Item i : itemList) {
            // System.out.println(i.toString());
            // }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        // Initialize population
        ArrayList<Chromosome> currGenePool = initializePopulation(itemList, 10);

        //Iteration loop
        for (currIterations = 0; currIterations < totalIterations; currIterations++) {
            ArrayList<Chromosome> survivors = new ArrayList<>(currGenePool);


            //Random mating: First put parents into a new arraylist and shuffle
            ArrayList<Chromosome> randomized = new ArrayList<>(currGenePool);
            Collections.shuffle(randomized, grng);

            //Crossover method to generate offspring
            for (i = 0; i < randomized.size(); i++) { 
                Chromosome parentA = randomized.get(i);
                i += 1;
                Chromosome parentB = randomized.get(i);
                survivors.add(parentA.crossover(parentB));
            }


        }









    }

    // Arraylist that reads txt file, currently works

    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        File itemsReader = new File(filename);
        Scanner inputItemsFile = new Scanner(itemsReader);
        ArrayList<Item> items = new ArrayList<>();

        while (inputItemsFile.hasNextLine()) {
            String line = inputItemsFile.nextLine();

            if (!line.isEmpty()) {
                String[] parts = line.split(",");

                String name = parts[0].trim();
                double weight = Double.parseDouble(parts[1].trim());
                int value = Integer.parseInt(parts[2].trim());

                items.add(new Item(name, weight, value));
            }
        }
        inputItemsFile.close();
        return items;
    }

    // Creates and returns arraylist with objects and the attributes FIXME
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        int i;
        ArrayList<Chromosome> species = new ArrayList<>();
        for (i = 0; i < populationSize; i++) {
            species.add(new Chromosome(items));
        }
        return species;
    }

}
