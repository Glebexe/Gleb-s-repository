public class Task_2{

    public int subtask_1_while(int num) {
        // Найти максимальное число, являющееся полным квадратом,
        // не превосходящее заданное натуральное num
        int max = 1, i = 1;
        while (i <= num){
            if (Math.sqrt(i) == (int)Math.sqrt(i)){
                max = i;
            }
            i++;
        }
        return max;
    }

    public int subtask_2_while(int num) {
        // Последовательность целых чисел p(n) определяется следующим образом:
        // p(0) = 1
        // p(1) = 2 * p(1) + 6, k > 0
        //Найти элемент последовательности с номером num;
        int[] cons = new int[num+1];
        cons[0] = 1;
        int i = 1;
        while (i <= num){
            cons[i] = 2 * cons[i - 1] + 6;
            i = i + 1;
        }
        return cons[num];
    }

    @Override
    public boolean subtask_3_while(int num, int base) {
        // Проверить, является ли число num натуральной степенью числа base
        int i = base;
        while (i < num){
            i *= base;
        }
        if (i == num)
            return true;
        else
            return false;
    }

    public int subtask_4_while(int start, int end) {
        // Вычислить, за какое минимальное число операций
        // вычесть 1
        // поделить на 2
        // число start можно превратить в end. Гарантируется, что start >= end >= 1
        int a = start, i = 0;
        while (a > end){
            if(a/2.0 == a/2 && a/2 > end)
                a /= 2;
            else
                a -= 1;
            i++;
        }
        return i;
    }
}
