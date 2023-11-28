import java.util.HashSet;
import java.util.Iterator;

public class Driver {
    public static void main(String [] args) {
        DecodingAlgorithm algorithm = new DecodingAlgorithm();
        WordDictionary dictionary = new WordDictionary(new HashSet());
        WordGetter getWordsFromDict = new WordGetter();
        getWordsFromDict.GetWords(dictionary, "C:/Users/chowd/Documents/Useful Past Projects/Oxford English Dictionary.txt");

        PatternTable table = new PatternTable();
        int counter = 0;
        while (counter < dictionary.Size()) {
            table.AddWord(dictionary.nextWord());
            counter++;
        }

        String newestMessage = "rbo rpktigo vcrb bwucja wj kloj hcjd, km sktpqo, cq rbwr loklgo \n" +
                "vcgg cjqcqr kj skhcja wgkja wjd rpycja rk ltr rbcjaq cj cr.";
        newestMessage = newestMessage.replaceAll("[^A-Za-z ]","");
        String[] splitMessage = newestMessage.split(" ");

        Puzzle newestPuzzle = new Puzzle(splitMessage, table);
        long start = System.currentTimeMillis();
        HashSet<Puzzle> newestSolution = algorithm.SolveAlphabeticCipher(newestPuzzle, 0);
        long end = System.currentTimeMillis();

        Iterator<Puzzle> newestSolutionIterate = newestSolution.iterator();
        while (newestSolutionIterate.hasNext()) {
            Puzzle relevantPuzzle = newestSolutionIterate.next();
            Translation puzzleTranslate = relevantPuzzle.getTranslation();
            for (int i = 0; i < splitMessage.length; i++) {
                System.out.print(puzzleTranslate.translation(splitMessage[i] + " "));
            }
        }
        //System.out.println(end - start);
    }
}
