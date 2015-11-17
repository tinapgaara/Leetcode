package bloomberg.design.drawboard;

import java.util.List;

/**
 * Created by yingtan on 11/16/15.
 */
public class Group extends Shape { // composite design pattern

    // it extends Shape
    // but it also contains a list of Shape

    private List<Shape> lists;

    private Group subGroup;

    public List<Shape> getComponents() {
        return lists;
    }

    @Override
    public void move() {

        for (Shape shape: lists) {
            shape.move();;
        }
    }

    @Override
    public void resize() {

        for (Shape shape: lists) {
            shape.resize();
        }
    }

    @Override
    public void rotate() {

    }

    @Override
    public void paint() {

        for (Shape shape: lists) {
            shape.paint();
        }
    }
}
