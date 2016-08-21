package bloomberg.Impl;

/**
 * Created by yingtan on 10/26/15.
 */
public class Matrix {

    int m_row;
    int m_col;

    int[][] m_matrix;

    public Matrix(int row, int col) {
        m_matrix = new int[row][col];
    }

    public void update(int x, int y, int value) {
        if ((x >= 0) && (x < m_row) && (y >= 0) && (y < m_col)) {
            m_matrix[x][y] = value;
        }
    }

    // sum 是求submatrix (x1, y1) topleft 到 (x2, y2) bottomright的和
    // how to do in O(1) ???
    /*
    public int sum(int x1, int y1, int x2, int y2) {

    }
    */
}
