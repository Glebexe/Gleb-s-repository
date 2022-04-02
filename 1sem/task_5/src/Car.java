public class Car {

    private String colour;
    private int maxSpeed;
    private int weight;

    public Car(String colour, int maxSpeed, int weight){
        this.colour = colour;
        this.maxSpeed = maxSpeed;
        this.weight = weight;
    }

    public void printWeight(){
        System.out.println(weight);
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int value){
        weight = value;
    }
}
