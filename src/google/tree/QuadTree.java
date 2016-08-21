package google.tree;

import java.util.List;

/**
 * Created by yingtan on 11/5/15.
 */
public class QuadTree {

    public class QuadTreeNode {
        public int color;
        public QuadTreeNode[] nodes;

        public QuadTreeNode(int color) {
            this.color = color;
        }
    }
    /*
        * 四叉树压缩⿊黑⽩白图⽚片，⼀一个图⽚片递归分成2x2四部
        * 算⿊黑像素⽐比例
        *
        * */
    public double calBlackPixelPortion(int[][] image) {
        QuadTreeNode root = compressImage(image, 0, image[0].length-1, 0, image.length-1);
        double totalNum = image.length * image[0].length * (1.0);
        int maxDepth = (int) (Math.sqrt(totalNum) / 2 +1);

        int blackNum = calBlackPixelNum(root, 1, maxDepth);

        return blackNum / totalNum;
    }

    /*
    * follow up是给两个图⽚片，把⽩白⾊色视为不透明，⿊黑⾊色视为透明，
        重叠在⼀一起，返回⼀一个图⽚片，都⽤用四叉树表⽰示
    *
    * */
    public QuadTreeNode imageOverlap(QuadTreeNode img1, QuadTreeNode img2) {
        QuadTreeNode res = null;

        if ((img1 == null) && (img2 == null)) return res;
        else if ((img1 == null) && (img2 != null)) return img2;
        else if ((img1 != null) && (img2 == null)) return img1;

        // assume all not null
        if (img1.color ==0) {
            res = new QuadTreeNode(img2.color);
            res.nodes = img2.nodes;
        }
        else if (img2.color == 0) {
            res = new QuadTreeNode(img1.color);
            res.nodes = img1.nodes;
        }
        else if (img1.color == 2) {
            res = new QuadTreeNode(img1.color);
            res.nodes = img1.nodes;
        }
        else if (img2.color == 2) {
            res = new QuadTreeNode(img2.color);
            res.nodes = img2.nodes;
        }
        else { // all ones
            res = new QuadTreeNode(1);// grey
            res.nodes = new QuadTreeNode[4];
            if ((img1.nodes != null) && (img2.nodes != null)) {
                res.nodes[0] = imageOverlap(img1.nodes[0], img2.nodes[0]);
                res.nodes[1] = imageOverlap(img1.nodes[1], img2.nodes[1]);
                res.nodes[2] = imageOverlap(img1.nodes[2], img2.nodes[2]);
                res.nodes[3] = imageOverlap(img1.nodes[3], img2.nodes[3]);
            }
        }
        return res;
    }

    public QuadTreeNode compressImage(int[][] image, int rowlow, int rowhigh, int collow, int colhigh) {

        if ((rowlow > rowhigh) || (collow > colhigh)) return null;
        if ((rowlow == rowhigh) && (collow == colhigh)) {
            if (image[rowlow][collow] == 0) return new QuadTreeNode(0);
            else return new QuadTreeNode(2); // black color
        }

        int rowMed = (rowhigh + rowlow) / 2;
        int colMed = (collow + colhigh) / 2;

        QuadTreeNode leftUp = compressImage(image, rowlow, rowMed, collow, colMed);
        QuadTreeNode rightUp = compressImage(image, rowlow, rowMed, colMed+1, colhigh);
        QuadTreeNode leftDown = compressImage(image, rowMed +1, rowhigh, collow, colMed);
        QuadTreeNode rightDown = compressImage(image, rowMed +1, rowhigh, colMed+1, colhigh);

        QuadTreeNode curNode  = null;
        if ((leftUp.color == rightUp.color) && (rightUp.color == leftDown.color)
                && (leftDown.color == rightDown.color)) {
            if (leftUp.color == 0) {
                curNode = new QuadTreeNode(0);
                return curNode;
            }
            else if (leftUp.color == 2) {
                curNode = new QuadTreeNode(2);
                return curNode;
            }
        }
        curNode = new QuadTreeNode(1);
        curNode.nodes = new QuadTreeNode[4];
        curNode.nodes[0] = leftUp;
        curNode.nodes[1] = rightUp;
        curNode.nodes[2] = leftDown;
        curNode.nodes[3] = rightDown;

        return curNode;
    }

    public int calBlackPixelNum(QuadTreeNode root, int depth, int maxDepth) {
        if (root == null) {
            return 0;
        }
        QuadTreeNode[] nodes = root.nodes;
        if (nodes != null) {
            int res = 0;
            for (int i = 0 ; i < nodes.length ; i ++) {
                res = res + calBlackPixelNum(nodes[i],depth + 1, maxDepth);
            }
            return res;
        }
        else {
            if (root.color == 2) {
                if ((maxDepth - depth) > 0) {
                    System.out.println(maxDepth - depth);
                    return (maxDepth - depth) * 4;
                } else if (maxDepth - depth == 0) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] pixels = new int[][]{{1,1,1,1},{1,1,1,1},{1,0,0,1},{0,1,0,0}};
        QuadTree ob = new QuadTree();
        System.out.println(ob.calBlackPixelPortion(pixels));
    }

}
