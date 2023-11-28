import java.util.*;
public class Translation {
    private HashMap letterEquivalents;

    public Translation() {
        letterEquivalents = new HashMap();
    }

    public Translation(Translation t) {
       /*
       Even though I'll try to only use upper case letters when translating one letter to another (since it seems as though
       a monoalphabetic substitution cipher doesn't distinguish between upper case letters and lower case letters), I have accounted
       for both uppercase and lowercase letters when checking for the decoded letters o Translation T just in case that is necessary
       for whatever reason later on.
        */
        letterEquivalents = new HashMap();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabet = alphabet + alphabet.toLowerCase();
        for (int i = 0; i < alphabet.length(); i++) {
            String relevantLetter = alphabet.substring(i, i + 1);
            if (t.isTranslated(relevantLetter) == true) {
                this.translateLetterTo(relevantLetter, t.translation(relevantLetter));
            }
        }
    }

    public void translateLetterTo(String encoded, String decoded) {
        letterEquivalents.put(encoded, decoded);
    }

    public boolean isTranslated(String letter) {
        return letterEquivalents.containsKey(letter);
    }

    public String translation(String encoded) {
        String delivered = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabet = alphabet + alphabet.toLowerCase();
        for (int i = 0; i < encoded.length(); i++) {
            if (alphabet.indexOf(encoded.substring(i, i + 1)) == -1) {
                delivered = delivered + encoded.substring(i, i + 1);
            }
            else if (this.isTranslated(encoded.substring(i, i + 1)) == true) {
                delivered = delivered + letterEquivalents.get(encoded.substring(i, i + 1));
            }
            else {
                delivered = delivered + "*";
            }
        }
        return delivered;
    }

    public boolean canBeTranslated(String encoded) {
        String reception = this.translation(encoded);
        int counter = 0;
        boolean canBeTranslated;
        while (counter < encoded.length() && !reception.substring(counter, counter + 1).equals("*")) {
            counter++;
        }
        if (counter >= reception.length()) {
            canBeTranslated = true;
        }
        else {
            canBeTranslated = false;
        }
        return canBeTranslated;
    }

    public boolean couldTranslateTo(String encoded, String word) {
        boolean couldTranslateTo;
        PatternMaker pattern = new PatternMaker();
        if (pattern.MakePattern(encoded).equals(pattern.MakePattern(word)) == true) {
            int counter = 0;
            boolean proceed = true;
            do {
                String relevantLetter = encoded.substring(counter, counter + 1);
                if (this.isTranslated(relevantLetter) == true && letterEquivalents.get(relevantLetter).equals(word.substring(counter, counter + 1)) == false) {
                    proceed = false;
                }
                else if (this.isTranslated(relevantLetter) == false && letterEquivalents.containsValue(word.substring(counter, counter + 1)) == true) {
                    proceed = false;
                }
                counter++;
            } while (counter < encoded.length() && proceed == true);
            couldTranslateTo = proceed;
        }
        else {
            couldTranslateTo = false;
        }

        return couldTranslateTo;
    }

}
