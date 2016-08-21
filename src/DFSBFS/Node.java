package DFSBFS;

/**
 * Created by yingtan on 9/18/15.
 */
public class Node {
    String m_word;
    int m_dist;
    Node m_prevWord;

    public Node(String word, Node node, int dist) {
        m_word = word;
        m_prevWord = node;
        m_dist = dist;
    }
}
