package sample.FunctionMax;

import java.util.Random;

public class Individual implements Comparable<Individual> {
    private int value;
    public static boolean max = true;
    public static boolean function = true;

    public Individual(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Individual o) {
        if (!max)
            return (int) (getFitness() - o.getFitness());
        return (int) (o.getFitness() - getFitness());
    }

    public double getFitness() {
        return getY(value);
    }
    public static double getY(double x){
        if (function)
            return + 0.5 * x * x + x -1;
        else return 100*Math.sin(x/5) + 100*Math.log(Math.sqrt(3)*x/20)*100000 + (Math.pow(-x,2))/1000;
    }
    public static Individual reproduce(Individual i1 , Individual i2,int length){
        int seperator = new Random().nextInt(length);
        int firstFirst = (int) (i1.value % Math.pow(10,seperator));
        int firstSecond = (int) (i2.value % Math.pow(10,seperator));
        int lastFirst = i1.value - firstFirst;
        int lastSecond = i2.value - firstSecond;
        Individual child1 = new Individual(lastFirst+firstSecond);
        Individual child2 = new Individual(lastSecond+firstFirst);
        if (child1.compareTo(child2)>0)return child1;
        return child2;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
