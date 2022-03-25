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
            Integer[] arr = new Integer[]{4,3,2,1};
            QuickSort quickSort = new QuickSort();
            quickSort.sort(arr, new TestComparator());
            assertArrayEquals(new Integer[]{1, 2,3,4}, arr);
        }catch (Exception e){
            fail();
        }
    }
}
