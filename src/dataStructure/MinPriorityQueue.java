package dataStructure;

/**
 * Created by yingtan on 10/5/15.
 */
/*
* A priority queue is a data structure of maintaining a set of S of elements, each with an associated value called a key.
*
* Support following operations:
* Insert(S, x): insert a element x to S
* Minimum(S): get the smallest key in the S
* Extract-Min(S): removes and returns the element with smallest key
* Decrease-Key(S, x, k): decrease the value of x's key to the new value: k
*
* */
public class MinPriorityQueue {

    public int[] m_num;
    public int m_heapSize;

    public int parent(int i) {
        return i / 2;
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    public MinPriorityQueue(int[] num) {
        m_heapSize = num.length;
        m_num = num;
        for (int i = m_heapSize/2; i >= 0 ; i --) {
            minHeap(i);
        }
    }

    public void minHeap(int i) {
        int left = left(i);
        int right = right(i);

        int minNo;
        if ( (left < m_heapSize) && (m_num[left] < m_num[i]) ) {
            minNo = left;
        }
        else
            minNo = i;
        if ( (right < m_heapSize) && (m_num[right] < m_num[minNo]))
            minNo = right;

        if (minNo != i) {
            int tmp = m_num[minNo];
            m_num[minNo] = m_num[i];
            m_num[i] = tmp;

            minHeap(minNo);
        }
    }

    public int getMinElement() {
        return m_num[0];
    }

    public int extractMinElement() {
        int res = m_num[0];
        m_num[0] = m_num[m_heapSize-1];
        m_heapSize --;
        minHeap(0);
        return res;
    }

    public void decreaseKey(int i , int key) {
        m_num[i] = key;

        while ( (i >= 0) && (m_num[parent(i)] > m_num[i])) {
            int parent = parent(i);
            int tmp = m_num[parent];
            m_num[parent] = m_num[i];
            m_num[i] = tmp;

            i = parent;
        }
    }

    public void insertElement(int key) {
        int[] newnum = new int[m_heapSize+1];
        for (int i = 0 ; i < m_heapSize; i ++) {
            newnum[i] = m_num[i];
        }
        newnum[m_heapSize] = Integer.MAX_VALUE;
        m_num = newnum;

        decreaseKey(m_heapSize, key);
        m_heapSize ++;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,4,2,5,7,0};
        MinPriorityQueue ob = new MinPriorityQueue(nums); // buildHeap: O(n)

        // Important !!!! Find k smallest Elements: Time : (O(n) + O(k * logn))
        int k = 3;
        int[] res = new int[k];
        for (int i = 0 ; i < k ; i ++) { // k iterations
            res[i] = ob.extractMinElement(); // O(logn)
        }
    }
}
