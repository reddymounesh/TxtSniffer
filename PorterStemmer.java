public class PorterStemmer {
    public static String stem(String word){
        if (word==null || word.length()<3) return word.toLowerCase();
        word=word.toLowerCase();

        if(word.endsWith("ing") && word.length()>5)
        return word.substring(0, word.length()-3);

        if (word.endsWith("ed") && word.length()>4)
        return word.substring(0, word.length()-2);

        if (word.endsWith("er") && word.length()>4)
        return word.substring(0, word.length()-2);

        if (word.endsWith("s") && word.length()>3)
        return word.substring(0, word.length()-1);

        return word;
        
        
    }
    
}
