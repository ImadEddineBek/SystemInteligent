package sample.FunctionMax;

import java.util.Random;

public class Individual implements Comparable<Individual> {
    private int value;
    public static boolean max = true;

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
        return 0.5 * value * value + value - 1;
    }
    public static Individual reproduce(Individual i1 , Individual i2,int length){
        int seperator = new Random().nextInt(length);
        int firstFirst = (int) (i1.value % Math.pow(10,seperator));
        int firstSecond = (int) (i2.value % Math.pow(10,seperator));
        int lastFirst = i1.value - firstFirst;
        int lastSecond = i2.value - firstSecond;
        Individual child1 = new Individual(lastFirst+firstSecond);
        Individual child2 = new Individual(lastSecond+firstFirst);
        if (child1.getFitness()>child2.getFitness())return child1;
        return child2;
    }

    @Override
    public String toString() {
        return "value = "+value;
    }
}
