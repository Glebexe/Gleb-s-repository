public class Runner {

    public static void main(String[] args){
        Car car1 = new Car("красный",100,2000);
        Car car2 = new Car("синий",121,1000);

        car1.printWeight();
        car1.setWeight(1234);
        car1.printWeight();

    }
}
