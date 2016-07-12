package heap;

import java.util.*;

/**
 * Created by yingtan on 7/11/16.
 *
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

 postTweet(userId, tweetId): Compose a new tweet.
 getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 follow(followerId, followeeId): Follower follows a followee.
 unfollow(followerId, followeeId): Follower unfollows a followee.

 */
public class Twitter {

    public class Tweet {
        public int mTweetId;
        public int mTime;
        public Tweet(int tweetId, int time) {
            mTweetId = tweetId;
            mTime = time;
        }
    }

    private int mTime;
    private Map<Integer, PriorityQueue<Tweet>> mQueue; // userId -> heap of Tweets
    private Map<Integer, HashSet<Integer>> mFollowees; // userId -> followeeIDs

    /** Initialize your data structure here. */
    public Twitter() {
        mTime = 0;
        mQueue = new HashMap<>();
        mFollowees = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, mTime);
        mTime ++;
        PriorityQueue<Tweet> queue;
        if (! mQueue.containsKey(userId)) {
            TweetComparator comparator = new TweetComparator();
            queue = new PriorityQueue<Tweet>(comparator);
        }
        else {
            queue = mQueue.get(userId);
        }
        queue.offer(tweet);
        mQueue.put(userId, queue);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent. */

    public List<Integer> getNewsFeed(int userId) {
        HashSet<Integer> users = mFollowees.get(userId);
        if (users != null) {
            for (Integer user: users) {
                PriorityQueue<Tweet> queue = mQueue.get(user.intValue());


            }
        }

    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        HashSet<Integer> set;
        if (! mFollowees.containsKey(followerId)) {
            set = new HashSet<Integer>();
        }
        else {
            set = mFollowees.get(followerId);
        }
        set.add(followeeId);
        mFollowees.put(followerId, set);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (! mFollowees.containsKey(followerId)) {
            return;
        }
        HashSet<Integer> set = mFollowees.get(followerId);
        set.remove(followeeId);
    }

    public class TweetComparator implements Comparator<Tweet> {
        @Override
        public int compare(Tweet n1, Tweet n2) {
            if (n1.mTime > n2.mTime) {
                return 1;
            }
            else if (n1.mTime < n2.mTime)
                return -1;
            return 0;
        }
    }

}
