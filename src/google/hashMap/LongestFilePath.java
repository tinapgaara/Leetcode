package google.hashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 7/19/17.
 */
public class LongestFilePath {

    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) return 0;

        /* Usage of map: file system depth -> length of path
        dir \n <1, 3+1>
        \tsubdir22 \n  <2, 12+1>
        \tsubdir2  \n  <2, 11+1>
        \t\tfile.ext \n <3, 21+1>

        Careful:
        can't cancate subdir22 with file.ext, only can cancate subdir2 with file.ext
        */
        Map<Integer, Integer> depth2PathLen = new HashMap<Integer, Integer>();
        int res = 0;
        depth2PathLen.put(0, 0);
        // will generate dir, \tsubdir, \tsubdir ...
        for (String part : input.split("\n")) {
            // if no \t, will return -1
            int depth = part.lastIndexOf("\t") + 1;
            int pathLen = depth2PathLen.get(depth);
            String curFile = part.substring(depth);
            if (curFile.contains(".")) {
                // this is a file, we can update result
                res = Math.max(res, pathLen + curFile.length());
            }
            else {
                // this is a path, update the pathLen corresponding to this depth, no need to cal max here because
                // if the depth+1 key is already here,this means this is a new turn starting from a different root,just overwrite
                // pathLen + curFile.length() + 1 (+1 is for \n)
                depth2PathLen.put(depth + 1, pathLen + curFile.length() + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestFilePath ob = new LongestFilePath();
        ob.lengthLongestPath("dir\n\tsubdir1.txt");
    }
}
