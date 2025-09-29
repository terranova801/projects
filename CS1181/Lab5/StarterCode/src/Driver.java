
public class Driver {

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add("Falcons");
		list.add("Bears");
		list.add("Titans");
		list.add("Eagles");
		list.add("Panthers");
		list.add("Cowboys");
		list.add("Steelers");
		list.add("49ers");
		list.add("Vikings");
		list.add("Saints");
		list.add("Seahawks");
		
		list.print();
		System.out.println("\n");
		System.out.println("First item: " + list.getFirst());
		System.out.println("Last item: " + list.getLast());
		System.out.println("Penultimate item: " + list.getPenultimate());
	}
}
