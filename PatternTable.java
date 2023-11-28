import java.util.ArrayList;
public class PatternTable {
    private TreeNode root;

    //adds a word to the table
    public void AddWord(String word) {
        String patternOfWord = new PatternMaker().MakePattern(word);
        root = addWordToTree(word, patternOfWord, root);
    }

    //will return an ArrayList containing all the words in the table with the same pattern as the given word
    public ArrayList WordsWithSamePatternAs(String word) {
        String patternOfWord = new PatternMaker().MakePattern(word);
        return findPatternInTree(patternOfWord, root);
    }

    /*
    This Will search the tree for the pattern given.
    If it finds it, it returns the associated ArrayList.
    If not, it returns a new ArrayList.
     */
    private ArrayList findPatternInTree(String pat, TreeNode tree) {
        if (tree == null) {
            return new ArrayList();
        }
        else if (tree.getPattern().equals(pat) == true) {
            return tree.getList();
        }
        else if (pat.compareTo(tree.getPattern()) > 0) {
            return findPatternInTree(pat, tree.getRight());
        }
        else {
            return findPatternInTree(pat, tree.getLeft());
        }
    }

    /*
    This will search the tree for a node containing the given pattern.
    If it finds one, it adds the word to the pattern’s word list.
     If not, it adds the pattern to the tree with the word as a single member of its ArrayList. It returns the root of tree.
     */
    private TreeNode addWordToTree(String word, String pat, TreeNode tree) {
        if (tree == null) {
            return new TreeNode(word, pat);
        }
        else {
            if (pat.equals(tree.getPattern()) == true) {
                tree.getList().add(word);
            }
            else if (pat.compareTo(tree.getPattern()) > 0) {
                tree.setRight(addWordToTree(word, pat, tree.getRight()));
            }
            else {
                tree.setLeft(addWordToTree(word, pat, tree.getLeft()));
            }
        }
        return tree;
    }


}
