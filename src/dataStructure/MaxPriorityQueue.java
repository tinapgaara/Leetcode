package dataStructure;

/**
 * Created by yingtan on 10/5/15.
 */
/*
* A priority queue is a data structure of maintaining a set of S of elements, each with an associated value called a key.
*
* Support following operations:
* Insert(S, x): insert a element x to S
* Maximum(S): get the largest key in the S
* Extract-Max(S): removes and returns the element with largest key
* Increase-Key(S, x, k): increase the value of x's key to the new value: k
*
* */

public class MaxPriorityQueue {

    public int[] m_num;
    public int m_heapSize;

    public int parent(int i) {
        return (i-1) / 2;
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    public MaxPriorityQueue(int[] num) {
        m_heapSize = num.length;
        m_num = num;
        for (int i = m_heapSize/2; i >= 0 ; i --) {
            maxHeap(i);
        }
    }

    public void maxHeap(int i) {
        int left = left(i);
        int right = right(i);

        int maxNo;
        if ( (left < m_heapSize) && (m_num[left] > m_num[i]) )  {
            maxNo = left;
        }
        else
            maxNo = i;
        if ( (right < m_heapSize) && (m_num[right] > m_num[maxNo]))
            maxNo = right;

        if (maxNo != i) {
            int tmp = m_num[maxNo];
            m_num[maxNo] = m_num[i];
            m_num[i] = tmp;

            maxHeap(maxNo);
        }
    }

    public int getMaxElement() {
        return m_num[0];
    }

    public int extractMaxElement() {
        int res = m_num[0];
        m_num[0] = m_num[m_heapSize-1];
        m_heapSize --;
        maxHeap(0);

        return res;
    }

    public void increaseKey(int i, int newkey) {
        m_num[i] = newkey;
        while ((i >= 0) && (m_num[parent(i)] < m_num[i])) {
            int parent = parent(i);
            int tmp = m_num[parent];
            m_num[parent] = m_num[i];
            m_num[i] = tmp;

            i = parent;
        }
    }

    public void insertElement(int key) {
        int[] newnum = new int[m_heapSize+1];
        for (int i = 0 ; i < m_heapSize ; i ++) {
            newnum[i] = m_num[i];
        }
        newnum[m_heapSize] = Integer.MIN_VALUE;
        m_num = newnum;
        increaseKey(m_heapSize, key);
        m_heapSize ++;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,4,2,5,7,0};
        MaxPriorityQueue ob = new MaxPriorityQueue(nums);
        // Important !!!: Find k top largest elements: Time : (O(n) + O(k * logn))
        int k = 3;
        int[] res = new int[k];
        for (int i = 0 ; i < k ; i ++) {
            res[i] = ob.extractMaxElement();
        }
    }

}
