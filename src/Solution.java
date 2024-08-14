import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathDirectory = reader.readLine();
        reader.close();
        Path path = Paths.get(pathDirectory);
        if (!Files.isDirectory(path)){
            System.out.println(path.toAbsolutePath() + " - не папка");
        } else {
            AtomicInteger allDirectories = new AtomicInteger();
            AtomicInteger allFiles = new AtomicInteger();
            AtomicLong allByteSize = new AtomicLong();
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (!dir.equals(path)) {
                        allDirectories.incrementAndGet();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    allFiles.incrementAndGet();
                    allByteSize.addAndGet(attrs.size());
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Всего папок - " + allDirectories.get());
            System.out.println("Всего файлов - " + allFiles.get());
            System.out.println("Общий размер - " + allByteSize.get());
        }
    }
}

