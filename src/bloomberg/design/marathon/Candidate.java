package bloomberg.design.marathon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yingtan on 11/15/15.
 */
public class Candidate {

    String name;
    List<Pass> passes;

    public Candidate(String name) {
        this.name = name;
        passes = new ArrayList<>();
    }

}
