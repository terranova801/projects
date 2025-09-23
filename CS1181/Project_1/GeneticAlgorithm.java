import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class GeneticAlgorithm {

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "Items.txt";
        //String filenameTwo = "more_Items.txt";
        try {
            ArrayList<Item> itemList = readData(filename);
            for (Item i : itemList) {
                System.out.println(i.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
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
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item>items, int populationSize) {

    }

}
