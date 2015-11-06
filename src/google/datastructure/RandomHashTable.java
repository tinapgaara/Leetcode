package google.datastructure;

/**
 * Created by yingtan on 11/5/15.
 */
/*
* Implement HashTable with get,set,delete,getRandom
functions in O(1).
这题之前地⾥里有⼈人po过⾯面经，楼主很幸运地当时认真实现
过。。
重点在于2个hashmap+arraylist

考虑一下delete的时间复杂度也是需要O(1)的，如果只有一个map，那么delete操作的时候，
要遍历arrayList才能找到删除的是哪个index里面的key，那么时间复杂度就是O(N)了，所以我觉得应该还需要一个map来存key和arrayList的index的对应关系，
保证delete(Object key)的时候可以在O(1)的时间复杂度里定位arrayList中的相应位置
*
* */
public class RandomHashTable {
    // 1. hashmap:<key, value> : set:o(1), get:o(1)
    // 2. arraylist: <keys> : getrandom: list.get(random(n)) , o(1)
    // 3. hashmap<Key, indexInList>
    //      delete(Integer 2)
    //      indexinlist = map2.get(Integer 2)
    //      list.remove(indexinlist)

}
