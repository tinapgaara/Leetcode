package google.window;

/**
 * Created by yingtan on 11/26/15.
 */
/*
* ：给一个String[] array, 和任意一个移动的window size k， 对array里的元素位置进行改变，使得window里的元素不
重复. 要efficient的解法

第五题应该是字符串重排， 要求相同字符的间距>k的变种题.
使用Heap可以快速解决。 大概是HashMap统计词频， Entry<Character, Integer> 作为item 放进Max Heap,
以Integer重复数量作为排序因子。 然后每次从Heap取出k个元素append到StringBuffer, Integer分别-1之后再放回Heap. 再取出K个entry重复就好了
* */
public class ReorderArray {


}

