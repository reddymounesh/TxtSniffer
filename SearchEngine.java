import java.io.File;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class SearchEngine {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the directory: \n");
        String dirpath=sc.nextLine().trim();

        System.out.println("Enter the keyword to search: \n");
        String keyword=sc.nextLine().trim();

        System.out.println("Enable stemming (yes/no): ");
        boolean usestemming=sc.nextLine().trim().equalsIgnoreCase("yes");

        File directory=new File(dirpath);
        if (!directory.exists() || !directory.isDirectory()){
            System.out.println("INVALID DIRECTORY");
            return;
        }

        List<File> textfiles=getAllTextFiles(directory);
        if(textfiles.isEmpty()){
            System.out.println("no .txt files found");
            return;
        }

        ConcurrentMap<File,Integer> resultMap=new ConcurrentHashMap<>();
        ExecutorService executor=Executors.newFixedThreadPool(5);

        for(File file : textfiles){
            executor.execute(new SearchTask(file,keyword,resultMap,usestemming));

        }
        executor.shutdown();
        executor.awaitTermination(1,TimeUnit.MINUTES);

        List<Map.Entry<File,Integer>> sortedResults=resultMap.entrySet().stream().sorted(Map.Entry.<File,Integer>comparingByValue().reversed()).collect(Collectors.toList());

        System.out.println("\n Search Results (ranked):");
        for (Map.Entry<File,Integer> entry:sortedResults){
            System.out.println(entry.getKey().getName()+":"+entry.getValue()+"hits");

        }
        


    }
    public static List<File>getAllTextFiles(File folder){
        List<File> files=new ArrayList<>();
        File[] allFiles=folder.listFiles();
        if (allFiles==null)return files;

        for(File file:allFiles){
            if(file.isDirectory()){
                files.addAll(getAllTextFiles(file));

            }else if(file.getName().toLowerCase().endsWith(".txt")){
                files.add(file);

            }
        }
        return files;

    }
}
