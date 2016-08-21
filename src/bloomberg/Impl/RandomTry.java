package bloomberg.Impl;

import java.util.Random;

/**
 * Created by yingtan on 10/27/15.
 */
public class RandomTry {

    public void random(){
        // 0- 1
        double random0between1 = Math.random();
        Random random = new Random();
        System.out.println(random.nextInt(40)); // random number between 0 - 40
    }

    public static  void main(String[] args) {
        RandomTry ob = new RandomTry();
        ob.random();
    }
}
