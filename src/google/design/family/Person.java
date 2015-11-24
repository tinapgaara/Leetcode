package google.design.family;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 11/22/15.
 */
public class Person {

    private String name;
    private int gender;
    private Person father;
    private Person mother;

    private List<Person> children;

    public Person(String name, int gender, Person father, Person mother) {
        this.name = name;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        children = new ArrayList<Person>();
    }

    public Person LowestCommonAncestor(Person p1, Person p2) {
        if ((p1 == null) || (p2 == null)) {
            return null; // can not find
        }

        if ((p1.father == p2.father) || (p1.father == p2)){
            return p1.father;
        }
        else if ((p1.mother == p2.mother) || (p1.mother == p2)) {
            return p1.mother;
        }
        else if ((p1 == p2.father) || (p1 == p2.mother)) return p1;

        // Is not the Lowest common ancestor !!! random
        Person candidate1 = LowestCommonAncestor(p1.father, p2.father);
        if (candidate1 != null)
            return candidate1;

        Person candidate2 = LowestCommonAncestor(p1.father, p2.mother);
        if (candidate2 != null) {
            return candidate2;
        }

        Person candidate3 = LowestCommonAncestor(p1.mother, p2.father);
        if (candidate3 != null)
            return candidate3;

        Person candidate4 = LowestCommonAncestor(p1.mother, p2.mother);
        if (candidate4 != null) {
            return candidate4;
        }

        return null;
    }
}
