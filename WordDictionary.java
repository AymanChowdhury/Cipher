import java.util.*;
public class WordDictionary {
    private Set storageImplement;
    private int count = 0;
    private Iterator<String> iterate;
    private boolean hasAddedSince = false;
    public WordDictionary(Set storage) {
        storage.clear();
        storageImplement = storage;
        iterate = storageImplement.iterator();
    }
    public void AddWord(String word) {
        storageImplement.add(word.toUpperCase());
        hasAddedSince = true;
    }
    public boolean IsInDictionary(String word) {
        return storageImplement.contains(word.toUpperCase());
    }
    public void ResetList() {
        count = 0;
        iterate = storageImplement.iterator();
    }
    public int Size() {
        return storageImplement.size();
    }
    public String nextWord() {
       /*
       returns the item of the next position of the set, even if the user adds a word in between nextWord() calls;
       accounts for adding a word in the middle of an iteration by making a new iterator and calling next() until
       it reaches the user's position
        */
        count++;
        if (hasAddedSince == true) {
            iterate = storageImplement.iterator();
            for (int i = 0; i < count - 1; i++) {
                iterate.next();
            }
            hasAddedSince = false;
        }
        return iterate.next();
    }
    public void Write(String fileName) {
       /*
       Writes the words in the set to the desired file
        */
        this.ResetList();
        List<String> passToWrittenFile = new ArrayList<>();
        FileUtilities filesDo = new FileUtilities();
        for (int i = 0; i < this.Size(); i++) {
            passToWrittenFile.add(this.nextWord());
        }
        this.ResetList();
        filesDo.writeLinesToFile(fileName, passToWrittenFile);
    }
    public void Read(String fileName) {
       /*
       Sets the words in the set to the words in the desired file
        */
        FileUtilities filesDO = new FileUtilities();
        List<String> collectWords = filesDO.readFileFromDisk(fileName);
        storageImplement.clear();
        for (int i = 0; i < collectWords.size(); i++) {
            this.AddWord(collectWords.get(i));
        }
    }

}
