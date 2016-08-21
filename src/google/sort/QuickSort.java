package google.sort;

/**
 * Created by yingtan on 11/8/15.
 */
/*
* http://java67.blogspot.com/2014/07/quicksort-algorithm-in-java-in-place-example.html
*
* */
public class QuickSort {

    //前提：get last element as pivot
    public void quickSort_1(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition_1(arr, low, high);
            quickSort_1(arr, low, pivotIndex - 1);
            quickSort_1(arr, pivotIndex + 1, high);
        }
    }

    public int partition_1(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        // must be < high, not <=
        for (int j = low; j < high; j ++) {
            if (arr[j] >= pivot) {
                i ++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int tmp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = tmp;

        return i+1;
    }

    public void quickSort_2(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high) // Important !!!
            return;

        // pick the pivot
        int middle = (low + high) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {

            while (arr[i] > pivot) {
                i++;
            }

            while (arr[j] < pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // i turns to pivotIndex
        // recursively sort two sub parts
        if (low < i -1 )
            quickSort_2(arr, low, i - 1);

        if (high > i)
            quickSort_2(arr, i, high);
    }


    void quickSort_3(int arr[], int low, int high) {
        int index = partition(arr, low, high);
        if (low < index - 1)
            quickSort_3(arr, low, index - 1);
        if (index < high) // must be less
            quickSort_3(arr, index, high);
    }

    int partition(int arr[], int left, int right)
    {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {// limitation: left and right index can not be the same. At least diff >= 1
            while (arr[i] > pivot)
                i++;
            while (arr[j] < pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        };

        return i;
    }

    public int[] quickSort_4(int a[], int i, int j)// runtime:nlogn  memory:logn
    {
        int low = i;
        int high = j;

        if (low > high) return a;
        int key = a[(low+high)/2];

        while(i < j)
        {
            while(a[j] < key)
                j--;
            if(i < j)
            {
                int tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;

                i++;
            }
            while(a[i] > key)
                i++;
            if(i < j)
            {
                int tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;

                j--;
            }
        }

        if((i-1) > low)
            a = quickSort_4(a, low, (i - 1));
        if((i+1) < high)
            a = quickSort_4(a, (i + 1), high);

        return a;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{4,7,3,2,1,5,6};
        QuickSort ob = new QuickSort();
        ob.quickSort_1(nums, 0, nums.length - 1);
        System.out.println("");
    }
}
