package google.mianjing;

import java.util.*;

/**
 * Created by erict on 2017/11/6.
 */

/**
 * 给一个graph，从root进，每个node（房间）能通往一些node，edge上面有门，
 * 有些需要钥匙，有些不需要，需要钥匙的门必须当前持有那个钥匙才能去下一个房间。
 * 每个房间里有一个任意房间的key，而且也有可能有宝藏。
 * 需要做的就是走整个graph来找到宝藏，找不到就回false
 */

/**
 * 关键在于图的BFS或DFS算法，在其中针对题目要求进行一些改造
 */

public class RoomsTraverser {

    public class Node {
        public int m_roomNo;
        public int m_precious;
        public int m_key;  // 如果一个房间里可以有多个key，则改用List
        public List<DirectedEdge> m_edges;
    }

    public class RoomKey {
        public Node m_room;  // 这个key是开哪个房间的
    }

    public class DirectedEdge {
        public Node m_toNode;
        public boolean m_keyNeeded;
    }

    public class Graph {
        public Node m_root;
    }

    // 返回含有宝藏的某个房间
    public Node findAnyPrecious(Graph graph) {
        Queue<Node> accessable = new LinkedList<>();  // 可供访问的
        List<Node> toVisit = new ArrayList<>();  // 待访问的房间列表, without key
        // room id -> key
        Map<Integer, Integer> keys = new HashMap<>(); // all keys collected in process

        accessable.offer(graph.m_root);

        // 包含宝藏的某一个房间
        Node result = null;
        while ( (result == null) && ( ! accessable.isEmpty() ) ) {
            Node cur = accessable.poll();
            // 从accessable中任选一个Node，从它出发采用DFS或者BFS遍历，
            // 在遍历过程中，如果当前Node含有宝藏，则返回其房间号，结束
            if(cur.m_precious != 0) {
                return cur;
            }
            // 将房间里发现的每个key加入keys
            if (cur.m_key != 0) {
                keys.put(cur.m_key, cur.m_roomNo);
            }
            // 检查当前Node的每条边(m_edges)，如果这条边不需要key，则将它指向的Node加入到accessable
            //    如果这条边需要key，则将它指向的Node加入到toVisit
            for (DirectedEdge edge : cur.m_edges) {
                if (edge.m_keyNeeded) {
                    Node neededKeyRoom = edge.m_toNode;
                    toVisit.add(neededKeyRoom);
                }
                else {
                    accessable.offer(edge.m_toNode);
                }
            }

            if ( ! toVisit.isEmpty() ) {
                // 根据keys，将所有当前已经有key的房间从toVisit移动到accessable,
                // 同时将这些key从keys集合中删除, 因为它们已经被用掉了
                for(Node room : toVisit) {
                    if(keys.containsKey(room.m_roomNo)) {
                        accessable.offer(room);
                        keys.remove(room.m_roomNo);
                    }
                }
            }
        }
        return null;
    }

    // 返回所有宝藏
    public int findAllPrecious(Graph graph) {
        Queue<Node> accessible = new LinkedList<>();
        List<Node> toVisit = new ArrayList<>();
        // room id -> key
        Map<Integer, Integer> keys = new HashMap<>(); // all keys collected in process
        accessible.offer(graph.m_root);
        int count = 0;

        while(! accessible.isEmpty()) {
            Node curRoom = accessible.poll();
            if (curRoom.m_precious != -1) {
                count = count + curRoom.m_precious;
            }
            if (curRoom.m_key != -1) {
                // collect keys
                keys.put(curRoom.m_key, curRoom.m_roomNo);
            }
            for (DirectedEdge edge : curRoom.m_edges) {
                if (edge.m_keyNeeded) {
                    Node neededKeyRoom = edge.m_toNode;
                    toVisit.add(neededKeyRoom);
                }
                else {
                    accessible.offer(edge.m_toNode);
                }
            }
            if(! toVisit.isEmpty()) {
                for (Node toVisitRoom : toVisit) {
                    if (keys.containsKey(toVisitRoom.m_roomNo)) {
                        accessible.offer(toVisitRoom);
                        keys.remove(toVisitRoom.m_roomNo);
                    }
                }
            }
        }

        // 与前面基本相同，但是发现宝藏后不能结束，而是累加，等到最后accessable为空以后再返回累加值
        return count;
    }

