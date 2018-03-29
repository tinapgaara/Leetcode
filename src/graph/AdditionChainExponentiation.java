package graph;

/**
 * Created by yingtan on 1/18/18.
 */
import java.util.*;
public class AdditionChainExponentiation {

    public List<Integer> expPath(int targetExp) {
        // starts from x with exp :1
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        Queue<List<Integer>> elementQueue = new LinkedList<>();
        List<Integer> init = new ArrayList<>();
        elementQueue.add(init);
        while(! queue.isEmpty()) {
            Integer cur = queue.poll();
            if (cur == targetExp) {
                return elementQueue.poll();
            }
            List<Integer> prevelements = elementQueue.poll();
            queue.offer(cur + cur);
            List<Integer> newelements = new ArrayList<>();
            newelements.addAll(prevelements);
            newelements.add(cur);
            elementQueue.offer(newelements);

            for (Integer elem : prevelements) {
                queue.offer(elem + cur);
                newelements = new ArrayList<>();
                newelements.addAll(prevelements);
                newelements.add(cur);
                elementQueue.offer(newelements);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        AdditionChainExponentiation ob = new AdditionChainExponentiation();
        System.out.println(ob.expPath(15));
    }

}
