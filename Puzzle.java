import java.util.*;
public class Puzzle {
    public static final int UNTRANSLATED = 0;
    public static final int TRANSLATED = 1;
    public static final int UNTRANSLATABLE = 2;
    //puzzles to be solved
    private String[] puzzlesToSolve;
    //stores what each letter represents
    private Translation lettersAlreadyTranslated;
    //gives each puzzle word a state
    private int[] stateOfEachPuzzleWord;
    //the state of all the puzzles as a whole
    private int[] stateOfOverallPuzzle;
    //lists of words having the same pattern as the word to be translated
    private ArrayList<String>[] patternsOfPuzzleWords;

    public Puzzle(String[] message, PatternTable table) {
        puzzlesToSolve = new String[message.length];
        stateOfEachPuzzleWord = new int[message.length];
        stateOfOverallPuzzle = new int[3];
        patternsOfPuzzleWords = new ArrayList[message.length];
        for (int i = 0; i < message.length; i++) {
            puzzlesToSolve[i] = message[i];
            stateOfEachPuzzleWord[i] = UNTRANSLATED;
            patternsOfPuzzleWords[i] = table.WordsWithSamePatternAs(message[i]);
        }
        stateOfOverallPuzzle[0] = message.length;;
        stateOfOverallPuzzle[1] = 0;
        stateOfOverallPuzzle[2] = 0;
        lettersAlreadyTranslated = new Translation();
    }

    public Puzzle(Puzzle p) {
        int size = p.messageSize();

        puzzlesToSolve = new String[size];
        stateOfEachPuzzleWord = new int[size];
        stateOfOverallPuzzle = new int[3];
        patternsOfPuzzleWords = new ArrayList[size];
        lettersAlreadyTranslated = new Translation(p.getTranslation());

        for (int i = 0; i < size; i++) {
            puzzlesToSolve[i] = p.nthWord(i);
            stateOfEachPuzzleWord[i] = p.getStatus(i);
            patternsOfPuzzleWords[i] = p.wordsWithTheSamePatternAs(i);
        }
        for (int i = 0; i < 3; i++) {
            stateOfOverallPuzzle[i] = p.numberOfWordsWithStatus(i);
        }
    }

    public int messageSize() {
        return puzzlesToSolve.length;
    }

    public String nthWord(int n) {
        return puzzlesToSolve[n];
    }

    public int getStatus(int n) {
        return stateOfEachPuzzleWord[n];
    }

    public void setStatus(int n, int status) {
        stateOfOverallPuzzle[stateOfEachPuzzleWord[n]] = stateOfOverallPuzzle[stateOfEachPuzzleWord[n]] - 1;
        stateOfOverallPuzzle[status] = stateOfOverallPuzzle[status] + 1;
        stateOfEachPuzzleWord[n] = status;
    }

    public int numberOfWordsWithStatus(int status) {
        return stateOfOverallPuzzle[status];
    }

    public ArrayList wordsWithTheSamePatternAs(int n) {
        return patternsOfPuzzleWords[n];
    }

    public Translation getTranslation() {
        return lettersAlreadyTranslated;
    }

    //assumes that String word is of the same length as the nth word, has the same pattern as the nth word, could be translated to the nth word, and is a known word
    public void TranslateNthWordTo(int N, String word) {
        PatternMaker pattern = new PatternMaker();
        String puzzleWord = puzzlesToSolve[N];
        for (int i = 0; i < word.length(); i++) {
            String relevantLetter = puzzleWord.substring(i, i + 1);
            if (lettersAlreadyTranslated.isTranslated(relevantLetter) == false) {
                lettersAlreadyTranslated.translateLetterTo(relevantLetter, word.substring(i, i + 1));
            }
        }
        this.setStatus(N, TRANSLATED);
    }

    public int easiestToTranslate() {
        boolean proceed = true;
        int counter = -1;
        int indexOfEasiest = -1;
        do {
            counter++;
            if (stateOfEachPuzzleWord[counter] == UNTRANSLATED) {
                proceed = false;
            }
        } while (proceed == true && counter < puzzlesToSolve.length);
        if (proceed == false) {
            indexOfEasiest = counter;
            for (int i = counter + 1; i < puzzlesToSolve.length; i++) {
                if (stateOfEachPuzzleWord[i] == UNTRANSLATED && patternsOfPuzzleWords[i].size() < patternsOfPuzzleWords[indexOfEasiest].size()) {
                    indexOfEasiest = i;
                }
            }
        }
        return indexOfEasiest;
    }
}
