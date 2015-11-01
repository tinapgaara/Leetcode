package vmware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 */
public class InterExtrapolation {

    public static void main(String[] args) {

        /*
		Scanner in = new Scanner(System.in);
		String strLine1 = in.nextLine();
		String strLine2 = in.nextLine();
		String strLine3 = in.nextLine();
		in.close();
		
		double result = 0;
		
		strLine1.trim();
		String[] numStrings1 = strLine1.split(" ");
		int count = Integer.parseInt(numStrings1[0]);
		if (count > 0) {
			int num = Integer.parseInt(numStrings1[1]);
			
			int[] x = new int[count];
			double[] y = new double[count];
			
			strLine2.trim();
			strLine3.trim();
			String[] numStrings2 = strLine2.split(" ");
			String[] numStrings3 = strLine3.split(" ");
			for (int i = 0; i < count; i++) {
				x[i] = Integer.parseInt(numStrings2[i]);
				y[i] = Double.parseDouble(numStrings3[i]);
			}
			
			result = (new InterExtrapolation()).insertValue(x, y, num);
            System.out.printf("%.2f\r\n", result);
		}
        */
         int[] x = new int[] {10, 25, 50, 100, 500};
         String[] y = new String[] {"27.32", "23.13", "21.25", "18.00", "15.50"};
         int num = 2000;
		 float result = new InterExtrapolation().Interpolate(num, x, y);
        BigDecimal bd = new BigDecimal(Float.toString(result));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd.floatValue());

    }

    static float Interpolate(int n, int[] amount, String[] ucost) {

        return insertValue(amount, ucost, n);
    }

    static float insertValue(int[] x, String[] y, int num) {

        if ( (x == null) || (x.length == 0) || (y == null) || (y.length == 0) ) return 0;
        List<Integer> indexs = new ArrayList<>();
        List<Float> values = new ArrayList<>();

        for (int i = 0 ; i < y.length; i++) {
            float f = Float.parseFloat(y[i]);
            if (f > 0) {
                if (num == x[i]) return f;
                indexs.add(x[i]);
                values.add(f);
            }
        }
        int numIndexs = indexs.size();
        if (numIndexs == 1) return values.get(0);
        
        quickSort(indexs, values, 0, numIndexs - 1);
        
        int prev = 0;
        int cur = 1;

        if (num <= indexs.get(0)) return calInterExtraPolation(indexs, values, prev, cur, num);

        while (cur < numIndexs) {
            int prevIndex = indexs.get(prev);
            int curIndex = indexs.get(cur);

            if ((num > prevIndex) && (num <= curIndex)) {
                return calInterExtraPolation(indexs, values, prev, cur, num);
            }
            
            prev ++;
            cur ++;
        }

        return calInterExtraPolation(indexs, values, prev - 1, cur - 1, num);
    }

    static float calInterExtraPolation(List<Integer> indexs, List<Float> values, int index1, int index2, int num) {
        int x1 = indexs.get(index1);
        int x2 = indexs.get(index2);
        float value1 = values.get(index1);
        float value2 = values.get(index2);

        float slope = (value2 - value1) / (x2 - x1);

        return (slope * (num - x1)) + value1;
    }

    static void quickSort(List<Integer> indexs, List<Float> values, int low, int high) {
        int i = low;
        int j = high;

        int midVal = indexs.get((i + j) / 2);
        while (i < j) {
            while (indexs.get(j) > midVal) {
                j --;
            }
            while (indexs.get(i) < midVal) {
                i ++;
            }
            if (i <= j) {
                int tmp = indexs.get(i);
                indexs.set(i, indexs.get(j));
                indexs.set(j, tmp);

                float dtmp = values.get(i);
                values.set(i, values.get(j));
                values.set(j, dtmp);

                j --;
                i ++;
            }
            if (i < high)
                quickSort(indexs, values, i+1, high);
            if (j > low) {
                quickSort(indexs, values, low, j-1);
            }
        }
    }
}
