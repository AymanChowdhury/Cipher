import java.util.ArrayList;
public class TreeNode {
    private String pattern;
    private ArrayList list;
    private TreeNode left;
    private  TreeNode right;

    public TreeNode(String word, String pat) {
        pattern = pat;
        list = new ArrayList();
        list.add(word);
        left = null;
        right = null;
    }

    public String getPattern() {
        return pattern;
    }

    public ArrayList getList() {
        return list;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setPattern(String str) {
        pattern = str;
    }

    public void setList(ArrayList wl) {
        list = wl;
    }

    public void setLeft(TreeNode node) {
        left = node;
    }

    public void setRight(TreeNode node) {
        right = node;
    }

}
