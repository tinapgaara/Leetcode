package google.geometric;

import java.util.HashSet;

/**
 * Created by yingtan on 7/30/17.
 *
 *
 Add to List
 391. Perfect Rectangle
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

 Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).

 http://www.cnblogs.com/dongling/p/5831106.html
 */
public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        /*
        有且只有:
 *   - 最左下 最左上 最右下 最右上 的四个点只出现过一次,其他肯定是偶数次（成对，即两次double）出现的(保证完全覆盖)
 *   - 上面四个点围成的面积,正好等于所有子矩形的面积之和(保证不重复)

        任意一个矩形均有4个顶点，当出现Perfect Rectangle时，在最大覆盖矩形内部，所有其它矩形的任意一个顶点均会出现偶数次（因为一个矩形旁边应当有一个或三个矩形和它紧密相连，那么这个相接的顶点就出现了偶数次）。所以，可以构建一个HashSet set,以一个矩形的四个顶点的(x,y)坐标构造字符串 (x+","+y),以该字符串做键值，当set中不存在该键值时，则存入该键值；当set中存在该键值时，则删除该键值；最终set中应该只剩下四个键，这四个键即是最大覆盖矩形的四个顶点。

最终考虑使用如下算法思想：

任意一个矩形均有4个顶点，当出现Perfect Rectangle时，在最大覆盖矩形内部，所有其它矩形的任意一个顶点均会出现偶数次（因为一个矩形旁边应当有一个或三个矩形和它紧密相连，那么这个相接的顶点就出现了偶数次）。所以，可以构建一个HashSet set,以一个矩形的四个顶点的(x,y)坐标构造字符串 (x+","+y),以该字符串做键值，当set中不存在该键值时，则存入该键值；当set中存在该键值时，则删除该键值；最终set中应该只剩下四个键，这四个键即是最大覆盖矩形的四个顶点。
        */

        if(rectangles.length==0||rectangles[0].length==0){
            return false;
        }
        else{
            HashSet<String> set=new HashSet<>();
            int leftMost=Integer.MAX_VALUE;
            int bottomMost=Integer.MAX_VALUE;
            int rightMost=Integer.MIN_VALUE;
            int topMost=Integer.MIN_VALUE;
            int x1,y1,x2,y2;
            long area=0;
            for(int[] rect:rectangles){
                x1=rect[0];
                y1=rect[1];
                x2=rect[2];
                y2=rect[3];
                area+=(x2-x1)*(y2-y1);//累积记录已遍历的矩形的面积
                //记录最靠边界的点的坐标
                if(x1<leftMost){
                    leftMost=x1;
                }
                if(y1<bottomMost){
                    bottomMost=y1;
                }
                if(x2>rightMost){
                    rightMost=x2;
                }
                if(y2>topMost){
                    topMost=y2;
                }
                if(area>(rightMost-leftMost)*(topMost-bottomMost)){
                    //目前遍历的矩形的面积已经大于了所能覆盖的面积，则一定存在了重叠
                    return false;
                }
                //由当前考察的矩形的四个顶点的坐标构成的键值
                String key1=x1+","+y1;
                String key2=x1+","+y2;
                String key3=x2+","+y1;
                String key4=x2+","+y2;
                //totalCouverCount用以记录是否出现了某个矩形完全覆盖了之前的某个矩形
                //删除那些出现了偶数次的键值
                if(set.contains(key1)){
                    set.remove(key1);
                }
                else{
                    set.add(key1);
                }

                if(set.contains(key2)){
                    set.remove(key2);
                }
                else{
                    set.add(key2);
                }

                if(set.contains(key3)){
                    set.remove(key3);
                }
                else{
                    set.add(key3);
                }

                if(set.contains(key4)){
                    set.remove(key4);
                }
                else{
                    set.add(key4);
                }
            }

            if(area!=(rightMost-leftMost)*(topMost-bottomMost)){//说明没有完全覆盖或出现了重叠覆盖
                return false;
            }

            String key1=leftMost+","+bottomMost;
            String key2=leftMost+","+topMost;
            String key3=rightMost+","+bottomMost;
            String key4=rightMost+","+topMost;
            if(set.size()!=4||!set.contains(key1)||
                    !set.contains(key2)||!set.contains(key3)||
                    !set.contains(key4)){
                return false;
            }
            else{
                return true;
            }
        }
    }
}
