package google.greedy;

import java.util.*;

/**
 * Created by yingtan on 1/22/17.
 *
 * 406. Queue Reconstruction by Height
 *
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

 Note:
 The number of people is less than 1,100.

 Example

 Input:
 [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

 Output:
 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstruction {


    // solution 2: more concise o(n) ?

    public int[][] reconstructQueue2(int[][] people) {
        /*
        首先我们给队列先排个序，按照身高高的排前面，如果身高相同，则第二个数小的排前面。然后我们新建一个空的数组，遍历之前排好序的数组，然后根据每个元素的第二个数字，将其插入到res数组中对应的位置，参见代码如下
        eg:
        [7,0] [7,1] [6,1] [5,0] [5,2]
        make sure height小的必须在height大的后面,且people数量小的必须在people数量大的前面
        so greedy:
        [7,0] then [5,0] , if there is zero people higher than  7, then there must be zero people higher than 5.

        */
        Arrays.sort(people, (new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        }));
        // must use linkedlist, so add(index, element) is o(1)
        List<int[]> resultList = new LinkedList<>();
        for(int[] cur : people){
            // [7,0] which means insert 7 to index 0th
            resultList.add(cur[1], cur); // o(1)
        }
        return resultList.toArray(new int[people.length][]);
    }


    public class PeopleComparator implements Comparator<int[]> {
        @Override
        // sort according to the second(numBef) from small to large, then according to the first(height) from small to large
        public int compare(int[] o1, int[] o2) {
            if (o1[1] > o2[1]) {
                return 1;
            }
            else if (o1[1] < o2[1] ) {
                return -1;
            }
            else {
                if (o1[0] > o2[0]) {
                    return 1;
                }
                else if (o1[0] < o2[0] ) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public class People {
        int height;
        int numBefore;
        public People(int height, int numBefore) {
            this.height = height;
            this.numBefore = numBefore;
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        if (people == null) return null;

        PeopleComparator comparator = new PeopleComparator();
        Arrays.sort(people, comparator);

        int row = people.length;
        if (row < 1) return people;

        int col = people[0].length;

        List<People> list = new ArrayList<People>();
        list.add(new People(people[0][0], people[0][1]));

        for (int i = 1 ; i < row; i ++) {
            int height = people[i][0];
            int numBef = people[i][1];

            // find (numBef + 1)th element which >= height, insert this people before the guy
            // can not find, insert to tail
            int numLarger = 0;
            int insertPos = -1;

            // using greedy: get last ele from list
            People last = list.get(list.size() - 1);

            // Greedy:
            /*
              under what situation we need to go through the whole list and cal what pos of this element inserted

              Some greedy situation:
              Case 1:
              pre.height < cur.height
              prev.numBef == cur.numBef
              [5, 0] [7, 0]
              We can make sure cur people must be inserted after prev people
                 // if we insert [7, 0] [5, 0];  then [5, 0] must change to [5, 1]

              Case 2:
              prev.height == cur.height
              prev.numBef < cur.numBef
              [7, 0] [7, 1]
              We can make sure cur people must be inserted after prev people
                 // if we insert like [7, 1] [7, 0];  then [7, 0] must change to [7, 1]

             Case 3:
             prev.height < cur.height
             prev.numBef < cur.numBef
             [7, 0] [8, 1]
             We can make sure cur people must be inserted after prev people
                 // if we insert like [8, 1] [7, 0];  then [7, 0] must change to [7, 1]

             Case 4:
             prev.height < cur.height
             prev.numBef < cur.numBef
             [7, 0] [8, 1]
             We can make sure cur people must be inserted after prev people
                 // if we insert like [8, 1] [7, 0];  then [7, 0] must change to [7, 1]


             Only one confused case:
             prev.height > cur.height
             prev.numBef < cur.numBef
             [7, 0] [4, 1]
             We can not make sure [4, 1] must be inserted after [7, 0]
                // there could be like:  [6, 0] [4, 1] [7, 0]
                // in this case, we need to loop list, and find an insertion place for [4, 1]

            */
            if ( (height < last.height) && (numBef > last.numBefore) ) {
                for (int j = 0 ; j < list.size(); j ++) {
                    People p = list.get(j);
                    int compareHeight = p.height;

                    if (compareHeight >= height) {
                        numLarger ++;

                        if (numLarger == (numBef + 1)) {
                            // insert this people here
                            insertPos = j;
                            break;
                        }
                    }
                }
                if (insertPos != -1) { // find a place to insert
                    list.add(insertPos, new People(height, numBef));
                }
                else { // insert to tail
                    list.add(new People(height, numBef));
                }
            }
            else { // insert to tail
                list.add(new People(height, numBef));
            }
        }

        int[][] res = new int[row][col]; // o(n)
        for (int i = 0 ; i < list.size(); i ++) {
            res[i][0] = list.get(i).height;
            res[i][1] = list.get(i).numBefore;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] res = new int[][]{{7,0}, {4,4}, {7,1}, {5,0},{6,1}, {5,2}};

        QueueReconstruction ob = new QueueReconstruction();
        res = ob.reconstructQueue(res);
        //ob.sort(res);
        System.out.println(res);
    }

}
