package bloomberg.design.stock;

/**
 * Created by yingtan on 11/16/15.
 */
public class Stock {
    String name;
    Integer price;
    Integer initialPrice;

    int changeTimes;
    long updatedTime;

    public Stock(String name, int price, int initialPrice) {
        this.name = name;
        this.price = price;
        this.initialPrice = initialPrice;

        changeTimes = 0;
        updatedTime = System.currentTimeMillis();
    }
}
