public class WordMaker {
    public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ'";
    private String word = "";
    private boolean isReady = false;
    public void reset() {
        word = "";
    }
    public void addChar(char newChar) {
        if (letters.contains(Character.toString(newChar)) || letters.toLowerCase().contains(Character.toString(newChar))) {
            if (isReady == true) {
                reset();
                isReady = false;
            }
            if (newChar != '\'') {
                word = word + Character.toString(newChar);
            }
        }
        else {
            if (isReady == false) {
                isReady = true;
            }
        }
    }
    public boolean wordReady() {
        return isReady;
    }
    public String getWord() {
        return word;
    }
}
