import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task3 {
    //    Write several versions of the FastFileMover utility, which moves a file from one directory to another directory.
//    It takes both file paths as command-line parameters. All exceptions must be handled correctly.
//    Required functionality:
//
//    It uses FileStreams;
//    It uses FileStreams with buffer 100 kb;
//    It uses FileChannel;
//    It uses NIO 2 File API.
//    After that prepare a performance report based on the next requirements.
//
//    Measure the time for copying, run on several reference files of different sizes
//            (1 Kb, 100 Kb, 10 Mb, 1 GB). On each file, run 1000 times, get the average time.

    public static void fileStreamsMover(Path from, Path to) throws IOException {
        if (!from.toFile().exists()) {
            throw new FileNotFoundException("File does not exist");
        }
        try (FileInputStream input = new FileInputStream(from.toFile());
             FileOutputStream output = new FileOutputStream(to.toString())) {
            int i;
            while ((i = input.read()) != -1) {
                output.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!from.toFile().delete()) {
            throw new IOException("File not deleted");
        }
    }

    public static void fileStreamsMoverWithBuffer100kb(Path from, Path to) throws IOException {
        if (!from.toFile().exists()) {
            throw new FileNotFoundException("File does not exist");
        }
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(from.toFile()), 1000);
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(to.toString()))) {
            byte[] bytes;
            while (input.available() > 0) {
                bytes = input.readNBytes(1000);
                output.write(bytes);
                output.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!from.toFile().delete()) {
            throw new IOException("File not deleted");
        }
    }

    public static void fileChannelMover(Path from, Path to) throws IOException {
        if (!from.toFile().exists()) {
            throw new FileNotFoundException("File does not exist");
        }
        try (FileChannel source = new FileInputStream(from.toFile()).getChannel();
             FileChannel destination = new FileOutputStream(to.toFile()).getChannel()) {
            long count = 0;
            long size = source.size();
            while ((count += destination.transferFrom(source, count, size - count)) < size) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!from.toFile().delete()) {
            throw new IOException("File not deleted");
        }
    }

    public static void nio2FileAPIMover(Path from, Path to) throws IOException {
        if (!from.toFile().exists()) {
            throw new FileNotFoundException("File does not exist");
        }
        Files.move(from, to);
    }
    public static void main(String[] args) throws IOException {
        Path folderPathFrom1 = Path.of("io-nio\\src\\main\\resources\\from\\fileToMove1.txt");
        Path folderPathTo1 = Path.of("io-nio\\src\\main\\resources\\to\\fileToMove1.txt");
        Path absolutePathFrom1 = folderPathFrom1.toAbsolutePath();
        Path absolutePathTo1 = folderPathTo1.toAbsolutePath();
        Path folderPathFrom2 = Path.of("io-nio\\src\\main\\resources\\from\\fileToMove2.txt");
        Path folderPathTo2 = Path.of("io-nio\\src\\main\\resources\\to\\fileToMove2.txt");
        Path absolutePathFrom2 = folderPathFrom2.toAbsolutePath();
        Path absolutePathTo2 = folderPathTo2.toAbsolutePath();
        Path folderPathFrom3 = Path.of("io-nio\\src\\main\\resources\\from\\fileToMove3.txt");
        Path folderPathTo3 = Path.of("io-nio\\src\\main\\resources\\to\\fileToMove3.txt");
        Path absolutePathFrom3 = folderPathFrom3.toAbsolutePath();
        Path absolutePathTo3 = folderPathTo3.toAbsolutePath();
        Path folderPathFrom4 = Path.of("io-nio\\src\\main\\resources\\from\\fileToMove4.txt");
        Path folderPathTo4 = Path.of("io-nio\\src\\main\\resources\\to\\fileToMove4.txt");
        Path absolutePathFrom4 = folderPathFrom4.toAbsolutePath();
        Path absolutePathTo4 = folderPathTo4.toAbsolutePath();

//        Path folderPathFrom21 = Path.of("io-nio\\src\\main\\resources\\from\\1GB1.zip");
//        Path folderPathTo21 = Path.of("io-nio\\src\\main\\resources\\to\\1GB1.zip");
//        Path absolutePathFrom21 = folderPathFrom21.toAbsolutePath();
//        Path absolutePathTo21 = folderPathTo21.toAbsolutePath();
//        Path folderPathFrom22 = Path.of("io-nio\\src\\main\\resources\\from\\1GB2.zip");
//        Path folderPathTo22 = Path.of("io-nio\\src\\main\\resources\\to\\1GB2.zip");
//        Path absolutePathFrom22 = folderPathFrom22.toAbsolutePath();
//        Path absolutePathTo22 = folderPathTo22.toAbsolutePath();
//        Path folderPathFrom23 = Path.of("io-nio\\src\\main\\resources\\from\\1GB3.zip");
//        Path folderPathTo23 = Path.of("io-nio\\src\\main\\resources\\to\\1GB3.zip");
//        Path absolutePathFrom23 = folderPathFrom23.toAbsolutePath();
//        Path absolutePathTo23 = folderPathTo23.toAbsolutePath();
//        Path folderPathFrom24 = Path.of("io-nio\\src\\main\\resources\\from\\1GB4.zip");
//        Path folderPathTo24 = Path.of("io-nio\\src\\main\\resources\\to\\1GB4.zip");
//        Path absolutePathFrom24 = folderPathFrom24.toAbsolutePath();
//        Path absolutePathTo24 = folderPathTo24.toAbsolutePath();

//        Path folderPathFrom31 = Path.of("io-nio\\src\\main\\resources\\from\\10mb1.jpg");
//        Path folderPathTo31 = Path.of("io-nio\\src\\main\\resources\\to\\10mb1.jpg");
//        Path absolutePathFrom31 = folderPathFrom31.toAbsolutePath();
//        Path absolutePathTo31 = folderPathTo31.toAbsolutePath();
//        Path folderPathFrom32 = Path.of("io-nio\\src\\main\\resources\\from\\10mb2.jpg");
//        Path folderPathTo32 = Path.of("io-nio\\src\\main\\resources\\to\\10mb2.jpg");
//        Path absolutePathFrom32 = folderPathFrom32.toAbsolutePath();
//        Path absolutePathTo32 = folderPathTo32.toAbsolutePath();
//        Path folderPathFrom33 = Path.of("io-nio\\src\\main\\resources\\from\\10mb3.jpg");
//        Path folderPathTo33 = Path.of("io-nio\\src\\main\\resources\\to\\10mb3.jpg");
//        Path absolutePathFrom33 = folderPathFrom33.toAbsolutePath();
//        Path absolutePathTo33 = folderPathTo33.toAbsolutePath();
//        Path folderPathFrom34 = Path.of("io-nio\\src\\main\\resources\\from\\10mb4.jpg");
//        Path folderPathTo34 = Path.of("io-nio\\src\\main\\resources\\to\\10mb4.jpg");
//        Path absolutePathFrom34 = folderPathFrom34.toAbsolutePath();
//        Path absolutePathTo34 = folderPathTo34.toAbsolutePath();


        System.out.println("moving txt file");
        long start11 = System.currentTimeMillis();
        fileStreamsMover(absolutePathFrom1,absolutePathTo1);
        long end11 = System.currentTimeMillis();
        System.out.println("fileStreamsMover : " + (end11 - start11));

        long start12 = System.currentTimeMillis();
        fileStreamsMoverWithBuffer100kb(absolutePathFrom2,absolutePathTo2);
        long end12 = System.currentTimeMillis();
        System.out.println("fileStreamsMoverWithBuffer100kb : " + (end12 - start12));

        long start13 = System.currentTimeMillis();
        fileChannelMover(absolutePathFrom3,absolutePathTo3);
        long end13 = System.currentTimeMillis();
        System.out.println("fileChannelMover : " + (end13 - start13));

        long start14 = System.currentTimeMillis();
        nio2FileAPIMover(absolutePathFrom4,absolutePathTo4);
        long end14 = System.currentTimeMillis();
        System.out.println("nio2FileAPIMover : " + (end14 - start14));
        System.out.println();

// add 1 gb file to resources folder
//        System.out.println("moving 1 gb file");
//        long start21 = System.currentTimeMillis();
//        fileStreamsMover(absolutePathFrom21,absolutePathTo21);
//        long end21 = System.currentTimeMillis();
//        System.out.println("fileStreamsMover : " + (end21 - start21));
//
//        long start22 = System.currentTimeMillis();
//        fileStreamsMoverWithBuffer100kb(absolutePathFrom22,absolutePathTo22);
//        long end22 = System.currentTimeMillis();
//        System.out.println("fileStreamsMoverWithBuffer100kb : " + (end22 - start22));
//
//        long start23 = System.currentTimeMillis();
//        fileChannelMover(absolutePathFrom23,absolutePathTo23);
//        long end23 = System.currentTimeMillis();
//        System.out.println("fileChannelMover : " + (end23 - start23));
//
//        long start24 = System.currentTimeMillis();
//        nio2FileAPIMover(absolutePathFrom24,absolutePathTo24);
//        long end24 = System.currentTimeMillis();
//        System.out.println("nio2FileAPIMover : " + (end24 - start24));
//        System.out.println();

// add 10 mb file to resources folder
//        System.out.println("moving 10 mb file");
//        long start31 = System.currentTimeMillis();
//        fileStreamsMover(absolutePathFrom31,absolutePathTo31);
//        long end31 = System.currentTimeMillis();
//        System.out.println("fileStreamsMover : " + (end31 - start31));
//
//        long start32 = System.currentTimeMillis();
//        fileStreamsMoverWithBuffer100kb(absolutePathFrom32,absolutePathTo32);
//        long end32 = System.currentTimeMillis();
//        System.out.println("fileStreamsMoverWithBuffer100kb : " + (end32 - start32));
//
//        long start33 = System.currentTimeMillis();
//        fileChannelMover(absolutePathFrom33,absolutePathTo33);
//        long end33 = System.currentTimeMillis();
//        System.out.println("fileChannelMover : " + (end33 - start33));
//
//        long start34 = System.currentTimeMillis();
//        nio2FileAPIMover(absolutePathFrom34,absolutePathTo34);
//        long end34 = System.currentTimeMillis();
//        System.out.println("nio2FileAPIMover : " + (end34 - start34));
    }

}
//1kb txt
//fileStreamsMover:1        1
//fileStreamsMoverWithBuffer100kb:2     3
//fileChannelMover:3        4
//nio2FileAPIMover:2        1

//100kb txt
//fileStreamsMover:357      510
//fileStreamsMoverWithBuffer100kb:4     3
//fileChannelMover:3        5
//nio2FileAPIMover:1        2

//10mb jpg
//fileStreamsMover:31965      32239
//fileStreamsMoverWithBuffer100kb:68     86
//fileChannelMover:8        8
//nio2FileAPIMover:40        47

//1gb zip
//fileStreamsMover:
//fileStreamsMoverWithBuffer100kb:6861     6433
//fileChannelMover:627        594
//nio2FileAPIMover:131        143