package linkedin.random;

import java.util.*;

public class RandomGenerator {
	
	//Random generate as given probability : 1/8: 0 ;  1/8 : 1    6/8 : 2

	// Solution 2: make a new list
	// [
	// 0:[0, 1/8],
	// 1:(1/8, 2/8],
	// 2:(2/8, 6/8]
	// ]
	// cal Math.random :1/12  ; drop in [0,1/8], return 0.

	//⼀一个数组
	//1,4,2,6.....
	/*
	*先找cumulative probability［1, 5, 7, 13]， 然后弄个0〜～13
	之间的random数字⽐比较过去找它的位置就好 (binary
	search)
	* */

	public int generateRandomNumber(int[] numbers, double[] probs) {
		for (int i = 1 ; i < probs.length; i ++) {
			probs[i] = probs[i-1] + probs[i];
		}

		double rand = Math.random();
		System.out.println("random:" + rand);
		for (int i = 0 ; i < probs.length; i ++) {
			if (rand <= probs[i]) {
				return numbers[i];
			}
		}
		return numbers[probs.length-1];
	}

	// optimizer
	public int generateRandomNumberOptimizer(int[] numbers, double[] probs) {
		double rand = Math.random();
		System.out.println("random:" + rand);
		for (int i = 1 ; i < probs.length; i ++) {
			probs[i] = probs[i-1] + probs[i];
		}
		return generateRandomeNumberBinarySearch(numbers, probs, rand, 0, probs.length -1);
	}

	// log(n): binary search interval
	public int generateRandomeNumberBinarySearch(int[] numbers, double[] probs, double rand, int low, int high) {
		if (low > high) return -1; // can not find

		int med = (low + high ) / 2;

		if ((med - 1 >= 0)) {
			if ((rand > probs[med-1]) && (rand <= probs[med])) return numbers[med];
		}
		else {
			if (rand <= probs[med]) return numbers[med];
		}
		if (rand > probs[med]) {
			return generateRandomeNumberBinarySearch(numbers, probs, rand, med + 1, high);
		}
		else {
			return generateRandomeNumberBinarySearch(numbers, probs, rand, low, med - 1);
		}
	}


	// Solution 2: make a new list [[0:[0,1],1:[1,2],2:[2,6]]], cal Math.random*10 : 4.5 ; drop in [2,6], return 2
	public int generateRandomUseRange(int[] machines)
	{
		List<List<Integer>> intervals = new ArrayList<List<Integer>>();
		int low = 0;
		int totalTimes = 0;
		for(int i=0;i<machines.length;i++)
		{
			int times = machines[i];
			totalTimes = totalTimes + times;
			int high = low + times;
			List<Integer> interval = new ArrayList<Integer>();
			interval.add(i);
			interval.add(low);
			interval.add(high);

			intervals.add(interval);

			low = high;
		}

		double randPoint = Math.random() * totalTimes;
		for(List<Integer> list : intervals)
		{
			int l = list.get(1);
			int h = list.get(2);

			if((randPoint >= l) && (randPoint <= h))
				return list.get(0);
		}
		return -1;
	}


	// Solution 1: make a new array [0,1,2,2,2,2,2,2], return array[rand.nextInt(10)]
	public int generateRandomFillArray(int[] machines)
	{
		int totalNumber = 0;
		for(int i = 0; i< machines.length;i++)
		{
			totalNumber = totalNumber + machines[i];
		}
		
		int[] newArray = new int[totalNumber];
		int k = 0;
		for(int i=0; i<machines.length;i++)
		{
			int times = machines[i];
			int val = i;
			for(int j=0;j<times;j++)
			{
				newArray[k] = val;
				k++;
			}
		}
		Random rand = new Random();
		int randIndex = rand.nextInt(totalNumber);
		System.out.println(randIndex);
		return newArray[randIndex];
	}
	


	// generate Cn,3  choose 3 elements from n elements randomly. : use set data structure
	public Set<Integer> generateChooseRandomUseSet(int[] A, int count)
	{
		Set<Integer> newArray = new HashSet<Integer>();
		
		while(newArray.size() < count)
		{
			int randIndex = (int) (Math.random()* (A.length));
			newArray.add(A[randIndex]);
		}
		
		return newArray;
	}
	
	// generate Cn,3  choose 3 elements from n elements randomly. : use shuffle
	public int[] generateChooseRandomShuffle(int[] A, int count)
	{
		for(int i=0;i<A.length;i++)
		{
			int randIndex = (int) (Math.random() * A.length);
			int tmp = A[randIndex];
			A[randIndex] = A[i];
			A[i] = tmp;
		}
		
		int[] res = new int[count];
		for(int i=0;i<count;i++)
		{
			res[i] = A[i];
		}
		return res;
	}

/*
	Given a infinite stream of number, return k random elements
	with equal probability.

	*/
	int curNumber = 0;
	public int[] getRandomKEqualProbability(int[] stream, int k) {
		for (int i = 0; i < stream.length; i ++) {
			curNumber ++;
		}
		int[] arr = new int[k];
		for (int i = 0 ; i < k ; i ++) {
			arr[i] = stream[i];
		}
		Random rd = new Random();
		for (int i = k ; i < curNumber; i ++) {
			int rand = rd.nextInt(i);// calculate random number bewteen [0, k]
			if (rand < k) {
				arr[rand] = stream[i];
			}
		}
		return arr;
	}

	/*
	Given a infinite stream of number, return a random element
	with equal probability.

	*/
	public int getRandomNumberEqualProbability(int[] stream) {
		for (int i = 0 ; i < stream.length ; i ++) {
			curNumber ++;
		}

		int randNum = stream[0];
		Random rd = new Random();
		for (int i = 1; i < curNumber; i ++) {
			int randIndex = rd.nextInt(i);

			if (randIndex < 1) {
				randNum = stream[i];
			}
		}
		return randNum;
	}

	
	public static void main(String[] args)
	{
		int[] machines = {1,2,3,4,5};
		double[] probs = new double[]{0.2, 0.4, 0.1, 0.1, 0.2};
		RandomGenerator ob = new RandomGenerator();
		System.out.println(ob.generateRandomNumberOptimizer(machines, probs));
	}

}
