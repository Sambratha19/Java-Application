public class FileSystemNode {
    String name;
    Directory parent;
    FileSystemNode(String name, Directory parent){
        this.name=name;
        this.parent=parent;
    }

    public boolean isDirectory() {
        return true;
    }
}
