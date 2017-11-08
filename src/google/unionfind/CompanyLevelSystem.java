package google.unionfind;

/**
 * Created by yingtan on 11/5/17.
 */
public class CompanyLevelSystem {
    Node[] employees;
    public class Node {
        private Node manager;
        private int id;
        public Node(int id) {
            this.id = id;
        }
    }

    public CompanyLevelSystem(int number) {
        employees = new Node[number];
    }

    private void manage(int e1, int e2) {
        // e1 is e2's manager
        Node employee1;
        if (employees[e1] == null) {
            employee1 = new Node(e1);
            employee1.id = e1;
        }
        else {
            employee1 = employees[e1];
        }
        Node employee2;
        if (employees[e2] == null) {
            employee2 = new Node(e2);
            employee2.id = e2;
        }
        else {
            employee2 = employees[e2];
        }

        employee2.manager = employee1;
    }

    private void peer(int e1, int e2) {
        Node employee1 = employees[e1];

        Node employee2;
        if (employees[e2] == null) {
            employee2 = new Node(e2);
            employee2.id = e2;
        }
        else {
            employee2 = employees[e2];
        }
        employee1.manager = employee2.manager;
    }
    /*
    * 谢谢lz分享啊！请问第一题如果是manager 的manager 要怎么处理 is_manage 只需要判断直接上司嘛
-google 1point3acres
m的m也算数的
    * */
    private boolean isManage(int m, int e) {
        while(employees[e].manager != null) {
            if (employees[e].manager.id == m) return true;
            e = employees[e].manager.id;
        }
        return false;
    }
}
