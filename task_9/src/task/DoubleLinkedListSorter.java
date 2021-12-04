package task;

public class DoubleLinkedListSorter {
    public static <T extends Comparable<T>> void sort(DoubleLinkedList<T> list) {
        for(int i = 0; i < list.getSize(); i++) {
            DoubleLinkedListItem minItem =  list.getFirst();
            DoubleLinkedListItem currItem = list.getFirst();
            for(int j = 0; j < list.getSize()-i; j++) {
                if(currItem.getData().compareTo(minItem.getData()) <0){
                    minItem = currItem;
                }
                currItem = currItem.getNext();
            }
            list.remove(minItem);
            list.insertAfter(list.getLast(), (T)minItem.getData());
        }
    }
}
