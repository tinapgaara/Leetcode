package bloomberg.stock;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by yingtan on 11/12/15.
 */
/*。题目是写一个getLatestNStocks(int n)函数，返回最新n支股票的名字，不能有重复。比如
我的股票更新记录是：.鏈􀀀枃鍘熷垱鑷􀖺1point3acres璁哄潧
OMG. 1point 3acres 璁哄潧
LGD
EDG
LGD
AFK
假如要返回最新的4支股票的名字，则应该是[AFK,LGD,EDG,OMG
*
* */
public class GetLatestStock {

    HashMap<String, Long> map = new HashMap<>();
    LinkedList<String> stocksInTimeDecreasing = new LinkedList<>();

    public void getLatestNStocks(int n) {
        for (int i = 0 ; i < n ; i ++) {
            System.out.println(stocksInTimeDecreasing.get(i));
        }
    }

    public void addNewStock(String stock) {
        if (map.containsKey(stock)) {
            // update linkedlist: remove old one, insert new one
            stocksInTimeDecreasing.remove(stock);
            stocksInTimeDecreasing.add(0, stock);
        }
        else {
            stocksInTimeDecreasing.add(0, stock);
        }
        // update map
        map.put(stock, System.currentTimeMillis());
    }

}
