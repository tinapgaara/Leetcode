package DP;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by yingtan on 1/16/18.
 *
 *
 1 2 3   seq: [1,3,4,6]. Can find it.
 3 4 5
 5 6 7
It is acceptable to visit a cell more than once.
 *
 */
public class SearchSeqIn2DArray {
    public boolean findSeq(int[][] matrix, int[] seq) {
        // normal recursion
        // can cache the itermediate result
        Set<Param> dp = new HashSet<>();
        return recurFind(matrix, 0, 0, seq, 0, dp);
    }
    public boolean recurFind(int[][] matrix, int i, int j, int[] seq, int index, Set<Param> dp) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || index > seq.length) {
            return false;
        }
        Param curParam = new Param(i, j, index);
        if (dp.contains(curParam)) {
            return true;
        }
        if (index == seq.length) {
            return true;
        }
        if (matrix[i][j] == seq[index]) {
            // recursion is called multiple times, can be cached. with the same param: (i,j, index),
            // can store these params to a new class, and map: param -> boolean
            boolean canFind = recurFind(matrix, i +1 , j , seq, index + 1, dp) ||
                    recurFind(matrix, i-1, j, seq, index + 1, dp) ||
                    recurFind(matrix, i, j + 1, seq, index + 1, dp) ||
                    recurFind(matrix, i, j - 1,  seq, index + 1, dp);
            if (canFind) {
                dp.add(curParam);
                return true;
            }

        }
        return false;
    }
    public class Param {
        int matrix_i;
        int matrix_j;
        int seq_index;

        public Param(int i, int j, int index) {
            matrix_i = i;
            matrix_j = j;
            seq_index = index;
        }
        @Override
        public boolean equals(Object o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            Param p = (Param)o;
            if (p.matrix_i == this.matrix_i && p.matrix_j == this.matrix_j && p.seq_index == this.seq_index) {
                return true;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(matrix_i, matrix_j, seq_index);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}, {3,4,5}, {5,6,7}};
        int[] seq = {1,3,4,6};
        SearchSeqIn2DArray ob = new SearchSeqIn2DArray();
        System.out.println(ob.findSeq(matrix, seq));
    }
}
