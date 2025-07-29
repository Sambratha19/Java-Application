public class File extends FileSystemNode{
    StringBuilder content;
    File(String name, Directory parent){
        super(name, parent);
        this.content=new StringBuilder();
    }

    public boolean isDirectory(){
        return true;
    }
}
