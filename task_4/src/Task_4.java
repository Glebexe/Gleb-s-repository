import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task_4 implements Task_4_base {
    @Override
    public int[] subtask_1_arrays(int size, int a0, int d) {
        // сгенерировать и вернуть массив размера size, содержащий элементы
        // арифметической прогрессии с первым членом a0 и разностью d
        int[] intAr = new int[size];
        intAr[0] = a0;
        for(int i = 1; i < size; i++){
            intAr[i] = intAr[i-1]+d;
        }
        return intAr;
    }

    @Override
    public int[] subtask_2_arrays(int size) {
        // сгенерировать и вернуть массив размера size, первые два элемента
        // которого равны единице, а каждый следующий - сумме всех предыдущих
        int[] intAr = new int[size];
        intAr[0] = 1;
        if(size > 1) {
            intAr[1] = 1;
            for (int i = 2; i < size; i++) {
                intAr[i] = Arrays.stream(intAr).sum();
            }
        }

        return intAr;
    }

    @Override
    public int[] subtask_3_arrays(int size) {
        // сгенерировать и вернуть массив размера size, содержащий первые
        // size чисел последовательности фибоначчи.
        // https://ru.wikipedia.org/wiki/Числа_Фибоначчи
        int[] intAr = new int[size];
        intAr[0] = 0;
        if(size > 1) {
            intAr[1] = 1;
            for (int i = 2; i < size; i++) {
                intAr[i] = intAr[i - 1] + intAr[i - 2];
            }
        }

        return intAr;
    }

    @Override
    public int subtask_4_arrays(int[] data) {
        // Для данного массива вычислить максимальный элемент
        //
        // Я бы сделал всё просто в одну строчку Arrays.stream(data).max().getAsInt(),
        // но вдруг нужно сделать именно через цикл, поэтому на всякий случай я сделал и через него
        int max = data[0];
        for(int i = 0; i < data.length; i++){
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }

    @Override
    public int subtask_5_arrays(int[] data, int k) {
        // Для данного массива вычислить максимальный элемент
        // кратный k. Гарантируется, что как минумум один такой элемент
        // в массиве есть
        int max = -2147483646;
        for(int i = 0; i < data.length; i++){
            if(data[i] > max && data[i] % k == 0)
                max = data[i];
        }
        return max;
    }

    @Override
    public int[] subtask_6_arrays(int[] arr1, int[] arr2) {
        // Даны два массива arr1, arr2.
        // Произвести слияние данных массивов в один отсортированный
        // по возрастанию массив.
        ArrayList<Integer> intAr = new ArrayList<>();
        for(int i = 0; i < arr1.length; i++){
            intAr.add(arr1[i]);
        }
        for(int i = 0; i < arr2.length; i++){
            intAr.add(arr2[i]);
        }

        return intAr.stream().sorted().mapToInt(i->i).toArray();
    }

    @Override
    public int[] subtask_7_arrays(int[] arr1, int[] arr2) {
        // Даны два отсортированных по возрастанию массива arr1, arr2.
        // Произвести слияние данных массивов в один также отсортированный
        // по возрастанию массив.
        // Используйте алгоритм, время работы которого будет пропорционально сумме
        // размеров arr1 и arr2, а не их произведению
        ArrayList<Integer> intAr = new ArrayList<>();
        for(int i = 0; i < arr1.length; i++){
            intAr.add(arr1[i]);
        }
        for(int i = 0; i < arr2.length; i++){
            intAr.add(arr2[i]);
        }// не уверен что мой алгоритм самый быстрый и пропорционален сумме
        // размеров arr1 и arr2, но работает не долго, поэтому думаю он может быть решением этой задачи
        return intAr.stream().sorted().mapToInt(i->i).toArray();
    }
}