    // 判断两个房间是否可达
    public boolean isReachable(Node fromRoom, Node toRoom) {

        Queue<Node> accessible = new LinkedList<>();
        List<Node> toVisit = new ArrayList<>();
        // room id -> key
        Map<Integer, Integer> keys = new HashMap<>(); // all keys collected in process
        accessible.offer(fromRoom);

        while ( ! accessible.isEmpty() ) {
            Node curRoom = accessible.poll();
            if (curRoom.equals(toRoom)) return true;

            // collect keys
            if (curRoom.m_key != -1) {
                keys.put(curRoom.m_roomNo, curRoom.m_key);
            }
            // loop neighbors
            for (DirectedEdge edge : curRoom.m_edges) {
                if (edge.m_keyNeeded) {
                    toVisit.add(edge.m_toNode);
                }
                else {
                    accessible.offer(edge.m_toNode);
                }
            }
            if (! toVisit.isEmpty()) {
                for (Node toVisitRoom : toVisit) {
                    if (keys.containsKey(toVisitRoom.m_roomNo)) {
                        accessible.offer(toVisitRoom);
                    }
                }
            }
        }
        return false;
    }

    /*

    // 计算从一个房间到另一房间的最短路径
    public List<Node> calcShortestPath(Node fromRoom, Node toRoom) {
        List<Node> resultPath = new ArrayList<>();
        int pathLengthSoFar = 0;
        if (fromRoom == toRoom) resultPath.add(fromRoom);
        else {
            // STEP1: 遍历图，计算图中每个Node的前驱Node (在Node类中设置保存前驱Node list的属性)
            //       （如果从A有一条边指向B，不管这条边是否需要key，A都是B的前驱）
            //        在遍历过程中建立一个从源Node到目标Node的hashMap，目标Node中包含进入源Node所需的key
            HashMap<Node, Node> hashMap = new HashMap<>();
            // 如果有多个keys可打开同一房间，这个hashMap要改成 HashMap<Node, List<Node>>
            traverse(graph.m_root, hashMap);

            // STEP2：针对toRoom的每个前驱节点predRoom 循环：
            for each 前驱节点predRoom loop {
                if 从predRoom到toRoom需要key {
                    Node roomWithKey = hashMap.get(predRoom);
                    // 如果有多个keys可打开同一房间, 需要对每个roomWithKey循环，这里假定每个房间只有一个key
                    List<Node> path1 = calcShortestPath(fromRoom, roomWithKey);
                    if (pathLengthSoFar == 0 || path1.size() + 2 < pathLengthSoFar) {
                        List<Node> path2 = calcShortestPath(roomWithKey, predRoom);
                        path = path1 拼接 path2;
                    }
                    else { continue; // 放弃这条路径，因为它不可能比已经求出的路径更短 }
                }
                else {
                    path = calcShortestPath(fromRoom, predRoom);
                }
                path.add(toRoom);
                paths.add(path);
            }
            if (pathLengthSoFar == 0) resultPath = paths中的最短路径;
            else resultPath = resultPath 与 paths中最短路径 的较短者;
            pathLengthSoFar = resultPath.size();
        }
        return resultPath;
    }

    private void traverse(Node node, HashMap<Node, Node> hashMap) {
        if (node.m_key != null) hashMap.put(node.m_key.m_room, node);
        if (node.m_edges != null) {
            for (DirectedEdge edge : node.m_edges) {
                edge.m_toNode.addPredNode(node); // 添加前驱节点
                traverse(edge.m_toNode, hashMap);  // 这样是DFS，也可以改成BFS
            }
        }
    }
    */
}
