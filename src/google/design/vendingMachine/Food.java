package google.design.vendingMachine;

/**
 * Created by yingtan on 11/26/15.
 */
public class Food {

    private Integer id;
    private String name;
    private Double price;
    private int count;

    public Food(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        count = 0;
    }

    public synchronized void addOne() {
        count ++;
    }

    public synchronized void minusOne() {
        count --;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
