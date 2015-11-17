package bloomberg.design.stock;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yingtan on 11/16/15.
 */
public class WindowStockQueue {

    private int windowSize;
    private Queue<Double> queue;
    private Iterable<Double> iterStocks;
    private double curavg;

    public WindowStockQueue(Iterable<Double> stockIter, int windowSize) {
        queue = new LinkedList<>();
        iterStocks = stockIter;
        this.windowSize = windowSize;
        curavg = 0;
    }

    public double readNext() {
        // init
        if (queue.size() == 0) {
            double sum = 0;
            int count = 0;
            for (int i = 0 ; i < windowSize ; i ++) {
                if (iterStocks.iterator().hasNext()) {
                    double stockPrice = iterStocks.iterator().next();
                    sum = sum + stockPrice;
                    count ++;
                    queue.offer(stockPrice);
                }
                else
                    break;
            }

            if (count > 0) {
                curavg = sum / count;
            }
        }
        else {
            if (iterStocks.iterator().hasNext()) {
                double nextPrice = iterStocks.iterator().next();
                double firstPrice = queue.poll();
                curavg = (windowSize * curavg - firstPrice + nextPrice) / windowSize;
                queue.offer(nextPrice);
            }
        }
        return curavg;
    }
}
