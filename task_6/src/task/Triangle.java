package task;

public class Triangle {
    private double a;
    private double b;
    private double c;
    public Triangle(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA(){
        return a;
    }
    public double getB(){
        return b;
    }
    public double getC(){
        return c;
    }

    public boolean isValid(){
        return a*a>b*b+c*c && b*b>a*a+c*c && c*c>b*b+a*a;
    }

    public double square(){
        double p = (a+b+c)/2;
        if(isValid())
            return Math.sqrt(p*Math.sqrt(p-a)*Math.sqrt(p-b)*Math.sqrt(p-c));
        else
            return -1;
    }

    public double perimeter(){
        if(isValid())
            return a+b+c;
        else
            return -1;
    }
}
