import org.junit.*;

public class Task1_Tests extends Assert {

    private int getIntValue(ListNode<Integer> item) {
        return ((Node<Integer>) item).getData();
    }

    @Test
    public void pushFront_PushFrontToEmptyList_EmptyIsFalse() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void pushFront_PushFrontToEmptyList_GettingErrorWhenTryingToGetItemNumberOne() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        try {
            list.get(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void pushItems_PushFrontThreeItems_FirstItemIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        assertEquals(1, getIntValue(list.get(0)));
    }

    @Test
    public void pushItems_PushFrontThreeItems_SecondItemIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        assertEquals(2, getIntValue(list.get(1)));
    }

    @Test
    public void pushItems_PushFrontThreeItems_ThirdItemIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        assertEquals(3, getIntValue(list.get(2)));
    }

    @Test
    public void pushItems_PushFrontThreeItems_HeadIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        assertEquals(1, getIntValue(list.getHead()));
    }

    @Test
    public void pushItems_PushFrontThreeItems_TailIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        assertEquals(3, getIntValue(list.getTail()));
    }

    @Test
    public void pushItems_PushBackThreeItems_FirstItemIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushBack(3);
        list.pushBack(2);
        list.pushBack(1);
        assertEquals(1, getIntValue(list.get(0)));
    }

    @Test
    public void pushItems_PushBackThreeItems_SecondItemIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushBack(3);
        list.pushBack(2);
        list.pushBack(1);
        assertEquals(2, getIntValue(list.get(1)));
    }

    @Test
    public void pushItems_PushBackThreeItems_ThirdItemIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushBack(3);
        list.pushBack(2);
        list.pushBack(1);
        assertEquals(3, getIntValue(list.get(2)));
    }

    @Test
    public void removeItem_RemoveFirstItem_FirstItemIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.remove(list.get(0));
        assertEquals(2, getIntValue(list.get(0)));
    }

    @Test
    public void removeItem_RemoveFirstItem_HeadIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.remove(list.get(0));
        assertEquals(2, getIntValue(list.getHead()));
    }

    @Test
    public void removeItem_RemoveFirstItem_TailIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.remove(list.get(0));
        assertEquals(3, getIntValue(list.getTail()));
    }

    @Test
    public void removeItem_RemoveFirstItem_SizeIsCorrect() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.remove(list.get(0));
        assertEquals(2, list.getSize());
    }

    @Test
    public void insertList_insertListAfterFirstItem_SizeIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListAfter(list1.get(0), list2);
        assertEquals(6, list1.getSize());
    }

    @Test
    public void insertList_insertListAfterFirstItem_HeadIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListAfter(list1.get(0), list2);
        assertEquals(1, getIntValue(list1.getHead()));
    }

    @Test
    public void insertList_insertListAfterFirstItem_TailIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListAfter(list1.get(0), list2);
        assertEquals(3, getIntValue(list1.getTail()));
    }

    @Test
    public void insertList_insertListAfterFirstItem_AllElementsAreCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);

        list1.insertListAfter(list1.get(0), list2);

        assertEquals(1, getIntValue(list1.get(0)));
        assertEquals(4, getIntValue(list1.get(1)));
        assertEquals(5, getIntValue(list1.get(2)));
        assertEquals(6, getIntValue(list1.get(3)));
        assertEquals(2, getIntValue(list1.get(4)));
        assertEquals(3, getIntValue(list1.get(5)));
    }

    @Test
    public void insertList_insertListAfterSecondItem_AllElementsAreCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);

        list1.insertListAfter(list1.get(1), list2);

        assertEquals(1, getIntValue(list1.get(0)));
        assertEquals(2, getIntValue(list1.get(1)));
        assertEquals(4, getIntValue(list1.get(2)));
        assertEquals(5, getIntValue(list1.get(3)));
        assertEquals(6, getIntValue(list1.get(4)));
        assertEquals(3, getIntValue(list1.get(5)));
    }

    @Test
    public void insertList_insertListAfterThirdItem_AllElementsAreCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);

        list1.insertListAfter(list1.get(2), list2);

        assertEquals(1, getIntValue(list1.get(0)));
        assertEquals(2, getIntValue(list1.get(1)));
        assertEquals(3, getIntValue(list1.get(2)));
        assertEquals(4, getIntValue(list1.get(3)));
        assertEquals(5, getIntValue(list1.get(4)));
        assertEquals(6, getIntValue(list1.get(5)));
    }

    @Test
    public void insertList_insertListAfterThirdItem_HeadIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListAfter(list1.get(2), list2);
        assertEquals(1, getIntValue(list1.getHead()));
    }

    @Test
    public void insertList_insertListAfterThirdItem_TailIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListAfter(list1.get(2), list2);
        assertEquals(6, getIntValue(list1.getTail()));
    }

    @Test
    public void insertList_insertListBeforeFirstItem_SizeIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListBefore(list1.get(0), list2);
        assertEquals(6, list1.getSize());
    }

    @Test
    public void insertList_insertListBeforeFirstItem_HeadIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListBefore(list1.get(0), list2);
        assertEquals(4, getIntValue(list1.getHead()));
    }

    @Test
    public void insertList_insertListBeforeFirstItem_TailIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListBefore(list1.get(0), list2);
        assertEquals(3, getIntValue(list1.getTail()));
    }

    @Test
    public void insertList_insertListBeforeFirstItem_AllElementsAreCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);

        list1.insertListBefore(list1.get(0), list2);

        assertEquals(4, getIntValue(list1.get(0)));
        assertEquals(5, getIntValue(list1.get(1)));
        assertEquals(6, getIntValue(list1.get(2)));
        assertEquals(1, getIntValue(list1.get(3)));
        assertEquals(2, getIntValue(list1.get(4)));
        assertEquals(3, getIntValue(list1.get(5)));
    }

    @Test
    public void insertList_insertListBeforeSecondItem_AllElementsAreCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);

        list1.insertListBefore(list1.get(1), list2);

        assertEquals(1, getIntValue(list1.get(0)));
        assertEquals(4, getIntValue(list1.get(1)));
        assertEquals(5, getIntValue(list1.get(2)));
        assertEquals(6, getIntValue(list1.get(3)));
        assertEquals(2, getIntValue(list1.get(4)));
        assertEquals(3, getIntValue(list1.get(5)));
    }

    @Test
    public void insertList_insertListBeforeThirdItem_AllElementsAreCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);

        list1.insertListBefore(list1.get(2), list2);

        assertEquals(1, getIntValue(list1.get(0)));
        assertEquals(2, getIntValue(list1.get(1)));
        assertEquals(4, getIntValue(list1.get(2)));
        assertEquals(5, getIntValue(list1.get(3)));
        assertEquals(6, getIntValue(list1.get(4)));
        assertEquals(3, getIntValue(list1.get(5)));
    }

    @Test
    public void insertList_insertListBeforeThirdItem_HeadIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListBefore(list1.get(2), list2);
        assertEquals(1, getIntValue(list1.getHead()));
    }

    @Test
    public void insertList_insertListBeforeThirdItem_TailIsCorrect() {
        DoubleLinkedList<Integer> list1 = new DoubleLinkedList<>();
        list1.pushFront(1);
        list1.pushFront(2);
        list1.pushFront(3);

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.pushFront(4);
        list2.pushFront(5);
        list2.pushFront(6);
        list1.insertListBefore(list1.get(2), list2);
        assertEquals(3, getIntValue(list1.getTail()));
    }

    @Test
    public void createArray_CreateArrayWithoutSize_SizeEqualsDefaultSize() {
        DynamicArray<Integer> array = new DynamicArray<>();

        assertEquals(1024, array.getSize());
    }

    @Test
    public void createArray_CreateArrayWithSize_SizeIsCorrect() {
        DynamicArray<Integer> array = new DynamicArray<>(10);

        assertEquals(10, array.getSize());
    }

    @Test
    public void createArray_TryToGetValueOutOfRange_GettingIndexOutOfBoundsException() {
        DynamicArray<Integer> array = new DynamicArray<>(10);
        try {
            array.get(10);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
    }

    @Test
    public void createArray_TryToSetValueOutOfRange_GettingIndexOutOfBoundsException() {
        DynamicArray<Integer> array = new DynamicArray<>(10);
        try {
            array.set(10, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
    }

    @Test
    public void setValues_SetThreeValues_ValuesAreCorrect() {
        DynamicArray<Integer> array = new DynamicArray<>(10);
        array.set(0, 1);
        array.set(1, 2);
        array.set(2, 3);
        assertEquals(1, array.get(0).intValue());
        assertEquals(2, array.get(1).intValue());
        assertEquals(3, array.get(2).intValue());
    }

    @Test
    public void resize_ResizeArray_NewSizeIsCorrect() {
        try {
            DynamicArray<Integer> array = new DynamicArray<>(10);

            array.resize(20);
            assertEquals(20, array.getSize());

            array.resize(10);
            assertEquals(10, array.getSize());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void insert_TryToInsertValueOutOfRange_GettingIndexOutOfBoundsException() {
        DynamicArray<Integer> array = new DynamicArray<>(10);
        try {
            array.insert(10, 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
    }

    @Test
    public void insert_InsertValue_ValuesAreCorrect() {
        try {
            DynamicArray<Integer> array = new DynamicArray<>(10);
            array.set(0, 1);
            array.set(1, 2);
            array.set(2, 3);
            array.insert(1, 5);

            assertEquals(1, array.get(0).intValue());
            assertEquals(5, array.get(1).intValue());
            assertEquals(2, array.get(2).intValue());
            assertEquals(3, array.get(3).intValue());
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void pushBack_pushBackValue_ValuesAreCorrect() {
        DynamicArray<Integer> array = new DynamicArray<>(10);
        array.set(0, 1);
        array.set(1, 2);
        array.set(2, 3);
        array.pushBack(5);

        assertEquals(1, array.get(0).intValue());
        assertEquals(2, array.get(1).intValue());
        assertEquals(3, array.get(2).intValue());
        assertEquals(5, array.get(10).intValue());
    }

    @Test
    public void popBack_popBackFromEmptyArray_GettingUnsupportedOperationException() {
        try {
            DynamicArray<Integer> array = new DynamicArray<>(0);
            array.popBack();
            fail();
        } catch (Exception e) {
            assertEquals("Array is empty", e.getMessage());
        }
    }

    @Test
    public void popBack_TryGettingPopedBackValue_GettingException() {
        try {
            DynamicArray<Integer> array = new DynamicArray<>(3);
            array.set(0, 1);
            array.set(1, 2);
            array.set(2, 3);
            array.popBack();
            array.get(2);
            fail();
        } catch (Exception e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
    }

    @Test
    public void remove_TryRemovingIndexOutOfBounds_GettingIndexOutOfBoundsException() {
        try {
            DynamicArray<Integer> array = new DynamicArray<>(3);
            array.set(0, 1);
            array.set(1, 2);
            array.set(2, 3);
            array.remove(3);
            fail();
        } catch (Exception e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
    }

    @Test
    public void remove_RemoveOneValue_ValuesAreCorrect() {
        DynamicArray<Integer> array = new DynamicArray<>(10);
        array.set(0, 1);
        array.set(1, 2);
        array.set(2, 3);
        array.remove(1);

        assertEquals(1, array.get(0).intValue());
        assertEquals(3, array.get(1).intValue());

    }
}