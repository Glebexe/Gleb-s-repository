import org.junit.*;

public class Task2_Tests extends Assert{
    class TestComparator implements MyComparator<Integer>{
        @Override
        public int compare(Integer first, Integer second) {
            return first - second;
        }
    }

    @Test
    public void bubbleSort_ArrayContainsOnlyNumbersOne_ArrayHasNotChanged(){
        try {
            BubbleSort<Integer> bubbleSort = new BubbleSort<>();
            Integer[] arr = {1, 1, 1};
            bubbleSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1, 1, 1}, arr);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void bubbleSort_InvertedArrayWithTheSameLastTheBiggestElement_GetSortedArray(){
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        Integer[] arr = {3, 2, 1, 4};
        bubbleSort.sort(arr, new TestComparator());
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, arr);
    }

    @Test
    public void bubbleSort_InvertedArray_GetSortedArray(){
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        Integer[] arr = {3, 2, 1};
        bubbleSort.sort(arr, new TestComparator());
        assertArrayEquals(new Integer[]{1, 2, 3}, arr);
    }

    @Test
    public void bubbleSort_UnsortedArray_GetSortedArray(){
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        Integer[] arr = {15,23,5,0};
        bubbleSort.sort(arr, new TestComparator());
        assertArrayEquals(new Integer[]{0, 5, 15, 23}, arr);
    }

    @Test
    public void selectionSort_InvertedArrayLengthTwo_GetSortedArray(){
        SelectionSort<Integer> selectionSort = new SelectionSort<>();
        Integer[] arr = {2, 1};
        selectionSort.sort(arr, new TestComparator());
        assertArrayEquals(new Integer[]{1, 2}, arr);
    }

    @Test
    public void selectionSort_UnsortedArray_GetSortedArray(){
        SelectionSort<Integer> selectionSort = new SelectionSort<>();
        Integer[] arr = {2, 1, 10, 0};
        selectionSort.sort(arr, new TestComparator());
        assertArrayEquals(new Integer[]{0, 1, 2, 10}, arr);
    }

    @Test
    public void insertionSort_ArrayContainsOnlyNumbersOne_ArrayHasNotChanged(){
        try {
            InsertionSort<Integer> insertionSort = new InsertionSort<>();
            Integer[] arr = {1, 1};
            insertionSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1, 1}, arr);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void insertionSort_SortedArray_ArrayHasNotChanged(){
        InsertionSort<Integer> insertionSort = new InsertionSort<>();
        Integer[] arr = {1,2};
        insertionSort.sort(arr, new TestComparator());
        assertArrayEquals(new Integer[]{1,2}, arr);
    }
}
