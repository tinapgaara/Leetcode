package google.mianjing;

/**
 * Created by erict on 2017/11/9.
 */
public abstract class FontSizeForRect {
    public abstract int getHeight(int fontSize);
    public abstract int getWidth(char ch, int fontSize);

    public int calcFontSize(String text, int width, int height, int minSize, int maxSize) {
        if ((text == null) || text.isEmpty()) return maxSize;
        return _calcFontSize(text, width, height, minSize, maxSize);
    }

    private int _calcFontSize(String text, int width, int height, int minSize, int maxSize) {
        if (maxSize == minSize) return minSize;
        else if (maxSize == minSize + 1) {
            if (suitable(text, width, height, maxSize))
                return maxSize;
            else
                return minSize;
        }

        int middle = (minSize + maxSize) / 2;
        if (suitable(text, width, height, middle))
            return calcFontSize(text, width, height, middle, maxSize);
        else
            return calcFontSize(text, width, height, minSize, middle - 1);
    }

    private boolean suitable(String text, int width, int height, int fontSize) {
        int lineHeight = getHeight(fontSize);
        int widthSoFar = 0, heightSoFar = 0;
        // 这里没有考虑text的前后空格的问题，要问面试官，需要考虑吗，如果要考虑，应该怎么算
        for (int i = 0; i < text.length(); i++) {
            if (heightSoFar > height) return false;
            widthSoFar += getWidth(text.charAt(i), fontSize);
            if (widthSoFar >= width) {
                heightSoFar += lineHeight;
                widthSoFar = 0;
            }
        }
        return true;
    }
}
