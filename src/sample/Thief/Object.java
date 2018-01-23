package sample.Thief;

public class Object {
    private String id;
    private int value;
    private int weight;

    public Object(String id, int value, int weight) {
        this.id = id;
        this.value = value;
        this.weight = weight;
    }
    public Object(Object object) {
        this.id = object.id;
        this.value = object.value;
        this.weight = object.weight;
    }
    @Override
    public String toString() {
        return "Object{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", weight=" + weight +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
