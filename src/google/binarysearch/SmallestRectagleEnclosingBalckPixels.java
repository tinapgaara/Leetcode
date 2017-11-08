package google.binarysearch;

/**
 * Created by yingtan on 8/27/17.
 *
 * 302. Smallest Rectangle Enclosing Black Pixels
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

 For example, given the following image:

 [
 "0010",
 "0110",
 "0100"
 ]
 and x = 0, y = 2,
 Return 6.
 */
public class SmallestRectagleEnclosingBalckPixels {

    // sol1: binary search
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0) return 0;

        int row = image.length;
        int col = image[0].length;

        int up =  binarySearchLower(image, 0, x, false);
        int down = binarySearchHigher(image, x, row-1, false);
        int left = binarySearchLower(image, 0, y, true);
        int right = binarySearchHigher(image, y, col-1, true);

        return (right - left + 1) * (down - up + 1);
    }

    public int binarySearchLower(char[][] image, int low, int high, boolean isHorizontal) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (hasBlack(image, mid, isHorizontal)) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return high;
    }

    public int binarySearchHigher(char[][] image, int low, int high, boolean isHorizontal) {
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (hasBlack(image, mid, isHorizontal)) {
                low = mid;
            }
            else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean hasBlack(char[][] image,  int mid, boolean isHorizontal) {
        if (isHorizontal) { // search 竖向的，mid这一列 for col = mid , if exists 1
            for (int i = 0 ; i < image.length; i ++) {
                if (image[i][mid] == '1') return true;
            }
            return false;
        }
        else { // search horizontal，mid这一行 for col = mid , if exists 1
            for (int j = 0 ; j < image[0].length; j ++) {
                if (image[mid][j] == '1') return true;
            }
            return false;
        }
    }

    // sol2 brute force o(n^2)
    public int minArea_bruteForce(char[][] image, int x, int y) {
        if (image == null || image.length == 0) return 0;

        int row = image.length;
        int col = image[0].length;
        int left = y, right = y,  up = x, down = x;
        for (int i = 0; i < row; i ++) {
            for(int j = 0 ; j < col; j ++) {
                /*
                for example case, four 1s:
                   0             0             0            0
                2     2  ->   1     2  ->   1     2  ->  1     2
                   0             1             1            2
                */
                if (image[i][j] == 1) {
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    up = Math.min(up, i);
                    down = Math.max(down, i);
                }
            }
        }
        return (right - left + 1) * (down - up + 1);
    }

    public static void main(String[] args) {
        char[][] chs = new char[][]{{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}};
        SmallestRectagleEnclosingBalckPixels ob = new SmallestRectagleEnclosingBalckPixels();
        System.out.println(ob.minArea(chs, 0, 2));
    }
}
