package CS1181.Project_1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class GeneticAlgorithm {
    // Arraylist that reads txt file
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        File itemsReader = new File(filename);
        Scanner inputItemsFile = new Scanner (itemsReader);

        String text;
        while (inputItemsFile.hasNextLine()) {
            text = inputItemsFile.next();
            System.out.println(text);
        }
        inputItemsFile.close();
    }

    // Creates and returns arraylist with objects and the attributes
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) {

    }

    public static void main(String[] args) throws FileNotFoundException {
        String fileNameOne = "Items.txt";
        String fileNameTwo = "more_Items.txt"

        
        ArrayList<Item> readData(String filename) = new ArraryList<Item>;

    }

}
