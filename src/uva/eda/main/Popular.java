package uva.eda.main;

public class Popular {
    private String name;
    private int count;

    public Popular() {
        this.name = "";
        this.count = 0;
    }

    public Popular(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public void add() {
        count++;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return  name + ", con un total de " + count;
    }
}
