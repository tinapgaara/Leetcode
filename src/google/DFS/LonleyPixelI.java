package google.DFS;

/**
 * Created by yingtan on 9/17/17.
 *
 * 531. Lonely Pixel I
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a picture consisting of black and white pixels, find the number of black lonely pixels.

 The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

 A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

 Example:
 Input:
 [['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

 Output: 3
 Explanation: All the three 'B's are black lonely pixels.

 这道题定义了一种孤独的黑像素，就是该黑像素所在的行和列中没有其他的黑像素，让我们找出所有的这样的像素。那么既然对于每个黑像素都需要查找其所在的行和列，为了避免重复查找，我们可以统一的扫描一次，将各行各列的黑像素的个数都统计出来，然后再扫描所有的黑像素一次，看其是否是该行该列唯一的存在，是的话就累加计数器即可
 */
public class LonleyPixelI {

    public int findLonelyPixel(char[][] picture) {
        int row = picture.length;
        int col = picture[0].length;
        // rowBCount[i] shows how many black points in ith row
        int[] rowBCount = new int[row];
        // colBCount[i] shows how many black points in ith col
        int[] colBCount = new int[col];
        for (int i = 0 ; i < row ; i++)
            for (int j = 0 ; j < col ; j++)
                if (picture[i][j] == 'B') {
                    rowBCount[i]++;
                    colBCount[j]++;
                }

        int count = 0;
        for (int i=0;i<row;i++)
            for (int j=0;j<col;j++)
                if (picture[i][j] == 'B' && rowBCount[i] == 1 && colBCount[j] == 1) count++;

        return count;
    }
}
