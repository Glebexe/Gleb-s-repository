package task;

public class DoubleLinkedList<T extends Comparable<T>> {

    private DoubleLinkedListItem first;
    private DoubleLinkedListItem last;
    private int size;

    public DoubleLinkedList(){
        size = 0;
    }

    public DoubleLinkedListItem findFirstItem(T data){
        DoubleLinkedListItem item = first;
        while(item.getData() != data){
            item = item.getNext();
        }
        return item;
    }
    public DoubleLinkedListItem findLastItem(T data){
        DoubleLinkedListItem item = last;
        while(item.getData() != data){
            item = item.getPrev();
        }
        return item;
    }

    public void insertAfter(DoubleLinkedListItem item, T data){
        DoubleLinkedListItem newItem = new DoubleLinkedListItem(data);
        if(item == null && size == 0){
            first = newItem;
            last = newItem;
        }
        else if(item == null && size != 0){
            insertBefore(first,data);
            size--;
        }
        else if(item.getNext() == null){
            item.setNext(newItem);
            newItem.setPrev(item);
            last = newItem;
        }
        else{
            newItem.setNext(item.getNext());
            newItem.setPrev(item);
            item.getNext().setPrev(newItem);
            item.setNext(newItem);
        }
        size++;
    }
    public void insertBefore(DoubleLinkedListItem item, T data){
        DoubleLinkedListItem newItem = new DoubleLinkedListItem(data);
        if(item == null && size == 0){
            first = newItem;
            last = newItem;
        }
        else if(item == null && size != 0){
            insertAfter(last,data);
            size--;
        }
        else if(item.getPrev() == null){
            item.setPrev(newItem);
            newItem.setNext(item);
            first = newItem;
        }
        else{
            newItem.setPrev(item.getPrev());
            newItem.setNext(item);
            item.getPrev().setNext(newItem);
            item.setPrev(newItem);
        }
        size++;
    }
    public void remove(DoubleLinkedListItem item){
         if((item.getNext() == null) && (item.getPrev() == null)){
            last = null;
            first = null;
        }
        else if(item.getNext() == null){
            item.getPrev().setNext(null);
            last = item.getPrev();
        }
        else if(item.getPrev() == null){
            item.getNext().setPrev(null);
            first = item.getNext();
        }
        else{
            item.getNext().setPrev(item.getPrev());
            item.getPrev().setNext(item.getNext());
            item.setNext(null);
            item.setPrev(null);
        }
        size--;
    }

    public int getSize() {
        return size;
    }
    public DoubleLinkedListItem getFirst(){
        return first;
    }
    public DoubleLinkedListItem getLast(){return last;}
}
