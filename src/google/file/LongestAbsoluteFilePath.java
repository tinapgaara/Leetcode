package google.file;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yingtan on 7/20/17.
 *
 Add to List
 388. Longest Absolute File Path

 Suppose we abstract our file system by a string in the following manner:

 The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

 dir
 subdir1
 subdir2
 file.ext
 The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

 The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

 dir
 subdir1
 file1.ext
 subsubdir1
 subdir2
 subsubdir2
 file2.ext
 The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

 We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

 Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
 */
public class LongestAbsoluteFilePath {

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
}
