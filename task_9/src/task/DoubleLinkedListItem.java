package task;

public class DoubleLinkedListItem<T extends Comparable<T>> {

    private T data;
    private DoubleLinkedListItem next;
    private DoubleLinkedListItem prev;

    public DoubleLinkedListItem(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }
    public DoubleLinkedListItem getNext() {
        return next;
    }
    public DoubleLinkedListItem getPrev() {
        return prev;
    }

    public void setData(T data) {
        this.data = data;
    }
    public void setNext(DoubleLinkedListItem next) {
        this.next = next;
    }
    public void setPrev(DoubleLinkedListItem prev) {
        this.prev = prev;
    }
}
