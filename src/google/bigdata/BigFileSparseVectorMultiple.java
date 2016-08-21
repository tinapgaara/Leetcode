package google.bigdata;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 11/28/15.
 */
/*
* 一个文件：
* 67
* 78
* 0
* 0
* 89
*
* 代表向量<67, 78, 0,0, 89>
*
* 两个文件进行向量点积， 会有很多稀疏值，怎么计算点积更加高效
*
* */
public class BigFileSparseVectorMultiple {

    // key: rowId, value: valye of entry in vector
    private Map<Integer, Integer> vector1;
    private Map<Integer, Integer> vector2;

    private int sum;

    public BigFileSparseVectorMultiple() {
        vector1 = new HashMap<>();
        vector2 = new HashMap<>();
        sum = 0;
    }

    public long calcPointProduct(File file1, File file2) {
        boolean eof1 = false;
        boolean eof2 = false;

        Map<Integer, Integer> v1, v2;
        while (!eof1 && !eof2) {
            eof1 = readFromFile(file1, vector1);
            eof2 = readFromFile(file2, vector2);

            if (vector1.size() < vector2.size()) {
                v1 = vector1;
                v2 = vector2;
            }
            else {
                v1 = vector2;
                v2 = vector1;
            }

            for (Integer line: vector1.keySet()) {
                Integer value = vector2.get(line);
                if (value != null) {
                    sum = sum * vector1.get(line) * value;
                }
            }
        }
        return sum;
    }

    public boolean readFromFile(File file, Map<Integer, Integer> vector) {
        return true;
    }
}
