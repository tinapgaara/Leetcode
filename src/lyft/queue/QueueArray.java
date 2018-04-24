package lyft.queue;
// implement a queue using array
public class QueueArray {
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public QueueArray(int capacity) {
        this.capacity = capacity;
        size = 0;
        front = 0;
        rear = -1;
        arr = new int[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == capacity;
    }
    public void enqueue(int x) {
        if (! isFull()) {
            rear = (rear + 1) % capacity;
            arr[rear] = x;
            size ++;
        }
    }
    public int dequeue() {
        if (! isEmpty()) {
            int res = arr[front];
            front = (front + 1) % capacity;
            size --;
            return res;
        }
        return -1;
    }
    public int size() {
        return size;
    }
    public int front() {
        return arr[front];
    }
    public int rear() {
        return arr[rear];
    }
    public static void main(String[] args) {
        QueueArray ob = new QueueArray(3);
        ob.enqueue(1);
        ob.enqueue(2);
        ob.enqueue(3);

        System.out.println(ob.dequeue());
        System.out.println(ob.dequeue());
        ob.enqueue(5);
        ob.enqueue(4);
        System.out.println(ob.dequeue());
        System.out.println(ob.front());
        System.out.println(ob.rear());
    }

}
