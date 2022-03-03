public class DoubleLinkedList<T> {
    private DummyNode<T> dummy;
    private int size;

    public DoubleLinkedList() {
        dummy = new DummyNode<>();
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public int getSize() {
        return size;
    }
    public ListNode<T> getHead() {
        return dummy.getNext();
    }
    public ListNode<T> getTail() {
        return dummy.getPrev();
    }

    public ListNode<T> get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of bounds");
        ListNode<T> cur = getHead();
        for(int i = 0; i < index; ++i)
            cur = cur.getNext();
        return (Node<T>) cur;
    }

    public Node<T> insertAfter(ListNode<T> node, T data) {
        ++size;
        return new Node<T>(data, node.getNext(), node);
    }
    public Node<T> insertBefore(ListNode<T> node, T data) {
        ++size;
        return new Node<T>(data, node, node.getPrev());
    }
    public Node<T> pushFront(T data) {
        return insertAfter(getTail(), data);
    }
    public Node<T> pushBack(T data) {
        return insertBefore(getHead(), data);
    }
    public void remove(ListNode<T> node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        size = Math.max(0, size - 1);
    }

    public void insertListAfter(ListNode<T> node, DoubleLinkedList<T> list) {
        if(list.isEmpty())
            return;
        list.getHead().setPrev(node);
        list.getTail().setNext(node.getNext());
        node.getNext().setPrev(list.getTail());
        node.setNext(list.getHead());

        list.dummy.setNext(list.dummy);
        list.dummy.setPrev(list.dummy);

        size += list.getSize();
    }
    public void insertListBefore(ListNode<T> node, DoubleLinkedList<T> list) {
        if(list.isEmpty())
            return;
        list.getHead().setPrev(node.getPrev());
        list.getTail().setNext(node);
        node.getPrev().setNext(list.getHead());
        node.setPrev(list.getTail());

        list.dummy.setNext(list.dummy);
        list.dummy.setPrev(list.dummy);

        size += list.getSize();
    }
}
