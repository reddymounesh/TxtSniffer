import java.io.*;
import java.util.concurrent.ConcurrentMap;


public class SnifferTask implements Runnable{
    private final File file;
    private final String keyword;
    private final ConcurrentMap<File,Integer > resultMap;
    private final boolean usestemming;

    public SnifferTask(File file,String keyword,ConcurrentMap<File,Integer> resultMap,boolean usestemming){
        this.file=file;
        this.keyword=keyword.toLowerCase();
        this.resultMap=resultMap;
        this.usestemming=usestemming;

    }
    @Override
    public void run() {
        int count = 0;
        String searchWord = usestemming ? PorterStemmer.stem(keyword) : keyword.toLowerCase();
    
        System.out.println("🔍 [Thread] Scanning file: " + file.getName());
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
    
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] words = line.toLowerCase().split("\\W+");
    
                for (String word : words) {
                    if (word.isEmpty()) continue;
                    String target = usestemming ? PorterStemmer.stem(word) : word;
    
                    if (target.equals(searchWord)) {
                        count++;
                        System.out.println("✅ Found match in " + file.getName() + " (line " + lineNumber + "): " + word);
                    }
                }
            }
    
            System.out.println("📄 Finished scanning " + file.getName() + " → Total matches: " + count);
    
            if (count > 0) {
                resultMap.put(file, count);
            }
    
        } catch (IOException e) {
            System.out.println("❌ Error reading " + file.getName());
        }
    }
    
    
}
