import java.util.*;
import java.io.*;

public class GeneticAlgorithm {

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "Items.txt";
        String filenameTwo = "more_Items.txt";
        int currIterations;
        int totalIterations = 20;
        int i;
        int numToMutate;
        int whoMutate;
        int quant;

        Random grng = new Random();

        



        ArrayList<Item> itemList = readData(filename);
      
        // Step 1: Initialize population
        ArrayList<Chromosome> currentPopulation = initializePopulation(itemList, 10);

        // Iteration loop
        for (currIterations = 0; currIterations < totalIterations; currIterations++) {
            // Step 2: Add each individual to nextgen
            ArrayList<Chromosome> nextGeneration = new ArrayList<>(currentPopulation);

            // Step3: Random mating, put parents into a new arraylist and shuffle
            ArrayList<Chromosome> randomized = new ArrayList<>(currentPopulation);
            Collections.shuffle(randomized, grng);

            // Then crossover to generate offspring
            for (i = 0; i < randomized.size(); i++) {
                Chromosome parentA = randomized.get(i);
                i += 1;
                Chromosome parentB = randomized.get(i);
                nextGeneration.add(parentA.crossover(parentB));
            }

            // Step 4: Mutation of at least 1 individual, or 10% of population
            numToMutate = Math.max(1, ((int) Math.floor(nextGeneration.size() * 0.10))); // Calculates how many
                                                                                         // mutations to perform
            for (i = 0; i <= numToMutate; i++) { // Iterates dependent on number of chromosomes in population, but at
                                                 // least once
                whoMutate = grng.nextInt(nextGeneration.size()); // RNG to decide index location for chromosome mutation
                nextGeneration.get(whoMutate).mutate(); // Mutates chromosome at random index location
            }

            // Step 5: Sort nextgen chromosomes by fitness
            Collections.sort(nextGeneration);

            // Step 6: Clear out currpopulation
            currentPopulation.clear();

            // Step 7: Add top ten of nextgen to currpop
            if (nextGeneration.size() < 10) {
                quant = nextGeneration.size();
            } else {
                quant = 10;
            }

            for (i = 0; i < quant; i++) {
                currentPopulation.add(nextGeneration.get(i));
            }

        }
        //Step 9: Sort last iteration of the currentPopulation
        Collections.sort(currentPopulation);

        //Step 10: Output fittest individual
        Chromosome mostFit = currentPopulation.get(0);
        System.out.println("\n Fittest individual:");
        System.out.println(mostFit);
        System.out.println(mostFit.getFitness());

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

    // Creates and returns arraylist with objects and the attributes 
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        int i;
        ArrayList<Chromosome> species = new ArrayList<>();
        for (i = 0; i < populationSize; i++) {
            species.add(new Chromosome(items));
        }
        return species;
    }

}
