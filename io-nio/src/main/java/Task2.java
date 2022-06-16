import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {

    //    Write the DiskAnalyzer utility command line, which accepts a path to the input (for example, C: )
//    and a function number, correctly processes incorrect paths or function numbers.
//    The utility outputs the results of its work to a file.
//    The program should work for the C: drive of your working machine.
//
//    Required functionality:
//
//    Search for the file name with the maximum number of letters ‘s’ in the name, display the path to it.
//    Print Top-5 largest files (by size in bytes).
//    The average file size in the specified directory or any its subdirectory.
//    The number of files and folders, divided by the first letters of the alphabet
//    (for example, 100,000 files and 200 folders begin with the letter A).

    public static void main(String[] args) throws IOException {
        Task2 task = new Task2();
        //1
        Path path = task.analyzerMaximumSLetters("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\BANK");
        System.out.println("Maxixum S letters : " + path);
        System.out.println("////////////////");

        //2
        List<Path> paths = task.analyzer5TopLargestFiles("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\BANK");
        System.out.println("Listing 5 top largest files :");
        paths.forEach(System.out::println);
        System.out.println("////////////////");

        //3
        Double averageFileSize = task.analyzerAverageFileSize("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\BANK");
        System.out.println("Average file size : " + averageFileSize);
        System.out.println("////////////////");
        //4
        Long numberOfFiles = task.analyzerNumberOfFilesStartsWithA("C:\\Users\\Iryna_Kvasnytsya\\Desktop");
        System.out.println("Number of files which starts with A : " + numberOfFiles);
        System.out.println("////////////////");
    }

    Stream<Double> stream = Stream.of(1d, 2d, 3d);

    public Path analyzerMaximumSLetters(String path) throws IOException {
        Stream<Path> listFiles = Files.walk(Path.of(path), 1);

        Optional<Path> result = listFiles
                .filter(name -> name.getFileName().toString().contains("s"))
                .max(Comparator.comparing(path1 -> getSCount(path1.getFileName().toString())));
        return result.orElseGet(() -> Path.of(""));

    }

    public List<Path> analyzer5TopLargestFiles(String path) throws IOException {
        List<Path> paths = new ArrayList<Path>();
        return Files.walk(Path.of(path), 1)
                .sorted((f1, f2) -> Long.compare(getFileSize(f2), getFileSize(f1)))
                .limit(5)
                .collect(Collectors.toList());
    }

    public Double analyzerAverageFileSize(String path) throws IOException {
        Stream<Path> walk = Files.walk(Path.of(path));
        OptionalDouble optionalAverageFileSize = walk.mapToLong(this::getFileSize).average();
        return optionalAverageFileSize.orElseGet(null);
    }


    public Long analyzerNumberOfFilesStartsWithA(String path) throws IOException {
        return Files.walk(Path.of(path), 10).map(path1 -> path1.getFileName().toString())
                .filter(name -> name.startsWith("A") || name.startsWith("a"))
                .count();
    }

    private int getSCount(String name) {
        char[] arr = name.toCharArray();
        int count = 0;
        for (char c : arr) {
            if (c == 's' || c == 'S') {
                count++;
            }
        }
        return count;
    }

    private long getFileSize(Path path) {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }
}
