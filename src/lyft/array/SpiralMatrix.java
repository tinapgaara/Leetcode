package lyft.array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;
        int row = matrix.length;
        int col = matrix[0].length;
        int posx = 0;
        int posy = 0;
        int rowlen = row;
        int collen = col;
        while(rowlen > 0 && collen > 0) {
            if (rowlen == 1) {
                // base case
                while(collen > 0) {
                    res.add(matrix[posx][posy]);
                    posy ++;
                    collen --;
                }
                break;
            }
            if (collen == 1) {
                while(rowlen > 0) {
                    res.add(matrix[posx][posy]);
                    posx ++;
                    rowlen --;
                }
            }
            // go righter
            int len = 0;
            while(len < collen - 1) {
                res.add(matrix[posx][posy]);
                posy ++;
                len ++;
            }
            // go down
            len = 0;
            while(len < rowlen - 1) {
                res.add(matrix[posx][posy]);
                posx ++;
                len ++;
            }
            // go lefter
            len = 0;
            while(len < collen - 1) {
                res.add(matrix[posx][posy]);
                posy --;
                len ++;
            }
            // go up
            len = 0;
            while(len < rowlen - 1) {
                res.add(matrix[posx][posy]);
                posx --;
                len ++;
            }
            rowlen = rowlen - 2;
            collen = collen - 2;
            posx ++;
            posy ++;
        }
        return res;
    }
}
