package bloomberg.binarysearch;

/**
 * Created by yingtan on 11/15/15.
 */
/*
* entry[1, 5, 9 15, 20]
* exit [2, 6, 18, 35，60]
里面存的是time stamp，记录是进入会议室的时间，给你一个时间，问你此时会议室里面有多少人，
* */

public class PeopleNumInMeetingRoom {

    public int numPeople(int[] entry, int[] exit, int time) {
        int len = entry.length;

        return binarySearchNumPeople(entry, exit, time, 0, len-1);
    }

    public int binarySearchNumPeople(int[] entry, int[] exit, int time, int low, int high) {

        if (low > high) return 0;

        int med = (low + high) / 2;

        int num = 0;
        if ((entry[med] < time) && (exit[med] > time)) {
            num ++;
            num = num + binarySearchNumPeople(entry, exit, time, low, med-1);
            num = num + binarySearchNumPeople(entry, exit, time, med+1, high);
        }
        else {
            if (entry[med] > time) {
                num = num + binarySearchNumPeople(entry, exit, time, low, med -1);
            }
            else if (exit[med] < time) {
                num = num + binarySearchNumPeople(entry, exit, time, med + 1, high);
            }
        }
        return num;
    }

    public static void main(String[] args) {
        PeopleNumInMeetingRoom ob = new PeopleNumInMeetingRoom();
        int[] entry = new int[]{1, 5, 9, 15, 20};
        int[] exit = new int[]{2, 6, 18, 35, 60};
        System.out.println(ob.numPeople(entry, exit, 22));
    }
}
