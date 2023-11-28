public class PatternMaker {
    public static String MakePattern(String recipient) {
        char [] lettersHold = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',                                                                       'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String recipientUse = recipient.toLowerCase();
        String pattern = "";
        int counter = 0;
        for (int iteration = 0; iteration < recipientUse.length(); iteration++) {
            for (int iterate = 0; iterate < iteration; iterate++) {
                if (!recipientUse.substring(iteration, iteration+    1).equals(recipientUse.substring(iterate, iterate + 1))) {
                    if (!((iterate > 0) && (pattern.substring(0, iterate).contains(pattern.substring(iterate, iterate + 1))))) {
                        counter++;
                    }
                }
                else {
                    iterate = iteration;
                }
            }
            pattern = pattern + lettersHold[counter];
            counter = 0;
        }
        return pattern;
    }
}

