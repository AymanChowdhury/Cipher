import java.util.*;

public class DecodingAlgorithm {
    public HashSet SolveAlphabeticCipher(Puzzle puzzle, int cut) {
        //if there are no untranslated words left, it returns a set containing the puzzle as a single solution
        if (puzzle.numberOfWordsWithStatus(Puzzle.UNTRANSLATED) == 0) {
            HashSet<Puzzle> set = new HashSet(1);
            set.add(puzzle);
            return set;
        }
        else {
            //initializes a list for the solutions
            LinkedList<Puzzle> solutions = new LinkedList();
            //picks a word that is UNTRANSLATED
            String puzzleWord = puzzle.nthWord(puzzle.easiestToTranslate());
            ArrayList<String> patternWithSamePatternAsPuzzleWord = puzzle.wordsWithTheSamePatternAs(puzzle.easiestToTranslate());
            //goes through each dictionary word that has the same pattern as the selected UNTRANSLATED word
            for (String patternWord : patternWithSamePatternAsPuzzleWord) {
                //proceeds a particular dictionary pattern-word if that pattern-word is possible in the first place
                if (puzzle.getTranslation().couldTranslateTo(puzzleWord, patternWord) == true) {
                    //makes a new Puzzle, sets the selected UNTRANSLATED word as "TRANSLATED" (assumption) and causes the word equivalencies to reflect that
                    Puzzle newPuzzle = new Puzzle(puzzle);
                    newPuzzle.TranslateNthWordTo(puzzle.easiestToTranslate(), patternWord);


                    //retrieves solutions and only accepts them if they are less than or equal to cut
                    HashSet<Puzzle> returnedSolutions = SolveAlphabeticCipher(newPuzzle,  cut);
                    Iterator<Puzzle> iterateThroughSolutions = returnedSolutions.iterator();
                    while (iterateThroughSolutions.hasNext()) {
                        Puzzle relevantPuzzle = iterateThroughSolutions.next();
                        String message = "";
                        for (int i = 0; i < newPuzzle.messageSize(); i++) {
                            message = message + newPuzzle.nthWord(i) + " ";
                        }
                        if (relevantPuzzle.numberOfWordsWithStatus(Puzzle.UNTRANSLATABLE) <= cut) {
                            solutions.add(relevantPuzzle);
                            cut = puzzle.numberOfWordsWithStatus(Puzzle.UNTRANSLATABLE);
                        }
                    }
                }
            }
            //considers the possibility that puzzleWord is not in the dictionary
            if (puzzle.numberOfWordsWithStatus(Puzzle.UNTRANSLATABLE) < cut) {
                Puzzle newPuzzle = new Puzzle(puzzle);
                newPuzzle.setStatus(newPuzzle.easiestToTranslate(), Puzzle.UNTRANSLATABLE);
                HashSet<Puzzle> returnedSolutions = SolveAlphabeticCipher(newPuzzle, cut);
                Iterator<Puzzle> iterateThroughSolutions = returnedSolutions.iterator();
                while (iterateThroughSolutions.hasNext()) {
                    Puzzle relevantPuzzle = iterateThroughSolutions.next();
                    if (relevantPuzzle.numberOfWordsWithStatus(Puzzle.UNTRANSLATABLE) < cut) {
                        solutions.add(relevantPuzzle);
                        cut = puzzle.numberOfWordsWithStatus(Puzzle.UNTRANSLATABLE);
                    }
                }
            }

            HashSet<Puzzle> overallSolution = new HashSet<>();
            if (solutions.size() > 0) {
                int minNum = solutions.get(0).numberOfWordsWithStatus(Puzzle.UNTRANSLATABLE);
                int length = solutions.size();
                for (int i = 1; i < length; i++) {
                    if (solutions.get(1).numberOfWordsWithStatus(Puzzle.UNTRANSLATABLE) < minNum) {
                        minNum = solutions.get(1).numberOfWordsWithStatus(Puzzle.UNTRANSLATABLE);
                        solutions.removeFirst();
                    }
                    else {
                        solutions.remove(1);
                    }
                }
            }
            ListIterator<Puzzle> puzzleIterate = solutions.listIterator();
            while (puzzleIterate.hasNext()) {
                overallSolution.add(puzzleIterate.next());
            }
            return overallSolution;
        }
    }
}
