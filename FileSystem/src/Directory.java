import java.util.*;

public class Directory extends FileSystemNode{

    Map<String, FileSystemNode> children; //name, file or directory

    Directory(String name, Directory parent){
        super(name, parent);
        this.children=new HashMap<>();
    }

    public boolean isDirectory(){ //check the path in crt format
        return true;
    }
}
