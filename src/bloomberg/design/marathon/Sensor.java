package bloomberg.design.marathon;

/**
 * Created by yingtan on 11/15/15.
 */
public class Sensor {
    int no;

    public Sensor(int no) {
        this.no = no;
    }

    public void accept(Candidate d) {

        long curTime = System.currentTimeMillis();

        Pass newpass = new Pass(curTime, this);
        d.passes.add(newpass);

    }
}
