package bloomberg.design.stock;

/**
 * Created by yingtan on 11/16/15.
 */
public class WindowStock {

    private int windowSize;
    private double curAvg;
    private Iterable<Double> stockIter;
    private double[] cache;
    private int windowHead;

    public WindowStock(Iterable<Double> stockIter, int windowSize) {
        this.windowSize = windowSize;
        curAvg = 0;
        this.stockIter = stockIter;
        this.cache= new double[windowSize];
        windowHead = -1;
    }

    public double readNextAvg() {
        // init
        if (windowHead == -1) {
            double sum = 0;
            int count  = 0; // boundary case
            for (int i = 0 ; i < windowSize; i ++) {
                if (stockIter.iterator().hasNext()) {
                    double stockPrice = stockIter.iterator().next();
                    sum = sum + stockPrice;
                    cache[i] = stockPrice;

                    count ++;
                }
                else
                    break;
            }

            if (count > 0) {
                curAvg = sum / count;
                windowHead = 0;
            }
        }
        else {
            // get next element
            if (stockIter.iterator().hasNext()) {
                double nextStockPrice = stockIter.iterator().next();
                curAvg = (curAvg * windowSize - cache[windowHead] + nextStockPrice) / windowSize;

                // circular array
                cache[windowHead] = nextStockPrice;
                windowHead ++;
                if (windowHead >= windowSize) {
                    windowHead = 0;
                }
            }
        }
        return curAvg;
    }
}
