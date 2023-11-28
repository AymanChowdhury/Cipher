import java.util.*;
public class WordGetter {
    public void GetWords(WordDictionary dict, String filename) {
        WordDictionary dictionary = new WordDictionary(new LinkedHashSet<String>());
        dictionary.Read(filename);
        WordMaker wordsDistinguish = new WordMaker();
        for (int i = 0; i < dictionary.Size(); i++) {
            String lineDissect = (String) dictionary.nextWord();
            for (int j = 0; j < lineDissect.length(); j++) {
                wordsDistinguish.addChar(lineDissect.charAt(j));
                //in order to separate the last word of a line with the first word of the next line
                if (j == lineDissect.length() - 1 && wordsDistinguish.getWord().length() > 0) {
                    wordsDistinguish.addChar(' ');
                }
                if (wordsDistinguish.wordReady() == true) {
                    dict.AddWord(wordsDistinguish.getWord());
                }
            }
        }
    }
}
