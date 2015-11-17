package bloomberg.design.stock;

import java.util.*;

/**
 * Created by yingtan on 11/16/15.
 */
public class StockProfits {

    private Map<String, Stock> stocks;
    private PriorityQueue<Stock> stockQueues;

    public StockProfits() {
        stocks = new HashMap<>();
        ProfitsComparator comparator = new ProfitsComparator();
        stockQueues = new PriorityQueue<>(comparator);
    }

    public void update(String stockName, int price) {
        if (stocks.containsKey(stockName)) {
            Stock curStock = stocks.get(stockName);

            curStock.price = price;
            curStock.changeTimes ++;
            curStock.updatedTime = System.currentTimeMillis();

            // do we need to remove and add it again? : no need. will change it directly.
            // but need offer: to do maxheap
            stockQueues.remove(curStock);
            stockQueues.offer(curStock);

        }
        else {
            Stock stock = new Stock(stockName, price, price);
            stocks.put(stockName, stock);
            stockQueues.offer(stock);
        }
    }

    public void getTopkProfitsStocks(int k) {
        for (int i = 0 ; i< k ; i ++) {
            System.out.println(stockQueues.poll().name);
        }
    }

    public int currentProfits(String name) {
        if (stocks.containsKey(name)) {
            return stocks.get(name).price - stocks.get(name).initialPrice;
        }
        return 0;
    }

    public int currentPrice(String name) {
        if (stocks.containsKey(name)) {
            return stocks.get(name).price;
        }
        return 0;
    }

    public class ProfitsComparator implements Comparator<Stock> {
        @Override
        public int compare(Stock s1, Stock s2) {
            if ((s1.price - s1.initialPrice) < (s2.price - s2.initialPrice)) {
                return 1;
            }
            else if ((s1.price - s1.initialPrice) > (s2.price - s2.initialPrice)) {
                return -1;
            }
            else
                return 0;
        }
    }

    public class UpdatedTimeComparator implements Comparator<Stock> {
        @Override
        public int compare(Stock s1, Stock s2) {
            if (s1.updatedTime < s2.updatedTime) {
                return 1;
            }
            else if (s1.updatedTime > s2.updatedTime) {
                return -1;
            }
            else
                return 0;
        }
    }

    public class ChangeTimesComparator implements Comparator<Stock> {
        @Override
        public int compare(Stock s1, Stock s2) {

            if (s1.changeTimes < s2.changeTimes) {
                return 1;
            }
            else if (s1.changeTimes > s2.changeTimes) {
                return -1;
            }
            else
                return 0;
        }
    }

    public static void main(String[] args) {
        StockProfits ob = new StockProfits();
        Stock s1 = new Stock("h",0,0);
        ob.update("h", 0);
        ob.update("h", 1);

        Stock s2 = new Stock("w",0,0);
        ob.update("w", 0);
        ob.update("w", 2);
        ob.update("w", 3);

        ob.getTopkProfitsStocks(2);

    }

}
