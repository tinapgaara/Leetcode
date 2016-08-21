package bloomberg.Impl;

/**
 * Created by yingtan on 11/16/15.
 */
public class QueueCircularArray<Integer> {

    private int[] elements;
    private int rear;
    private int front;
    private int MAX_SIZE;


    public QueueCircularArray() {
        MAX_SIZE = 4; // but actually, only can store 3 elements
        elements = new int[MAX_SIZE];

        front = 0;
        rear = 0;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        int num = (rear + 1) % MAX_SIZE;

        if (front == num) return true;
        else return false;
    }

    public void enqueue(int newelement) {
        if (!isFull()) {
            elements[rear] = newelement;
            rear  = (rear + 1) % MAX_SIZE;
        }
    }

    public int dequeue() {
        int res = elements[front];
        front  = (front + 1) % MAX_SIZE;
        return res;
    }

    public static void main(String[] args) {
        QueueCircularArray ob = new QueueCircularArray();
        ob.enqueue(2);
        ob.enqueue(3);
        ob.enqueue(4);
        ob.enqueue(5);
        System.out.println(ob.dequeue());
    }

}
