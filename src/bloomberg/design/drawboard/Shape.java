package bloomberg.design.drawboard;

/**
 * Created by yingtan on 11/16/15.
 */
public abstract class Shape {

    private int edgeWith;
    private int edgeColor;

    public abstract void move();
    public abstract void resize();
    public abstract void rotate();
    public abstract void paint();
}
