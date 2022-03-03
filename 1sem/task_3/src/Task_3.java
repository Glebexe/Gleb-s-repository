import java.util.Arrays;

public class Task_3 implements Task_3_base {
    @Override
    public int subtask_1_for(int n1, int n2, int a, int b) {
        // подсчитать, сколько чисел, кратных a, но не кратных b,
        // находится между числами n1 и n2 включительно
        int count = 0,min,max;
        if (n1 < n2) {
            min = n1;
            max = n2;
        }
        else{
            min = n2;
            max = n1;
        }
        for (int i = min; i <= max; i++) {
            if (i % a == 0 && i % b != 0)
                count++;
        }
        return count;
    }

    @Override
    public int subtask_2_for(int num) {
        // Последовательность чисел строится следующим образом:
        // сначала идет одна единица,
        // потом две двойки,
        // потом три тройки,
        // ...
        // потом n раз число n
        // ...
        // Найти, какое число будет находиться в этой последовательности
        // на позиции num
        String str = "";
        for(int i = 1; str.length() < num; i++){
            str += Integer.toString(i).repeat(i);
        }
        return Integer.parseInt(str.charAt(num-1)+"");
    }

    @Override
    public int subtask_3_for(int num, int d, int cnt) {
        // Дана последовательность
        // a(0) = num
        // a(n) = a(n - 1) * d + 1
        // Найти сумму первых cnt элементов последовательности
        int[] a = new int[cnt];
        a[0] = num;
        for(int i = 1; i < cnt; i++){
            a[i] = a[i-1] * d + 1;
        }
        return Arrays.stream(a).sum();
    }

    @Override
    public int subtask_4_for(int n) {
        // Вычислить сумму
        // S(n) = 1 + 1 * 2 + 1 * 2 * 3 + ... + n!
        // для заданного n
        // (n! - это n-факториал. Кто не знает - гуглите)
        int sum = 0,fact = 1;
        for(int i = 1; i <= n; i++){
            fact = 1;
            for(int j = 1; j <= i; j++){
                fact *= j;
            }
            sum += fact;
        }
        return sum;
    }
}
