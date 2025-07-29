import java.util.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("File System.");
        FileSystem fs=new FileSystem();
        fs.create("/a", true);
        fs.create("/a/b", true);
        fs.create("/a/b/file.txt", false);
        System.out.println(fs.list("/a/b"));

        fs.UpdateFileContent("/a/b/file.txt", "Shakthi Ganesh");

        fs.rename("/a/b/file.txt", "sam.txt");
        fs.delete("/a/b/file.txt");
        fs.restore();
        System.out.println(fs.list("/a/b"));

    }
}