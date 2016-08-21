package bloomberg.array;

import java.util.*;

/**
 * Created by yingtan on 10/26/15.
 */
public class TwitterHashTag {

    public class Tweet {
        String value;
        int count;

        public Tweet(String value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public class TweetComparator implements Comparator<Tweet> {
        @Override
        public int compare(Tweet t1, Tweet t2) {
            if (t1.count < t2.count) return 1;
            else if (t1.count > t2.count) return -1;
            else return 0;
        }
    }

    TweetComparator comparator = new TweetComparator();
    PriorityQueue<Tweet> queue = new PriorityQueue<>(comparator);
    HashSet<String> tagIndex = new HashSet<>();

    public void updateTopNHashTag(List<String> hashtags) {
        HashMap<String, Integer> tagCounts = new HashMap<>();

        for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
            String key = entry.getKey();
            int count = entry.getValue();

            if (tagIndex.contains(key)) {
                int oldCount = 0;
                // increase its count to ???
                queue.remove(new Tweet(key, oldCount));
            }
            else {
                tagIndex.add(key);
                queue.offer(new Tweet(key, count));
            }
        }
    }
}
