package amazon.design;

/**
 * Created by yingtan on 3/26/18.
 *
 * 355. Design Twitter
 DescriptionHintsSubmissionsDiscussSolution
 Pick One
 Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

 postTweet(userId, tweetId): Compose a new tweet.
 getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 follow(followerId, followeeId): Follower follows a followee.
 unfollow(followerId, followeeId): Follower unfollows a followee.
 Example:

 Twitter twitter = new Twitter();

 // User 1 posts a new tweet (id = 5).
 twitter.postTweet(1, 5);

 // User 1's news feed should return a list with 1 tweet id -> [5].
 twitter.getNewsFeed(1);

 // User 1 follows user 2.
 twitter.follow(1, 2);

 // User 2 posts a new tweet (id = 6).
 twitter.postTweet(2, 6);

 // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 twitter.getNewsFeed(1);

 // User 1 unfollows user 2.
 twitter.unfollow(1, 2);

 // User 1's news feed should return a list with 1 tweet id -> [5],
 // since user 1 is no longer following user 2.
 twitter.getNewsFeed(1);
 */
import java.util.*;
public class DesignTwitter {
    public class Tweet {
        private int id;
        private int time;
        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }
    private Map<Integer, Set<Integer>> friends;
    private Map<Integer, LinkedList<Tweet>> posts;
    private int curTime = 0;
    /** Initialize your data structure here. */
    public DesignTwitter() {
        friends = new HashMap<>();
        posts = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (! posts.containsKey(userId)) {
            posts.put(userId, new LinkedList<>());
        }
        LinkedList<Tweet> tweets = posts.get(userId);
        Tweet newtweet = new Tweet(tweetId, curTime);
        curTime ++;
        tweets.addLast(newtweet);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        TweetComparator comp = new TweetComparator();
        PriorityQueue<Tweet> queue = new PriorityQueue<>(comp);
        //List<List<Tweet>> tweets = new ArrayList<>();
        if (posts.containsKey(userId)) {
            // get itself posts
            for (Tweet tweet : posts.get(userId)) {
                queue.offer(tweet);
            }
        }
        if (friends.containsKey(userId)) {
            Set<Integer> fs = friends.get(userId);
            for (Integer user: fs) {
                if (posts.containsKey(user)) {
                    for (Tweet tweet : posts.get(user)) {
                        queue.offer(tweet);
                    }
                }
            }
        }
        int k = 0;
        while(! queue.isEmpty() && k < 10) {
            res.add(queue.poll().id);
            k ++;
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        if (! friends.containsKey(followerId)) {
            friends.put(followerId, new HashSet<>());
        }
        friends.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        if (friends.containsKey(followerId)) {
            friends.get(followerId).remove(followeeId);
        }
    }
    public class TweetComparator implements Comparator<Tweet> {
        public int compare(Tweet t1, Tweet t2) {
            return t1.time - t2.time;
        }
    }
}
