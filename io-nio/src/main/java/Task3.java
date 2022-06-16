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
    public static void main(String[] args) throws IOException {
        long start1 = System.currentTimeMillis();
        fileStreamsMover(Path.of("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\from\\fileToMove1.txt"),
                Path.of("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\to\\fileToMove1.txt"));
        long end1 = System.currentTimeMillis();
        System.out.println("fileStreamsMover : " + (end1 - start1));

        long start2 = System.currentTimeMillis();
        fileStreamsMoverWithBuffer100kb(Path.of("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\from\\fileToMove2.txt"),
                Path.of("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\to\\fileToMove1.txt"));
        long end2 = System.currentTimeMillis();
        System.out.println("fileStreamsMoverWithBuffer100kb : " + (end2 - start2));

        long start3 = System.currentTimeMillis();
        fileChannelMover(Path.of("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\from\\fileToMove3.txt"),
                Path.of("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\to\\fileToMove3.txt"));
        long end3 = System.currentTimeMillis();
        System.out.println("fileChannelMover : " + (end3 - start3));

        long start4 = System.currentTimeMillis();
        nio2FileAPIMover(Path.of("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\from\\fileToMove4.txt"),
                Path.of("C:\\Users\\Iryna_Kvasnytsya\\Desktop\\to\\fileToMove4.txt"));
        long end4 = System.currentTimeMillis();
        System.out.println("nio2FileAPIMover : " + (end4 - start4));
    }

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