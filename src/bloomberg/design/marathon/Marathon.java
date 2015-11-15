package bloomberg.design.marathon;

import java.util.*;

/**
 * Created by yingtan on 11/15/15.
 */
public class Marathon {

    public Candidate[] candidates;
    public Sensor[] sensors;

    private PriorityQueue<Candidate> queue;

    public Marathon(Candidate[] candidates, Sensor[] sensors) {
        this.candidates = candidates;
        this.sensors = sensors;

        CandidateComparator comparator = new CandidateComparator();
        queue = new PriorityQueue<>(comparator);
    }

    public void rank() {
        CandidateComparator comparator = new CandidateComparator();
        for (int i = 0 ; i < candidates.length ; i ++) {
            Arrays.sort(candidates, comparator);
        }
    }

    // when find out: d passes s recently:
    public void update(Sensor s, Candidate d) {

        s.accept(d);

        if (queue.contains(d)) {
            queue.remove(d); //  complexity ??
        }
        queue.offer(d); // complexity ??
    }

    public List<Candidate> updateTop10CandidatesRank() {
        List<Candidate> res = new ArrayList<>();

        // choose top 10:
        for (int i = 0 ; i < 10; i ++) {
            res.add(queue.poll());
        }

        return res;
    }

    public class CandidateComparator implements Comparator<Candidate> {
        @Override
        public int compare(Candidate c1, Candidate c2) {
            if (c1.passes.size() > c2.passes.size()) {
                return -1;
            }
            else if (c1.passes.size() < c2.passes.size()) {
                return 1;
            }
            else {
                Pass lastPass1 = c1.passes.get(c1.passes.size() -1);
                Pass lastPass2 = c2.passes.get(c2.passes.size() -1);

                if (lastPass1.timstamp > lastPass2.timstamp) {
                    return 1;
                }
                else if (lastPass1.timstamp < lastPass2.timstamp) {
                    return -1;
                }
            }
            return 0;
        }
    }

}
