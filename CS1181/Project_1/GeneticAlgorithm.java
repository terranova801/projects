import java.util.*;
import java.io.*;

/**
 * Main class and method
 */
public class GeneticAlgorithm {

    public static void main(String[] args) throws FileNotFoundException {
        String itemsOne = "Items.txt";  //textfile with 7 items
        String itemsTwo = "more_Items.txt"; //textfile with many more items
        String filename;    //placeholder for name of file to read
        int currIterations; //Tracks current iteration
        int totalIterations;    //The number of iterations to do
        int i;
        int numToMutate;    //Number of chromosome objects to pass to mutate method
        int whoMutate;      //Which chromosomes to mutate
        int quant;      //Number of fit individuals to move to nextgeneration
        String userInput;   //User input to select which list to use

        Scanner scnr = new Scanner(System.in);
        Random grng = new Random();

        System.out.println("Large list or short list?");
        userInput = scnr.next();

        //Decides which textfile to read based on user input and changes iterations based on which file is read
        if (userInput.toLowerCase().contains("large")) {
            filename = itemsTwo;
            totalIterations = 20;   //Originally implemented to see if more iterations...50000, would help output valid chromosomes with a 10lb weight limit
                                    //Did not help, but like the idea of having custom iteration values for either file. Currently left both at 20
        }
        else {
            filename = itemsOne;
            totalIterations = 20;
        }



        //Creates item arraylist from textfile
        ArrayList<Item> itemList = readData(filename);
      
        //Steps 2 through 7 are repeated for each iteration
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
        System.out.println("\nFittest Chromosome:");
         System.out.println("\nItems Included:\n");

        System.out.println(mostFit);
    }

    

    // Arraylist that reads txt file, currently works
    /**
     * reads textfiles containing objects and creates objects for items
     * https://www.geeksforgeeks.org/java/java-split-string-using-regex/ helpful in figuring out how to seperate data contained in test strings
     * https://www.geeksforgeeks.org/java/integer-valueof-vs-integer-parseint-with-examples/ helpful in converting string data to int datatype
     * @param filename name of file to be read
     * @return returns arraylist containing items
     * @throws FileNotFoundException
     */
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        File itemsReader = new File(filename);
        Scanner inputItemsFile = new Scanner(itemsReader);
        ArrayList<Item> items = new ArrayList<>();

        while (inputItemsFile.hasNextLine()) {
            String line = inputItemsFile.nextLine();

            if (!line.isEmpty()) {
                String[] part = line.split(",");    //Splits line at commas to be assign each parameter to correct variable

                String name = part[0].trim();   //First part for item name
                double weight = Double.parseDouble(part[1].trim()); //Second part assign object weight
                int value = Integer.parseInt(part[2].trim());   //Third part assigns object value

                items.add(new Item(name, weight, value));   //Adds item to arraylist by calling constructer
            }
        }
        inputItemsFile.close(); //Closes file
        return items;   //Returns array containing items
    }

    // Creates and returns arraylist with objects and the attributes
    /**
     * Initializes the population of chromosomes
     * @param items creates new chromosome objects
     * @param populationSize used to determine # of individuals to create   
     * @return returns arraylist containing chromosome objects
     */
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {
        int i;
        ArrayList<Chromosome> species = new ArrayList<>();
        for (i = 0; i < populationSize; i++) {
            species.add(new Chromosome(items));
        }
        return species; //Returns array containing chromosome objects
    }

}
