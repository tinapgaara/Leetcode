package search;

/**
 * Created by yingtan on 9/20/15.
 */
public class MatrixSearch {
    // Solution 1: for increasing order, can use two pointers, and adjust their locations acording to distance between actual val and required cal
    public boolean searchMatrix_1(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        int col = matrix[0].length;

        int x = 0;
        int y = col-1;
        while ((x < row) && (y >= 0)) {
            if (target == matrix[x][y])
                return true;
            else if (target > matrix[x][y]) {
                x ++;
            }
            else {
                y --;
            }
        }
        return false;
    }

    // Solution 2: use binary search
    public boolean searchMatrix_2(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        int col = matrix[0].length;

        return bsMatrix(matrix, target, 0, row - 1, 0, col - 1);
    }
    public boolean bsMatrix(int[][] matrix, int target, int rowLow, int rowHigh,int colLow, int colHigh)     {
        int row = matrix.length;
        int col = matrix[0].length;

        if ( (rowLow > rowHigh) || (colLow > colHigh)) {
            return false;
        }

        int rowMiddle = (rowLow + rowHigh) / 2;
        int colMiddle = (colLow + colHigh) / 2;
        int middle = matrix[rowMiddle][colMiddle];

        if (target == middle) {
            return true;
        }
        else {
            if (target < middle) {
                boolean ifLeftUp =
                        bsMatrix(matrix, target, rowLow, rowMiddle - 1, colLow, colMiddle - 1);
                boolean ifLeftRowLine =
                        bsMatrix(matrix, target, rowMiddle, rowMiddle, colLow, colMiddle - 1);
                boolean ifLeftColLine =
                        bsMatrix(matrix, target, rowLow, rowMiddle - 1, colMiddle, colMiddle);
                if (! (ifLeftUp | ifLeftRowLine | ifLeftColLine)) {
                    boolean ifLeftDown =
                            bsMatrix(matrix, target, rowMiddle + 1, rowHigh, colLow, colMiddle - 1);
                    if (!ifLeftDown) {
                        return
                                bsMatrix(matrix, target, rowLow, rowMiddle - 1, colMiddle + 1, colHigh);
                    } else
                        return true;
                } else
                    return true;
            } else {
                boolean ifRightDown =
                        bsMatrix(matrix, target, rowMiddle + 1, rowHigh, colMiddle + 1, colHigh);
                boolean ifRightRowLine =
                        bsLine(matrix, target, rowMiddle, rowMiddle, colMiddle + 1, colHigh);
                boolean ifRightColLine =
                        bsLine(matrix, target, rowMiddle +1, rowHigh, colMiddle, colMiddle);
                if (!(ifRightDown | ifRightRowLine | ifRightColLine)) {
                    boolean ifLeftDown =
                            bsMatrix(matrix, target, rowMiddle + 1, rowHigh, colLow, colMiddle - 1);
                    if (!ifLeftDown) {
                        return
                                bsMatrix(matrix, target, rowLow, rowMiddle - 1, colMiddle + 1, colHigh);
                    } else
                        return true;
                } else
                    return true;
            }
        }
    }

    public boolean bsLine(int[][] matrix, int target, int rowLow, int rowHigh, int colLow, int colHigh)     {
        if (rowLow == rowHigh) {
            while(colLow <= colHigh) {
                int mid = (colLow + colHigh) / 2;
                if (matrix[rowLow][mid] == target) {
                    return true;
                }
                else if (bsLine(matrix, target, rowLow, rowHigh, colLow, mid -1)) {
                    return true;
                }
                else
                    return bsLine(matrix, target, rowLow, rowHigh, mid +1, colHigh);
            }
        }
        else if (colLow == colHigh) {
            while(rowLow <= rowHigh) {
                int mid = (rowLow + rowHigh) / 2;
                if (matrix[mid][colLow] == target) {
                    return true;
                }
                else if (bsLine(matrix, target, rowLow, mid-1, colLow, colHigh)) {
                    return true;
                }
                else
                    return bsLine(matrix, target, mid+1, rowHigh, colLow, colHigh);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MatrixSearch ob = new MatrixSearch();
        int[][] matrix = new int[5][5];
        matrix[0] = new int[]{1,   4,  7, 11, 15};
        matrix[1] = new int[]{2,   5,  8, 12, 19};
        matrix[2] = new int[]{3,   6,  9, 16, 22};
        matrix[3] = new int[]{10, 13, 14, 17, 24};
        matrix[4] = new int[]{18, 21, 23, 26, 30};
        System.out.println(ob.searchMatrix_1(matrix,10));
    }
}
