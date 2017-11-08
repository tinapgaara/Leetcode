package greedy;

/**
 * Created by yingtan on 10/11/15.
 */
public class GasStation {

    public int canCompleteCircuit_withBestSolution(int[] gas, int[] cost) {
        if ((gas == null) || (cost == null) || (gas.length == 0) || (cost.length == 0) )
            return -1;

        int sum = 0;
        int[] index = new int[gas.length];
        int[] delta = new int[gas.length];
        for (int i = 0 ; i < gas.length; i ++) {
            delta[i] = gas[i] - cost[i];
            sum = sum + (gas[i] - cost[i]);
            index[i] = i;
        }
        if (sum < 0) return -1;
        // no need to do that since not asking the best solution
        quicksort(delta, gas, cost, index, 0, gas.length-1);
        return index[0];

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if ((gas == null) || (cost == null) || (gas.length == 0) || (cost.length == 0) )
            return -1;

        int sum = 0;
        int total = 0;
        int startPointMinusOne = -1;
        for (int i = 0 ; i < gas.length; i ++) {
            sum = sum + gas[i] - cost[i];
            total = total + gas[i] - cost[i];

            if (sum < 0) {
                // change starting point since the previous starting point i causes the minus value of gas in trip
                startPointMinusOne = i;
                // reset the sum
                sum = 0;
                // no need to change total
            }
        }
        if (total >= 0) {
            return startPointMinusOne  + 1; // important !!!!
        }
        else
            return -1;
    }

    public void quickSort(int[] gas, int[] cost,int[] delta, int[] index, int low, int high) {
        int i = low;
        int j = high;
        int midVal = delta[i];
        while (i < j) {
            while ((i < delta.length) && (delta[i] > midVal)) i ++;
            if (i < j) {
                int tmp = delta[i];
                delta[i] = delta[j];
                delta[j] = tmp;

                tmp = gas[i];
                gas[i] = gas[j];
                gas[j] = tmp;

                tmp = cost[i];
                cost[i] = cost[j];
                cost[j] = tmp;

                tmp = index[i];
                index[i] = index[j];
                index[j] = tmp;

                j --;
            }

            while ((j >= 0) && (delta[j] < midVal)) j --;

            if (i < j) {
                int tmp = delta[i];
                delta[i] = delta[j];
                delta[j] = tmp;

                tmp = gas[i];
                gas[i] = gas[j];
                gas[j] = tmp;

                tmp = cost[i];
                cost[i] = cost[j];
                cost[j] = tmp;

                tmp = index[i];
                index[i] = index[j];
                index[j] = tmp;

                i ++;
            }
            if ((j+1) < high) quickSort(gas, cost, delta, index, j+1, high);
            if ((i-1) > low) quickSort(gas, cost, delta, index, low, i-1);
        }
    }

    public int[] quicksort(int a[], int[] gas, int[] cost, int[] index, int i, int j)// runtime:nlogn  memory:logn
    {
        int low = i;
        int high = j;

        int key = a[low];

        while(i < j)
        {
            while( (j >= 0) && (a[j] < key))
                j--;
            if(i < j)
            {
                int tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;

                tmp = gas[i];
                gas[i] = gas[j];
                gas[j] = tmp;

                tmp = cost[i];
                cost[i] = cost[j];
                cost[j] = tmp;

                tmp = index[i];
                index[i] = index[j];
                index[j] = tmp;

                i++;
            }
            while ( (i < a.length) && (a[i] > key) )
                i++;
            if(i < j)
            {
                int tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;

                tmp = gas[i];
                gas[i] = gas[j];
                gas[j] = tmp;

                tmp = cost[i];
                cost[i] = cost[j];
                cost[j] = tmp;

                tmp = index[i];
                index[i] = index[j];
                index[j] = tmp;

                j--;
            }
        }

        if((i-1) > low)
            a = quicksort(a,gas, cost, index,low,(i-1));
        if((j+1) < high)
            a = quicksort(a,gas, cost, index,(j+1),high);

        return a;
    }

    public static void main(String[] args) {
        GasStation ob = new GasStation();
        int[] gas = new int[]{6,1,4,3,5};

        int[] cost = new int[]{3,8,2,4,2};
        int[] delta = new int[gas.length];
        //ob.quickSort(delta, 0, delta.length-1);
        ob.canCompleteCircuit(gas, cost);
    }
}
