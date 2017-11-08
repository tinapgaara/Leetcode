package google.topo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yingtan on 9/28/17.
 */
public class CourseScheduleIIMultiThread {

    public class Node {
        int val;
        int indegree;
        int color;

        List<Node> neighbors;

        public Node(int val)
        {
            this.val = val;
            this.indegree = 0;
            this.color = 0;
            this.neighbors = new ArrayList<>();
        }
    }

    int threadNum = 10;
    long waitQueueTime = 100;

    BlockingQueue<Node> queue = new ArrayBlockingQueue<Node>(threadNum);

    public void findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            int[] resEmpty = new int[numCourses];
            for (int i = 0 ; i < resEmpty.length; i ++) {
                resEmpty[i] = i;
            }
            //return resEmpty;
        }

        List<Integer> res = new ArrayList<Integer>();
        boolean[][] adj = new boolean[numCourses][numCourses];
        int[] color = new int[numCourses];

        // build graph
        for (int i = 0 ; i < prerequisites.length; i ++) {
            int start = prerequisites[i][0];
            int end = prerequisites[i][1];

            // construct graph and put node whose indegree == 0 to the queue
        }
        Integer finishCourseNumber = 0;
        CourseThread[] threads = new CourseThread[3];
        for(int i = 0 ; i < threads.length ; i ++) {
            threads[i] = new CourseThread(queue, waitQueueTime, finishCourseNumber);
            threads[i].start();
        }

        // check if all courses are finished
        boolean check = true;
        while(check) {
            if (finishCourseNumber.intValue() == numCourses) {
                // finished all
                check = false;
                for (CourseThread thread : threads) {
                    thread.setTaskFinished();
                }
            }
            else {
                try {
                    Thread.sleep(waitQueueTime);
                } catch (InterruptedException e) {

                }
            }
        }

    }

    public class CourseThread extends Thread
    {
        BlockingQueue<Node> queue;
        private long waitQueueTime;
        private boolean taskFinished;
        private Integer finishedCourseNumber;

        public CourseThread(BlockingQueue<Node> queue, long waitQueueTime, Integer finishedCourseNumber)
        {
            this.queue = queue;
            this.waitQueueTime = waitQueueTime;
            this.finishedCourseNumber = finishedCourseNumber;
        }
        @Override
        public void run()
        {
            while(! taskFinished)
            {
                if(! queue.isEmpty())
                {
                    Node node = null;
                    // keep waiting until get the node from the poll
                    try {
                        node = queue.poll(waitQueueTime, TimeUnit.MICROSECONDS);
                    } catch(InterruptedException e) {

                    }
                    if (node != null) {
                        int color = node.color;
                        // mark as visited, execute the course
                        node.color = 1;
                        finishOneMoreCourse();

                        for (Node neighbor : node.neighbors) {
                            neighbor.indegree --;
                            if (neighbor.indegree == 0 && neighbor.color == 0) {
                                //Inserts the specified element into this queue, waiting if necessary
                                // for space to become available.
                                queue.offer(neighbor);
                            }
                        }
                    }
                }
                else {
                    try {
                        Thread.sleep(waitQueueTime);
                    } catch (Exception e) {

                    }

                }
            }
        }


        public void setTaskFinished()
        {
            taskFinished = true;
        }

        public synchronized void finishOneMoreCourse() {
            this.finishedCourseNumber ++;
        }
    }

}
