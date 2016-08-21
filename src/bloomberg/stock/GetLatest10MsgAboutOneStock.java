package bloomberg.stock;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by yingtan on 11/14/15.
 */
/*
*接着美国小哥出Design的题，所有股票价格有 name, price 属性，股票价格在不断更新，怎么设计系统来获得某只股票最新
的10条股票信息
* */
public class GetLatest10MsgAboutOneStock {

    // key: stockName, value: list of historic prices: only include 10 top new prices
    Map<String, LinkedList<Integer>> stockProperties = new HashMap<>();

    public void update(String stock, int newprice) {
        LinkedList<Integer> prices = stockProperties.get(stock);

        if (prices.size() == 10) {
            prices.removeLast();
        }
        prices.add(0, newprice);
    }

    public LinkedList<Integer> getPrices(String stockName) {
        return stockProperties.get(stockName);
    }
}
