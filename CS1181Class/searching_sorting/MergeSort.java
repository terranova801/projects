import java.util.ArrayList;

public class MergeSort{


    public static <E extends Comparable<E>> List<E> mergeSort(List<E> items) {


        for (int i = 0; i < totalSize; i++) {
            if (secondHalfDone || (!firstHalfDone && firstItem.compareTo(secondItem))) {
                result.add(firstItem);
                firstHalfDone = firstHalf.isEmpty();
                if (!firstHalfDone) {
                    firstItem = firstHalf.removeFirst();
                }
                else {
                    result.add(secondItem);
                    secondHalfDone = secondHalf.isEmpty();
                }
            }
        }







    }
}