package bloomberg.design.topfrequent;

import java.util.*;

/**
 * Created by yingtan on 11/15/15.
 */
/*
*美国人打开chrome，说主页上会显示最长访问的8个网址，你如何实现这个功能。. 1point3acres.com/bbs
解法：max heap + hashmap
* */
public class TopFrequent {

    // times to visit some url
    private Map<String, Integer> visitTimes;
    private PriorityQueue<String> queue;

    public TopFrequent() {

        visitTimes = new HashMap<>();
        UrlComparator comparator = new UrlComparator();
        queue = new PriorityQueue<>(comparator);
    }

    public void clickUrl(String url) {
        if (visitTimes.containsKey(url)) {
            int count = visitTimes.get(url);
            visitTimes.put(url, count + 1);
        }
        else {
            visitTimes.put(url, 1);
        }

        queue.remove(url);
        queue.offer(url);
    }

    public List<String> extractTopKFrequentUrls(int k) {
        List<String> res = new ArrayList<>();
        for(int i = 0 ; i < k ; i ++) {
            res.add(queue.poll());
        }
        return res;
    }

    public class UrlComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            int count1 = visitTimes.get(s1);
            int count2 = visitTimes.get(s2);

            if (count1 > count2) {
                return -1;
            }
            else if (count1 < count2) {
                return 1;
            }
            else return 0;
        }
    }

    public static void main(String[] args) {
        TopFrequent ob = new TopFrequent();
        ob.clickUrl("h");
        ob.clickUrl("h");
        ob.clickUrl("e");
        ob.clickUrl("e");
        ob.clickUrl("e");
        ob.clickUrl("we");
        System.out.println(ob.extractTopKFrequentUrls(3));
    }

}
