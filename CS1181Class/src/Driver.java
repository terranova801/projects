import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Driver {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();

        List<String> l2 = new LinkedList<>();
        

        CustomLinkedList<String> customList = new CustomLinkedList<>();

        //Arraylist
        l1.add("Hello");
        l1.add("World");
        l1.add("Thing1");
        l1.add("Thing2");
        l1.add("Thing3");
        l1.add("Thing4");
        l1.add("Thing5");
        l1.add("Thing6");

        //LinkedList
        l2.add("Hello");
        l2.add("World");
        l2.add("Thing1");
        l2.add("Thing2");
        l2.add("Thing3");
        l2.add("Thing4");
        l2.add("Thing5");
        l2.add("Thing6");

        //Custom Linkedlist
        customList.add("Hello");
        customList.add("World");
        customList.add("Thing1");
        customList.add("Thing2");
        customList.add("Thing3");
        customList.add("Thing4");
        customList.add("Thing5");
        customList.add("Thing6");

        System.out.println("Custom linkedlist index 4:\n");
        System.out.println(customList.get(4));
        
        System.out.println(customList.toString());


        System.out.println("\nStandard arraylist\n");
        System.out.println(l1);

        System.out.println("\nStandard linkedlist\n");
        System.out.println(l2);


        

        // Node<String> n1 = new Node<String>("CEG 2350");
        // Node<String> n2 = new Node<String>("CS 1150");
        // Node<String> n3 = new Node<String>("CS 1181");
        
        // n1.setNext(n2);
        // n2.setNext(n3);
        // n3.setNext(null);


        // System.out.println(n1.getNext().getNext());

    }
}