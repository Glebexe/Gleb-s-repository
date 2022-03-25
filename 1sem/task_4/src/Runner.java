import java.util.Scanner;

public class Runner {
    public static void main(String[] args){
        //p(0) = 1
        //p(n) = 3*(p(n-1)+6)
        int[] arr = new int[]{1,2,3,4,5,7};
        for(int i: arr) {
            System.out.println(i);
        }
    }
}
