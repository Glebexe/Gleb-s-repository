import org.junit.*;

public class Task3_Tests extends Assert {
    class TestComparator implements MyComparator<Integer>{
        @Override
        public int compare(Integer first, Integer second) {
            return first - second;
        }
    }

    @Test
    public void quickSort_UnsortedArrayWithLengthTwo_GetSortedArray(){
        try {
            Integer[] arr = new Integer[]{2,1};
            QuickSort quickSort = new QuickSort();
            quickSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1, 2}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void quickSort_UnsortedArrayWithTwoZeroAndLengthFour_GetSortedArray(){
        try {
            Integer[] arr = new Integer[]{3, 0,2,0};
            QuickSort quickSort = new QuickSort();
            quickSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{0,0,2,3}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void quickSort_UnsortedArray_GetSortedArray(){
        try {
            Integer[] arr = new Integer[]{0,3,0,2,1};
            QuickSort quickSort = new QuickSort();
            quickSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{0,0,1,2,3}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void mergeSort_UnsortedArrayWithLengthTwo_GetSortedArray(){
        try {
            Integer[] arr = new Integer[]{2,1};
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1, 2}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void mergeSort_UnsortedOnlyFirstTwoElements_GetSortedArray(){
        try {
            Integer[] arr = new Integer[]{2,1,2,3};
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1, 2,2,3}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void mergeSort_UnsortedArrayV1_GetSortedArray(){
        try {
            Integer[] arr = new Integer[]{2,1,3,1};
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1, 1,2,3}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void mergeSort_UnsortedArrayV2_GetSortedArray(){
        try {
            Integer[] arr = new Integer[]{3,1,0,1};
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{0, 1,1,3}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void mergeSort_UnsortedArrayV3_GetSortedArray(){
        try {
            Integer[] arr = new Integer[]{0,0,1,2,2,1,0,2};
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{0,0,0,1,1,2,2,2}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void heapSort_ArrayContainsOnlyTwoNumbersOne_GetUnchangedArray(){
        try {
            Integer[] arr = new Integer[]{1,1};
            HeapSort heapSort = new HeapSort();
            heapSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1,1}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void heapSort_SortedArrayLengthTwo_GetUnchangedArray(){
        try {
            Integer[] arr = new Integer[]{1,2};
            HeapSort heapSort = new HeapSort();
            heapSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1,2}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void heapSort_SortedArrayLengthThree_GetUnchangedArray(){
        try {
            Integer[] arr = new Integer[]{1,2,3};
            HeapSort heapSort = new HeapSort();
            heapSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1,2,3}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void heapSort_UnsortedArrayV1_GetUnchangedArray(){
        try {
            Integer[] arr = new Integer[]{6,0,3,2,0,6};
            HeapSort heapSort = new HeapSort();
            heapSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{0,0,2,3,6,6}, arr);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void heapSort_UnsortedArrayV2_GetUnchangedArray(){
        try {
            Integer[] arr = new Integer[]{5,4,1,2,1,2,6,3,4,1,8,6};
            HeapSort heapSort = new HeapSort();
            heapSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1,1,1,2,2,3,4,4,5,6,6,8}, arr);
        }catch (Exception e){
            fail();
        }
    }
}
