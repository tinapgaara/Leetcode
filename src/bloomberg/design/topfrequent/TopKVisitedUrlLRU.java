package bloomberg.design.topfrequent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by yingtan on 11/16/15.
 */
public class TopKVisitedUrlLRU {

    private Map<String, String> urlInfo = new HashMap<>();
    private LinkedList<String> topKFrequent = new LinkedList<>();
    private int MAX_VALUE = 10;

    // first: old, last: latest
    public void update(String url, String newinfo) { // o(1)

        if (urlInfo.containsKey(url)) {
            urlInfo.put(url, newinfo);

            // must exsit in topKFrequent
            topKFrequent.remove(url); // o(1)
            topKFrequent.add(url);// lastest one // o(1)
        }
        else { // new one
            if (urlInfo.size() < MAX_VALUE) {
                urlInfo.put(url, newinfo);
                topKFrequent.addFirst(url);
            }
            else {
                String removedUrl = topKFrequent.removeFirst(); // old one
                urlInfo.remove(removedUrl);

                topKFrequent.add(url); // add new one
                urlInfo.put(url, newinfo);
            }
        }
    }
}
