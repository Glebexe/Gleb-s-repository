import java.util.Scanner;

public class Runner {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите свое фамилию и имя");
        String name = scanner.nextLine();
        Task_3 task = new Task_3();
        Task_3_Tests.test(task, name);

        boolean a = false;
        while(a == true){
            System.out.println(a);

        }

        for(int i = 0; i < 45; i++){
            System.out.println(i);
        }
    }
}
