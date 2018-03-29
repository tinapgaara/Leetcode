package sort;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingtan on 12/12/17.
 */
public class SortArrayWithManyRepeatedEntries {

    public class Person {
        public String name;
        public int age;
        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    Map<Integer, Integer> ageToCount = new HashMap<>();
    Map<Integer, Integer> ageOffset = new HashMap<>();

    public void group(List<Person> person) {
        int offset = 0;
        for (Map.Entry<Integer, Integer> entry : ageToCount.entrySet()) {
            ageOffset.put(entry.getKey(), offset);
            offset = offset + entry.getValue();
        }
        for (Map.Entry<Integer, Integer> entry : ageOffset.entrySet()) {
            int age = entry.getKey();
            int pos = entry.getValue();
            Person wrongperson = person.get(pos);
            int supposedPos = ageOffset.get(wrongperson.age);
            Collections.swap(person, pos, supposedPos);
            int count = ageToCount.get(age) - 1;
            ageToCount.put(age,count);
            if (count > 0) {

            }

        }
    }

}
